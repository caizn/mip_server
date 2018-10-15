function Trim(str, is_global) {
	var result;
	result = str.replace(/(^\s+)|(\s+$)/g, "");
	if (is_global.toLowerCase() == "g") result = result.replace(/\s/g, "");
	return result;
}
function clearBr(key) {
	key = Trim(key, "g");
	key = key.replace(/<\/?.+?>/g, "");
	key = key.replace(/[\r\n]/g, "");
	return key;
}

//获取随机数
function getANumber() {
	var date = new Date();
	var times1970 = date.getTime();
	var times = date.getDate() + "" + date.getHours() + "" + date.getMinutes() + "" + date.getSeconds();
	var encrypt = times * times1970;
	if (arguments.length == 1) {
		return arguments[0] + encrypt;
	} else {
		return encrypt;
	}

}

//以下是package组包过程：
var oldPackageString; //记住package，方便最后进行整体签名时取用
function getPartnerId() {
	return $("#partnerid").val();
}

function getPartnerKey() {
	return $("#partnerkey").val();
}

function getPackage() {
	completeString = "prepay_id="+$("#prepay_id").val()
	oldPackageString = completeString; //记住package，方便最后进行整体签名时取用
	return completeString;
}

//下面是app进行签名的操作：
var oldTimeStamp; //记住timestamp，避免签名时的timestamp与传入的timestamp时不一致
var oldNonceStr; //记住nonceStr,避免签名时的nonceStr与传入的nonceStr不一致
function getAppId() {
	return $("#appid").val();
}

function getAppKey() {
	return $("#paysignkey").val();
}

function getProductID(){
	return $("#productid").val();
}

function getTimeStamp() {
	var timestamp = new Date().getTime();
	var timestampstring = timestamp.toString(); //一定要转换字符串
	oldTimeStamp = timestampstring;
	return timestampstring;
}
/**
 * 随即字符串
 * @returns {String}
 */
function getNonceStr() {
	var $chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
	var maxPos = $chars.length;
	var noceStr = "";
	for (i = 0; i < 32; i++) {
		noceStr += $chars.charAt(Math.floor(Math.random() * maxPos));
	}
	oldNonceStr = noceStr;
	return noceStr;
}

function getSignType() {
	return "MD5";
}

var oldSignNative="";
function getSignNative(){
	
}

var oldSign="";
/**
 * 获取签名
 * @returns
 */
function getSign() {
	//第一步，对所有需要传入的参数加上appkey作一次key＝value字典序的排序
	var keyvaluestring = "appId=" + getAppId().toString() +
		"&nonceStr=" + oldNonceStr + 
		"&package=" + getPackage() + 
		"&signType=" + getSignType()+
		"&timeStamp=" + oldTimeStamp+
		"&key="+$("#partnerkey").val();
	sign = CryptoJS.MD5(keyvaluestring).toString().toLocaleUpperCase();
    oldSign=sign;
	return sign;
}