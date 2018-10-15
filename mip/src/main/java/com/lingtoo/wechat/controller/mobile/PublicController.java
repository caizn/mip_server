package com.lingtoo.wechat.controller.mobile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lingtoo.wechat.utils.FileOperation;
import com.lingtoo.wechat.utils.Strings;
import com.lingtoo.wechat.utils.UploadImgUtil;

@Controller("MobilePublicController")
@RequestMapping("/mobile/public")
public class PublicController {
	@Value("${system.image.logo.absolutePath}")
	private String imagePath;

	@RequestMapping("upload-pictrue")
	@ResponseBody
	public String savedWorkerApply(@RequestParam(required = false) MultipartFile pic, HttpSession session, Model model,Integer rotate,
			HttpServletRequest request) {
		String logoPath = "/images/user/member/";
		if (pic != null && !pic.isEmpty()) {
			String imgName = "";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmSSS");
			imgName = sdf.format(new Date()) + Strings.getRandomNum("1", 5) + "."
					+ FileOperation.getExtension(pic.getOriginalFilename()).toLowerCase();
			logoPath = logoPath + imgName;

			try {
				File file = new File(pic + logoPath);
				if (file.exists())
					file.delete();

				UploadImgUtil.suoxiaoImg(pic,
						FileOperation.getExtension(pic.getOriginalFilename()).toLowerCase(), imagePath + logoPath,
						null, null, rotate);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return logoPath;
	}
}
