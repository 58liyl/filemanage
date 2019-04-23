package es.attachment.filemanage.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;;

@RestController
public class FileManage {

	@ResponseBody
	public String get() {
		return null;
	}

	@RequestMapping("/filedownload/{name}")
	public void download(HttpServletRequest request, HttpServletResponse response,
			@PathVariable(name = "name", required = false) String fileName) {
		try {
			System.out.println(fileName);
			if (fileName == null) {
				fileName = "标准文本.txt";
			}
			File file = new File("C:\\Users\\Fiona\\Desktop\\"+fileName);
			System.out.println(file.getName());
			String isoName = new String(file.getName().getBytes("UTF-8"), "ISO-8859-1");
			response.addHeader("Content-Disposition", "attachment;filename=" + isoName);
			response.setHeader("Content-Length", String.valueOf(file.length()));
			response.setContentType("text/plain;charset=utf-8");
			OutputStream output = response.getOutputStream();
			InputStream inputStream = new FileInputStream(file);
			IOUtils.copy(inputStream, output);
			IOUtils.closeQuietly(inputStream);
			IOUtils.closeQuietly(output);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
