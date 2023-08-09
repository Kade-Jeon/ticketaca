package com.ticketaca.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/download.do")
public class FileDownloadController extends HttpServlet {
	private static String exhImageRepo = "C:\\ticket\\exh_image";  //+글번호폴더+파일명


	protected void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//이미지 파일 이름과 글번호를 가져옵니다.
		String exhNO = request.getParameter("no");
		if(exhNO == null) {
			exhNO = request.getParameter("exhNo");
		}
		String image = (String) request.getParameter("image");
		String detailImage = (String) request.getParameter("detailImage");
		
		System.out.println("exhNO ====== " + exhNO);
		System.out.println("image ====== " + image);
		System.out.println("detailImage =====" + detailImage);
				
		OutputStream out = response.getOutputStream();
		
		// 메인 이미지 포함 여부 확인
		if(image != null) {
			//String path = exhImageRepo + "\\" + exhNO + "\\" + image + ".png"; //글 번호에 대한 파일 경로를 설정
			String path = exhImageRepo + "\\" + exhNO + "\\" + image ; //글 번호에 대한 파일 경로를 설정
			File imageFile = new File(path);
			
			response.setHeader("Cache-Control", "no-cache");
			response.addHeader("Content-disposition", "attachment;fileName=" + image); //이미지를 내려받는데 필요한 response에 헤더정보를 설정
			
			try {
				FileInputStream in = new FileInputStream(imageFile);
				
				byte[] buffer = new byte[1024 * 8];
			    int count;
			    while ((count = in.read(buffer)) != -1) {
			        out.write(buffer, 0, count);
			    }
			    in.close();
			} catch(Exception e) {
				System.out.println("FileInputStream (image) ERR : " + e.getMessage());
			}
		}
		
		// 상세 이미지 포함 여부 확인
		if(detailImage != null) {
			String path1 = exhImageRepo + "\\" + exhNO + "\\" + detailImage; //글 번호에 대한 파일 경로를 설정
			File imageFile1 = new File(path1);
			
			response.addHeader("Content-disposition", "attachment;fileName=" + detailImage);
			
			try {
				FileInputStream in1 = new FileInputStream(imageFile1);
				
				byte[] buffer = new byte[1024 * 8];
			    int count;
			    while ((count = in1.read(buffer)) != -1) {
			        out.write(buffer, 0, count);
			    }
			    in1.close();
			} catch(Exception e) {
				System.out.println("FileInputStream (detailImage) ERR : " + e.getMessage());
			}
		}	
		out.close();
	}

}