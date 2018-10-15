package com.lingtoo.wechat.utils.wxpay;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.lingtoo.wechat.components.MyX509TrustManager;
import com.lingtoo.wechat.pojo.WxPaySet;

/**
 * 微信接口调用模拟Http连接
 * 
 * @author Caizn
 * 
 */
public class WeixinHttpUtil {
	private static Logger log = Logger.getLogger(WeixinHttpUtil.class);

	public static Document httpKeyRequest(String requestUrl, String xmldoc,
			WxPaySet pSet) {
		// 指定读取证书格式为PKCS12
		Document xml = null;
		try {
			KeyStore keyStore;
			keyStore = KeyStore.getInstance("PKCS12");
			// 读取本机存放的PKCS12证书文件
			FileInputStream instream = new FileInputStream(new File(
					pSet.getPayFile()));
			try {
				// 指定PKCS12的密码(商户ID)
				keyStore.load(instream, pSet.getPartnerid().toCharArray());
			} finally {
				instream.close();
			}
			SSLContext sslcontext = SSLContexts
					.custom()
					.loadKeyMaterial(keyStore,
							pSet.getPartnerid().toCharArray()).build();
			// 指定TLS版本
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
					sslcontext,
					new String[] { "TLSv1" },
					null,
					SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			// 设置httpclient的SSLSocketFactory
			CloseableHttpClient httpclient = HttpClients.custom()
					.setSSLSocketFactory(sslsf).build();

			// POST的URL
			HttpPost httppost = new HttpPost(requestUrl);
			// 建立HttpPost对象
			// 添加参数
			StringEntity myEntity = new StringEntity(xmldoc, "UTF-8");
			httppost.setEntity(myEntity);
			// 设置编码
			HttpResponse response = httpclient.execute(httppost);

			HttpEntity resEntity = response.getEntity();
			InputStream is = resEntity.getContent();
			InputStreamReader reader = new InputStreamReader(is, "UTF-8");

			char[] buff = new char[1024];
			int length = 0;
			String xmlStr = "";
			while ((length = reader.read(buff)) != -1) {
				xmlStr += new String(buff, 0, length);
				log.error(new String(buff, 0, length));
			}

			xmlStr.replace("\n", "");
			StringReader sr = new StringReader(xmlStr);
			InputSource isource = new InputSource(sr);

			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			xml = builder.parse(isource);

			httpclient.getConnectionManager().shutdown();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("调用红包接口异常");
		}
		return xml;
	}

	/**
	 * 发起https请求xml文件并获取xml结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（POST，GET）
	 * @param xmldoc
	 *            发起的xml文件
	 * @return
	 */
	public static Document httpXMLRequest(String requestUrl,
			String requestMethod, String xmldoc) {
		Document xml = null;
		StringBuffer buffer = new StringBuffer();
		log.warn("开始创建菜单，提交httpRequest");
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();
			// 当有数据需要提交时
			if (null != xmldoc) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(xmldoc.getBytes("UTF-8"));
				outputStream.close();
			}
			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String str = "";
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			log.error(buffer.toString());
			StringReader sr = new StringReader(buffer.toString());
			InputSource is = new InputSource(sr);
			// 释放资源
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			xml = builder.parse(is);

		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			log.error("https request error:{}", e);
		}
		return xml;
	}


	/**
	 * 发起https请求xml文件获取String结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（POST，GET）
	 * @param xmldoc
	 *            发起的xml文件
	 * @return
	 */
	public static String httpXMLRequestString(String requestUrl,
			String requestMethod, String xmldoc) {
		StringBuffer buffer = new StringBuffer();
		log.warn("开始创建菜单，提交httpRequest");
		String recieveString="";
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();
			// 当有数据需要提交时
			if (null != xmldoc) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(xmldoc.getBytes("UTF-8"));
				outputStream.close();
			}
			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String str = "";
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			log.error(buffer.toString());
			recieveString=buffer.toString();
		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			log.error("https request error:{}", e);
		}
		return recieveString;
	}
	
	
	/**
	 * 发起https请求发送文件并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（POST，GET）
	 * @param filepath
	 *            文件路径
	 * @return
	 */
	public static JSONObject httpMultiRequest(String requestUrl,
			String requestMethod, String filepath, String postData) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		log.warn("开始创建连接，提交httpRequest");
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url
					.openConnection();
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
			httpUrlConn.setRequestProperty("Connection", "Keep-Alive");
			httpUrlConn.setRequestProperty("Charset", "UTF-8");
			httpUrlConn
					.setRequestProperty("Accept-Encoding", "gzip,   deflate");
			String BOUNDARY = "----------" + System.currentTimeMillis();
			httpUrlConn.setRequestProperty("Content-Type",
					"multipart/form-data; boundary=" + BOUNDARY);
			httpUrlConn
					.setRequestProperty("User-Agent",
							"Mozilla/4.0   (compatible;   MSIE   6.0;   Windows   NT   5.1)");
			httpUrlConn
					.setRequestProperty(
							"Accept",
							"image/gif,image/x-xbitmap,image/jpeg,image/pjpeg,application/vnd.ms-excel,application/vnd.ms-powerpoint,application/msword,application/x-shockwave-flash,application/x-quickviewplus,*/*");
			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();
			File file = new File(filepath);
			if (file.isFile()) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				StringBuilder sb = new StringBuilder();
				sb.append("--");
				sb.append(BOUNDARY);
				sb.append("\r\n");
				sb.append("Content-Disposition: form-data;name=\"file\";filename=\""
						+ file.getName() + "\"\r\n");
				sb.append("Content-Type:application/octet-stream\r\n\r\n");
				byte[] head = sb.toString().getBytes("utf-8");
				outputStream.write(head);
				DataInputStream in = new DataInputStream(new FileInputStream(
						file));
				int bytes = 0;
				byte[] bufferOut = new byte[1024];
				while ((bytes = in.read(bufferOut)) != -1) {
					outputStream.write(bufferOut, 0, bytes);
				}
				in.close();
				byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n")
						.getBytes("utf-8");// 定义最后数据分隔线
				outputStream.write(foot);
				outputStream.flush();
				outputStream.close();
			}
			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			if (requestMethod.equals("GET")) {
				OutputStream os = new FileOutputStream(file);
				int bytesRead = 0;
				byte[] bytes = new byte[8192];
				while ((bytesRead = inputStream.read(bytes, 0, 8192)) != -1) {
					os.write(bytes, 0, bytesRead);
				}
				os.close();
				inputStreamReader.close();
				inputStream.close();
				inputStream = null;
				httpUrlConn.disconnect();
			} else {
				BufferedReader bufferedReader = new BufferedReader(
						inputStreamReader);
				String str = null;
				while ((str = bufferedReader.readLine()) != null) {
					buffer.append(str);
				}
				bufferedReader.close();
				inputStreamReader.close();
				inputStream.close();
				inputStream = null;
				httpUrlConn.disconnect();
				jsonObject =new JSONObject(buffer.toString());
			}
		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			log.error("https request error:{}", e);
		}
		return jsonObject;
	};

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static Document httpsRequestXml(String requestUrl,
			String requestMethod, String outputStr) {
		Document xml = null;
		StringBuffer buffer = new StringBuffer();
		log.warn("开始创建菜单，提交httpRequest");
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();
			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String str = "";
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			log.error(buffer.toString());
			StringReader sr = new StringReader(buffer.toString());
			InputSource is = new InputSource(sr);
			// 释放资源
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			xml = builder.parse(is);
		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			log.error("https request error:{}", e);
		}
		return xml;
	}

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpsRequest(String requestUrl,
			String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		log.warn("开始创建菜单，提交httpRequest");
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = new JSONObject(buffer.toString());
		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			log.error("https request error:{}", e);
		}
		return jsonObject;
	}

	public static void main(String[] args) {
		WeixinHttpUtil.httpsRequest("http://124.31.217.147:5555/20150323/",
				"GET", "");
	}
}
