//package com.ticketaca.util;
//
//import java.io.IOException;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.ticketaca.dao.AdminDAO;
//import com.ticketaca.vo.ExhVO;
//
//@WebServlet("/paging")
//public class Paging extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//    public Paging() {
//        super();
//    }
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		List<ExhVO> list = null;
//		AdminDAO adminDAO = new AdminDAO();
//		int totalExhCount = adminDAO.selectTotalExhs();	// 총 글 갯수
//		
//		int currentPageNum = 1;	// 현재 페이지 넘버
//		int countList = 10; // 보여줄 글 갯수
//		int countPage = 10;	// 페이지 갯수
//		int block = totalExhCount / countList;
//		
//		if(totalExhCount % countList != 0){
//			block++;
//		}
//		String pageNum = request.getParameter("pageNum");
//		if (pageNum == null) {
//			currentPageNum = 1;
//		}else {
//			currentPageNum = Integer.parseInt(pageNum);
//			if(currentPageNum <= 0 ) {
//				currentPageNum = 1;
//			}
//			if(currentPageNum > block) {
//				currentPageNum -= 3;
//			}
//		}
//		
//		int startPage = (currentPageNum-1) / countPage * countPage + 1; // 시작 페이지
//		int endPage = startPage + countPage - 1; // 끝 페이지
//		if (endPage > block) {
//			endPage = block;
//		}
//		int start = currentPageNum * 5 - 4;
//		int end = currentPageNum * 5;
//		list = adminDAO.getList(start, end); // 해당 페이지에 맞는 게시물 불러오는 메서드
//		
//		request.setAttribute("startPage", startPage);
//		request.setAttribute("endPage", endPage);
//		request.setAttribute("list", list);
//		request.setAttribute("totalExhCount", totalExhCount);
//		request.setAttribute("currentPageNum", currentPageNum);
//		request.setAttribute("countPage", countPage);
//		request.getRequestDispatcher("/user/list.jsp").forward(request, response);
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//	}
//
//}
