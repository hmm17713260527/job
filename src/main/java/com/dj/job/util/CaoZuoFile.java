package com.dj.job.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public class CaoZuoFile {

	public static String scfile(MultipartFile fileName) {

		try {

			// 判断fileName是否为空
			if (!fileName.isEmpty()) {

				String path = "D:/upload/";
				File scfile = new File(path);

				// 判断E:/upload/是否存在 scfile.exists() 存在�?? true
				if (!scfile.exists()) {

					// mkdirs()与mkdir()的区别：mkdirs():也创建子文件 E:/upload/a/b/ mkdir() 只创�?? E:/upload/
					scfile.mkdirs();

				}
				
				String newFileName = UUID.randomUUID().toString().replace("-", "");
				String hz = fileName.getOriginalFilename().substring(fileName.getOriginalFilename().lastIndexOf("."));

				fileName.transferTo(new File(path+newFileName+hz));

				String str = newFileName+hz;
				return str;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 文件下载处理方法
	 */
	public static void xzfile(HttpServletResponse response, String fileName) {

		try {

			// 格式规范
			response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

			// 读取下载文件 也就是从(D:/upload/)地址下载
			InputStream input = new FileInputStream("D:/upload/" + fileName);

			// 获得response输出�??
			OutputStream out = response.getOutputStream();

			int len = 0;
			byte[] b = new byte[4096];

			while ((len = input.read(b)) > 0) {

				out.write(b, 0, len);

			}

			out.close();

			input.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
