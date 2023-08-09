package com.ticketaca.control;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import com.ticketaca.service.AdminService;
import com.ticketaca.vo.AdminVO;
import com.ticketaca.vo.ExhVO;
import com.ticketaca.vo.OrderVO;
import com.ticketaca.vo.QnaVO;
import com.ticketaca.vo.UserVO;

@WebServlet("/admin/*")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String exhImageRepo = "C:\\ticket\\exh_image";
	AdminService adminService;
	ExhVO exhVO = null;
	
	public void init(ServletConfig config) throws ServletException {
		adminService = new AdminService();
		exhVO = new ExhVO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = "";
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session;
		String action = request.getPathInfo();
		System.out.println("action:" + action);
		
		// 페이징
				String _section = request.getParameter("section");
				String _pageNum = request.getParameter("pageNum");
				int section = Integer.parseInt(((_section == null) ? "1" : _section));
				int pageNum = Integer.parseInt(((_pageNum == null) ? "1" : _pageNum));
							
				Map<String, Integer> pagingMap = new HashMap<String, Integer>();
				pagingMap.put("section", section);
				pagingMap.put("pageNum", pageNum);
		
		try {
			
			List<ExhVO> exhsList = new ArrayList<ExhVO>();	// 전체 전시 리스트
			List<ExhVO> exhsListOngoing = new ArrayList<ExhVO>();	// 진행 중 전시 리스트
			List<ExhVO> exhsListNearingDeadline = new ArrayList<ExhVO>();	// 마감 임박 전시 리스트
			List<ExhVO> exhsListUpcoming = new ArrayList<ExhVO>();	// 예정 전시 리스트
			List<ExhVO> exhsListClosing = new ArrayList<ExhVO>();	// 종료 전시 리스트
			List<ExhVO> exhsListDetail = new ArrayList<ExhVO>();	// 전시 세부정보 리스트
			List<QnaVO> adminlistQna = new ArrayList<QnaVO>();
			List<QnaVO> adminselectPno = new ArrayList();
			
			if(action.equals("/login.do")) {
				/*로그인 버튼 누를 시*/
				System.out.println(action);
				nextPage = "/admins/adminSign/login.jsp";
				
			}else if(action.equals("/loginAdmin.do")){
				/*id/pw 입력후 로그인버튼 누를 시*/
				System.out.println(action);
				String adminId = request.getParameter("adminId");
				String adminPw = request.getParameter("adminPw");
				
				AdminVO adminInfo = adminService.loginAdmin(adminId, adminPw);
				System.out.println(adminInfo);
				if(adminInfo == null) {
					request.setAttribute("msg", "loginFail");
					nextPage = "/admins/adminSign/login.jsp";
					
				} else {
					session = request.getSession();
					session.setAttribute("adm", adminInfo);
					
					request.setAttribute("msg", "loginSuccess");
					nextPage = "../admins/adminMain.jsp";
				}
			
			}else if(action.equals("/userPage.do")){
				/*관리자 화면에서 유저페이지 버튼 누를시*/
				session = request.getSession();
				session.invalidate();
				nextPage = "../userMain.jsp";
				
			}else if(action.equals("/userList.do")){
				/*관리자 화면에서 유저관리 버튼 누를시*/
				System.out.println(action);
				session = request.getSession();
				
				ArrayList<UserVO> usersInfo = adminService.selectAllUsers();
				request.setAttribute("usersInfo", usersInfo);
				System.out.println(usersInfo);
				nextPage = "../admins/userManage/userList.jsp";	
				
				
			}else if(action.equals("/searchUser.do")){
				/*관리자 화면에서 검색 시*/
				String condition = request.getParameter("condition");
				String keyword = request.getParameter("keyword");
				
				ArrayList<UserVO> searchUser = adminService.searchUser(condition, keyword);
				request.setAttribute("searchList", searchUser);
				
				nextPage="../admins/userManage/searchList.jsp";
			
				
			}else if(action.equals("/orderAllList.do")){
				/*관리자 화면에서 주문조회 시*/
				ArrayList<OrderVO> orderAllList = adminService.orderAllList();
				request.setAttribute("orderAllList", orderAllList);
				System.out.println(orderAllList);
				
				nextPage="../admins/payManage/orderAllList.jsp";	
				
			} else if(action.equals("/searchOrder.do")){
				/*관리자 화면에서 주문검색 시*/
				String condition = request.getParameter("condition");
				String keyword = request.getParameter("keyword");
				ArrayList<OrderVO> searchOrderList = adminService.searchUserOrder(condition, keyword);
				request.setAttribute("searchOrderList", searchOrderList);
				
				nextPage="../admins/payManage/orderSearchList.jsp";		
				
				
			// 전체 전시 리스트 출력
			} else if(action.equals("/logout.do")) {
				/*로그아웃 버튼 누를 시*/
				request.setAttribute("msg", "logout");
				session = request.getSession();
				session.invalidate();
				nextPage = "../admins/adminMain.jsp";
			
				// 전체 전시 리스트 출력
			} else if (action.equals("/listExhs.do")) {
				Map exhsMap = adminService.listExhs(pagingMap);
				exhsMap.put("section", section);
				exhsMap.put("pageNum", pageNum);
				request.setAttribute("exhsMap", exhsMap);

				nextPage = "/admins/exhManage/listExhs.jsp";
			
			// 진행 중 전시 리스트 출력
			} else if(action.equals("/listExhsOngoing.do")) {
				exhsListOngoing = adminService.listExhsOngoing();
				request.setAttribute("exhsListOngoing", exhsListOngoing);
				nextPage = "/admins/exhManage/listExhsOngoing.jsp";
				
			// 마감 임박 전시 리스트 출력
			} else if(action.equals("/listExhsNearingDeadline.do")) {
				exhsListNearingDeadline = adminService.listExhsNearingDeadline();
				request.setAttribute("exhsListNearingDeadline", exhsListNearingDeadline);
				nextPage = "/admins/exhManage/listExhsNearingDeadline.jsp";
				
			// 예정 전시 리스트 출력	
			} else if(action.equals("/listExhsUpcoming.do")) {
				exhsListUpcoming = adminService.listExhsUpcoming();
				request.setAttribute("exhsListUpcoming", exhsListUpcoming);
				nextPage = "/admins/exhManage/listExhsUpcoming.jsp";
			
			// 종료 전시 리스트 출력
			} else if(action.equals("/listExhsClosing.do")) {
				exhsListClosing = adminService.listExhsClosing();
				request.setAttribute("exhsListClosing", exhsListClosing);
				nextPage = "/admins/exhManage/listExhsClosing.jsp";
				
			// 전시 세부정보 출력
			} else if(action.equals("/exhDetail.do")) {
				String exhNo = request.getParameter("no");
				// exhNo 는 URL을 타고 오므로 문자열 형태로 전송이 된다
				// 그런데 DB에는 int 로 선언되어있으므로 형변환 필요
				exhsListDetail = adminService.listExhsDetail(Integer.parseInt(exhNo));
				
				System.out.println(exhsListDetail.get(0).getImage());
				
				request.setAttribute("exhsListDetail", exhsListDetail);
				nextPage = "/admins/exhManage/exhDetail.jsp";
					
			// 신규 등록 폼으로 이동
			} else if(action.equals("/exhAddForm.do")) {
				nextPage = "/admins/exhManage/exhAddForm.jsp";
				
			// 전시 신규 등록
			} else if (action.equals("/addExh.do")) {
				// 파일 업로드 기능을 사용하기 위해 upload()로 요청을 전달
				/** doHandle(request,) 값을 받아서 upload() 메서드로 전달 **/
				
				int exhNO = 0;
				Map<String, String> exhMap = upload(request, response);

				// exhMap에 저장된 글 정보를 다시 가져옴
				String name = exhMap.get("name");
				String place = exhMap.get("place");
				String address = exhMap.get("address");
				String strDate = exhMap.get("strDate");
				String endDate = exhMap.get("endDate");
				int adultPrice = Integer.parseInt(exhMap.get("adultPrice"));
				int youthPrice = Integer.parseInt(exhMap.get("youthPrice"));
				int childPrice = Integer.parseInt(exhMap.get("childPrice"));
				String content = exhMap.get("content");
				int maxCnt = Integer.parseInt(exhMap.get("maxCnt"));
				String exhStatus = exhMap.get("exhStatus");
				String postStatus = exhMap.get("postStatus");
				String image = exhMap.get("image");
				String detailImage = exhMap.get("detailImage");		
				
				exhVO = new ExhVO();
				exhVO.setName(name);
				System.out.println(1);
				exhVO.setPlace(place);
				System.out.println(2);
				exhVO.setAddress(address);
				exhVO.setStrDate(strDate);
				exhVO.setEndDate(endDate);
				exhVO.setAdultPrice(adultPrice);
				exhVO.setYouthPrice(youthPrice);
				exhVO.setChildPrice(childPrice);
				exhVO.setContent(content);
				exhVO.setMaxCnt(maxCnt);
				exhVO.setExhStatus(exhStatus);
				exhVO.setPostStatus(postStatus);
				exhVO.setImage(image);
				exhVO.setDetailImage(detailImage);

				adminService = new AdminService();
				
				exhNO = adminService.addExh(exhVO);
				
				System.out.println(exhNO);

				// 대표이미지만 있을 때
				if((image != null && image.length() != 0) && (detailImage == null && detailImage.length() == 0)) {
					File srcFile = new File(exhImageRepo + "\\" + "temp" + "\\" + image);
					File destDir = new File(exhImageRepo + "\\" + exhNO);
					destDir.mkdirs();
														
					FileUtils.moveFileToDirectory(srcFile, destDir, true);	
				
				// 상세이미지만 있을 때
				} else if((detailImage != null && detailImage.length() != 0) && (image == null && image.length() == 0)) {
					File srcFile1 = new File(exhImageRepo + "\\" + "temp" + "\\" + detailImage);
					File destDir1 = new File(exhImageRepo + "\\" + exhNO);
					destDir1.mkdirs();
					FileUtils.moveFileToDirectory(srcFile1, destDir1, true);
				
				// 대표이미지와 상세이미지가 둘 다 있을 때
				} else if ((image != null && image.length() != 0) && (detailImage != null && detailImage.length() != 0)) {
					File srcFile = new File(exhImageRepo + "\\" + "temp" + "\\" + image);
					File srcFile1 = new File(exhImageRepo + "\\" + "temp" + "\\" + detailImage);
					
					File destDir = new File(exhImageRepo + "\\" + exhNO);
					File destDir1 = new File(exhImageRepo + "\\" + exhNO);
					
					destDir.mkdirs();
					destDir1.mkdirs();
														
					FileUtils.moveFileToDirectory(srcFile, destDir, true);														
					FileUtils.moveFileToDirectory(srcFile1, destDir1, true);
				}

				// 글 등록 메시지를 나타낸 후 글 목록을 요청
				PrintWriter pw = response.getWriter();
				pw.print("<script>" + " alert('새 전시를 등록했습니다.');" 
						+ "location.href='" + request.getContextPath() 
						+ "/admin/listExhs.do';</script>");
				
				return;
			
			// 세부 정보를 가지고 수정창으로 이동
			} else if(action.equals("/viewExh.do")) {
				int exhNO = Integer.parseInt(request.getParameter("no"));
				exhVO = adminService.viewExh(exhNO);
				request.setAttribute("exh", exhVO);
				System.out.println("/viewExh.do : exhNO ----> " + exhVO);
				nextPage = "/admins/exhManage/exhModForm.jsp";
					
			// 전시 세부항목 수정 ---> 다시 확인
			} else if(action.equals("/modExh.do")) {
				Map<String, String> exhMap = upload(request, response);
				
				String exhNO = exhMap.get("no");	// JSP 에서 가져오는 파라미터명이 no이니 맞춰주어야함
				System.out.println("?? : " + exhNO);//null
				//exhVO.setNo(Integer.parseInt(exhNO));
				exhVO.setNo(Integer.parseInt(exhMap.get("no")));
				
				String name = exhMap.get("name");
				String place = exhMap.get("place");
				String address = exhMap.get("address");
				String strDate = exhMap.get("strDate");
				String endDate = exhMap.get("endDate");
				int adultPrice = Integer.parseInt(exhMap.get("adultPrice"));
				int youthPrice = Integer.parseInt(exhMap.get("youthPrice"));
				int childPrice = Integer.parseInt(exhMap.get("childPrice"));
				String content = exhMap.get("content");
				int maxCnt = Integer.parseInt(exhMap.get("maxCnt"));
				String exhStatus = exhMap.get("exhStatus");
				String postStatus = exhMap.get("postStatus");

				exhVO.setName(name);
				exhVO.setPlace(place);
				exhVO.setAddress(address);
				exhVO.setStrDate(strDate);
				exhVO.setEndDate(endDate);
				exhVO.setAdultPrice(adultPrice);
				exhVO.setYouthPrice(youthPrice);
				exhVO.setChildPrice(childPrice);
				exhVO.setContent(content);
				exhVO.setMaxCnt(maxCnt);
				exhVO.setExhStatus(exhStatus);
				exhVO.setPostStatus(postStatus);

				adminService.modExh(exhVO);	// 전송된 전시 정보를 이용해서 수정
				
				PrintWriter pw = response.getWriter();
				// 글 수정 후 location 객체의 href 속성을 이용해 상세 화면으로 이동
				pw.print("<script>" + "  alert('전시 정보를 수정했습니다.');" + " location.href='" + request.getContextPath()
				+ "/admin/exhDetail.do?no=" + exhNO + "';" + "</script>");
				
				return;
				
			} else if(action.equals("/deleteExh.do")) {
				
				int exhNO = Integer.parseInt(request.getParameter("no"));
				
				adminService.deleteExh(exhNO);
								
				// 글에 첨부된 이미지가 있으면 해당 파일을 삭제
				File imgDir = new File(exhImageRepo + "\\" + exhNO);
				if(imgDir.exists()) {
					FileUtils.deleteDirectory(imgDir);
				}
				
				PrintWriter pw = response.getWriter();
				pw.print("<script>" + "  alert('전시 정보를 삭제했습니다.');" + " location.href='" + request.getContextPath()
				+ "/admin/listExhs.do';" + "</script>");
				
				return;
			
				/*호준*/	
				// 	자기가 쓴 QnA만 볼 수 있음
			} else if(action.equals("/Qnas/adminlistQna.do")) {
						
				adminlistQna = adminService.selectAll();
				request.setAttribute("adminlistQna", adminlistQna);
				nextPage = "/admins/qnaManage/adminlistQna.jsp";
						
			} else if(action.equals("/Qnas/adminlistOriginalQna.do")) {	//상세정보 페이지 넘기기
				int Qno = Integer.parseInt(request.getParameter("Qno"));
						
				adminlistQna = adminService.selectAll(Qno);
				adminselectPno = adminService.downreplyQna(Qno);
						
				request.setAttribute("adminlistQna", adminlistQna);
				request.setAttribute("adminselectPno", adminselectPno);
						
				nextPage = "/admins/qnaManage/adminlistOriginalQna.jsp";
					
			} else if(action.equals("/Qnas/admininsertList.do")) {	//댓글 작성하기 페이지로 보내기
				int Qno = Integer.parseInt(request.getParameter("A_Qno"));
						
				adminlistQna = adminService.selectAll(Qno);
				adminselectPno = adminService.downreplyQna(Qno);
						
				request.setAttribute("adminlistQna", adminlistQna);
				request.setAttribute("adminselectPno", adminselectPno);
						
				nextPage = "/admins/qnaManage/admininsertWrite.jsp";
						
			} else if(action.equals("/Qnas/admininsertQna.do")) {
				String title = request.getParameter("A_title");
				int Qno = Integer.parseInt(request.getParameter("A_Qno"));
				String content = request.getParameter("C_content");
				String id = request.getParameter("A_Uid");
				String category = request.getParameter("A_category");
				adminlistQna = adminService.admininsertQna(category,title,id,content,Qno);
				System.out.println("@!#!@#@!!#@"+adminlistQna);
				request.setAttribute("adminlistQna",adminlistQna);
				nextPage="/admins/qnaManage/adminlistQna.jsp";
					
			} else if(action.equals("/mainSearch.do")) {
				String keyword = request.getParameter("keyword");
				Map exhsMap = adminService.mainSearch(pagingMap, keyword);
				exhsMap.put("section", section);
				exhsMap.put("pageNum", pageNum);
						
						
				request.setAttribute("exhsMap", exhsMap);
				nextPage = "/admins/exhManage/listExhs.jsp";	
				
			} else if(action.equals("/mainSearch.do")) {
				String keyword = request.getParameter("keyword");
				Map exhsMap = adminService.mainSearch(pagingMap, keyword);
				exhsMap.put("section", section);
				exhsMap.put("pageNum", pageNum);
				
				
				request.setAttribute("exhsMap", exhsMap);
				nextPage = "/admins/exhManage/listExhs.jsp";	
			}			
			else{
				nextPage ="../admins/adminMain.jsp";
			}
				
	
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//doHandle END
	
	
	
	
	
		private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			Map<String, String> exhMap = new HashMap<String, String>();
			String encoding = "UTF-8";
			
			// exhImageRepo : 업로드 저장 폴더 (C:\\ticket\\exh_image)
			File currentDirPath = new File(exhImageRepo);	// 글 이미지 저장 폴더에 대한 파일 객체를 생성
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setRepository(currentDirPath);	// (C:\\ticket\\exh_image)
			factory.setSizeThreshold(1024 * 1024);	// 업로드 크기 : 1024KB
			
			// ServletFileUpload : 업로드 전체 파일 크기 및 개별 파일의 크기 설정 
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			try {
				List<FileItem> items = upload.parseRequest(request);
				
				for (int i = 0; i < items.size(); i++) {
					FileItem fileItem = (FileItem) items.get(i);
					
					if (fileItem.isFormField()) {
						System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
						exhMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
						
					} else {
						System.out.println("파라미터명: " + fileItem.getFieldName());
						System.out.println("파일명: " + fileItem.getName());
						System.out.println("파일크기: " + fileItem.getSize() + "bytes");
						
						// 업로드한 파일이 존재하는 경우 업로드한 파일의 파일 이름으로 저장소에 업로드
						if (fileItem.getSize() > 0) {
							int idx = fileItem.getName().lastIndexOf("\\");
							if (idx == -1) {
								idx = fileItem.getName().lastIndexOf("/");
							}

							String fileName = fileItem.getName().substring(idx + 1);
							System.out.println("파일명: " + fileName);
							exhMap.put(fileItem.getFieldName(), fileName);
							
							// 첨부한 파일을 먼저 temp 폴더에 업로드
							File uploadFile = new File(currentDirPath + "\\" + "temp" + "\\" + fileName);
							fileItem.write(uploadFile);	// 파일 업로드
						} // end if
					} // end if
				} // end for
			} catch (Exception e) {
				System.out.println("upload() ERR : " + e.getMessage());
			}
			return exhMap;
		}//upload() END

}
