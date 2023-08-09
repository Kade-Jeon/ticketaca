package com.ticketaca.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ticketaca.service.UserService;
import com.ticketaca.vo.ExhVO;
import com.ticketaca.vo.OrderVO;
import com.ticketaca.vo.QnaVO;
import com.ticketaca.vo.UserVO;
import com.ticketaca.vo.ZzimVO;

@WebServlet("/user/*")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String exhImageRepo = "C:\\ticket\\exh_image";
	UserService userService;
	ExhVO exhVO;
	OrderVO orderVO;
	

	public void init() throws ServletException {
		userService = new UserService();
		exhVO = new ExhVO();
		orderVO = new OrderVO();
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = "";
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session;
		String action = request.getPathInfo();
		System.out.println("action:" + action);
		try {
			
			/*민지*/
			List<ExhVO> exhsList = new ArrayList<ExhVO>();	// 전체 전시 리스트
			List<ExhVO> exhsListOngoing = new ArrayList<ExhVO>();	// 진행 중 전시 리스트
			List<ExhVO> exhsListNearingDeadline = new ArrayList<ExhVO>();	// 마감 임박 전시 리스트
			List<ExhVO> exhsListUpcoming = new ArrayList<ExhVO>();	// 예정 전시 리스트
			List<ExhVO> exhsListClosing = new ArrayList<ExhVO>();	// 종료 전시 리스트
			List<ExhVO> exhsListDetail = new ArrayList<ExhVO>();	// 전시 세부정보 리스트
			List<QnaVO> qnaList = new ArrayList<QnaVO>();
			
			// 페이징
			String _section = request.getParameter("section");
			String _pageNum = request.getParameter("pageNum");
			int section = Integer.parseInt(((_section == null) ? "1" : _section));
			int pageNum = Integer.parseInt(((_pageNum == null) ? "1" : _pageNum));
			
			Map<String, Integer> pagingMap = new HashMap<String, Integer>();
			pagingMap.put("section", section);
			pagingMap.put("pageNum", pageNum);
			
			if (action.equals("/signUp.do")) {
				/* 회원가입 버튼 누를 시 */
				nextPage = "/users/sign/sign.jsp";

			} else if (action.equals("/signUpUser.do")) {
				/* 회원가입 폼 입력 후 가입하기 버튼 누를 시 */
				System.out.println(action);
				String userId = request.getParameter("userId");
				System.out.println(userId);
				String userPw = request.getParameter("userPw");
				String userName = request.getParameter("userName");
				Date userBirth = java.sql.Date.valueOf(request.getParameter("userBirth"));
				String userTel = request.getParameter("userTel");
				String userEmail = request.getParameter("userEmail");
				UserVO userVO = new UserVO(userId, userPw, userName, userBirth, userTel, userEmail);
				userService.signIn(userVO);

				request.setAttribute("msg", "signUpUser");
				nextPage = "/userMain.jsp";

			} else if (action.equals("/login.do")) {
				/* 로그인 버튼 누를 시 */
				System.out.println(action);
				nextPage = "/users/sign/login.jsp";

			} else if (action.equals("/loginUser.do")) {
				/* id/pw 입력후 로그인버튼 누를 시 */
				System.out.println(action);
				String userId = request.getParameter("userId");
				String userPw = request.getParameter("userPw");
				UserVO userInfo = userService.loginUser(userId, userPw);

				if (userInfo == null) {
					request.setAttribute("msg", "loginFail");
					nextPage = "/users/sign/login.jsp";

				} else if (userInfo.getUserOut().equals("T")) {
					request.setAttribute("msg", "userOut");
					nextPage = "/userMain.jsp";
					
				} else {
					session = request.getSession();
					session.setAttribute("user", userInfo);

					request.setAttribute("msg", "loginSuccess");
					nextPage = "/userMain.jsp";
				}

			} else if (action.equals("/userOut.do")) {
				/* 회원 탈퇴 처리 */
				System.out.println(action);
				
				session = request.getSession();
				UserVO userInfo =  (UserVO) session.getAttribute("user");
				String userId = userInfo.getUserId();
				userService.userOut(userId);
				session.invalidate();

				request.setAttribute("msg", "userOutSuccess");
				nextPage = "/userMain.jsp";

			} else if (action.equals("/checkId.do")) {
				/* 회원가입 폼, 중복확인 버튼 누를 시 */
				System.out.println(action);
				PrintWriter writer = response.getWriter();
				String userId = request.getParameter("userId");
				System.out.println(userId);
				boolean duplicatedId = userService.duplicatedId(userId);
				
				String result="usable";
				System.out.println(duplicatedId);
				if (duplicatedId == true) {
					System.out.println("not_usable");
					writer.print("not_usable");//
					result="not_usable";
				} else {
					System.out.println("usable");
					writer.print("usable");
					result="usable";
				}
				return;

			} else if (action.equals("/mypage.do")) {
				/* 마이페이지 버튼 누를 시 */
				System.out.println(action);
				nextPage = "/users/mypage/myPage.jsp";

			} else if (action.equals("/mypageCheck.do")) {
				/* 마이페이지 비밀번호 입력 후 확인 누를 시 */
				System.out.println(action);
				session = request.getSession();
				UserVO userVO = (UserVO) session.getAttribute("user");
				String userId = userVO.getUserId();
				UserVO userInfo = userService.selectOne(userId);
				request.setAttribute("userInfo", userInfo);
				nextPage = "/users/mypage/myPageMod.jsp";

			} else if (action.equals("/mypageUpdate.do")) {
				/* 마이페이지 수정 후 반영 */
				System.out.println(action);

				String userId = request.getParameter("userId");
				String userPw = request.getParameter("userPw");
				String userName = request.getParameter("userName");
				String userTel = request.getParameter("userTel");
				String userEmail = request.getParameter("userEmail");

				System.out.println(userId);
				System.out.println(userPw);
				System.out.println(userName);
				System.out.println(userTel);
				System.out.println(userEmail);

				UserVO userVO = new UserVO(userId, userPw, userName, userTel, userEmail);

				userService.userUpdate(userVO);

				request.setAttribute("msg", "UpdateSuccess");
				nextPage = "/users/mypage/myPage.jsp";

			} else if (action.equals("/logout.do")) {
				/* 로그아웃 버튼 누를 시 */
				request.setAttribute("msg", "logout");
				session = request.getSession();
				session.invalidate();
				nextPage = "/userMain.jsp";
				
			} else if (action.equals("/zzimList.do")) {
				/* 유저화면에서 찜목록 버튼 누를 시 */
				session = request.getSession();
				UserVO userInfo =  (UserVO) session.getAttribute("user");
				if(userInfo == null) {
					nextPage = "/userMain.jsp";
				}else {
				String userId = userInfo.getUserId();
				ArrayList<ZzimVO> zzimList = userService.zzimList(userId);
				request.setAttribute("zzimList", zzimList);
				nextPage = "../users/zzim/zzimList.jsp";
				}
				
			} else if (action.equals("/removezzim.do")) {
				/* 유저화면에서 찜 아이템 삭제할 시 */
				session = request.getSession();
				UserVO userInfo =  (UserVO) session.getAttribute("user");
				String userId = userInfo.getUserId();
				int zzimNo = Integer.parseInt(request.getParameter("no"));
				System.out.println(userId);
				System.out.println(zzimNo);
				userService.removeZzim(userId, zzimNo);
				
				ArrayList<ZzimVO> zzimList = userService.zzimList(userId);
				request.setAttribute("zzimList", zzimList);
				nextPage = "../users/zzim/zzimList.jsp";	
				
			/* 기철 */	
			} else if (action.equals("/userPay.do")) {
				/*상품 상세정보에서 예매하기 클릭 > 결제창 정보 넘겨주기*/
				System.out.println(action);
				
				String exhNo = request.getParameter("exhNo");
				System.out.println(exhNo);

				List<ExhVO> exhPay = userService.listExhsDetail(Integer.parseInt(exhNo));
				request.setAttribute("exhPay", exhPay);
				System.out.println(exhPay);
				
				nextPage = "../users/pay/payDetail.jsp";
				
			} else if (action.equals("/userOrderAdd.do")) {
				/* 상품 상세정보를 받아 주문목록 생성 */
				System.out.println(action);
				
				Date currentDate = null;
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				
				session = request.getSession();
				UserVO userPay =  (UserVO) session.getAttribute("user");
				//Integer.parseInt(request.getParameter("ordAQnt"));
				int exhNo =  Integer.parseInt(request.getParameter("exhNo"));
				String userId = userPay.getUserId();
				System.out.println(userId);
				String ordPay= "card";
				int ordAQnt = Integer.parseInt(request.getParameter("ordAQnt"));
				int ordYQnt = Integer.parseInt(request.getParameter("ordYQnt"));
				int ordCQnt = Integer.parseInt(request.getParameter("ordCQnt"));
				int ordPrice = Integer.parseInt(request.getParameter("ordPrice"));
				Date ordDate = currentDate;
				int seatNo = 0;
				String ordStatus = "T";
								
				OrderVO orderVO = new OrderVO(exhNo, userId, ordPay, ordAQnt, ordYQnt, ordCQnt, ordPrice, ordDate, seatNo, ordStatus);
				
				System.out.println(orderVO);
				
				userService.userOrderAdd(orderVO);
				
				List<OrderVO> userOrders = userService.userOrderList(userId);
				request.setAttribute("userOrders", userOrders);

				
				nextPage = "/users/pay/payList.jsp";
				
				
			} else if (action.equals("/userPayList.do")) {
				/* 유저의 결제내역 목록 */
				System.out.println(action); 
				
				session = request.getSession();
				UserVO userVO =  (UserVO) session.getAttribute("user");
				if (userVO == null) {
			        // 비 로그인시경고 메시지 표시
			        out.println("<script>alert('로그인이 필요합니다.');</script>");
			        // 로그인 페이지로 리다이렉트 or 메인으로 돌아가기 예정
			        response.sendRedirect("login_form.jsp");
			        return;
			    }
				String userId = userVO.getUserId();
				System.out.println(userId);
				
				//String userId = "one";	// 테스트 아이디 코드 추후 삭제
				
				List<OrderVO> userOrders = userService.userOrderList(userId);
				request.setAttribute("userOrders", userOrders);
				
				nextPage = "../users/pay/payList.jsp";
				
			} else if (action.equals("/adminPage.do")) {
				/* 유저화면에서 관리자페이지 버튼 누를 시 */
				session = request.getSession();
				session.invalidate();
				nextPage = "../admins/adminMain.jsp";
			
			} // 전체 전시 리스트 출력
			else if(action.equals("/listExhs.do")) {
				Map exhsMap = userService.listExhs(pagingMap);
				exhsMap.put("section", section);
				exhsMap.put("pageNum", pageNum);
				System.out.println("usercontroller .totalExhs : " + exhsMap.get("totalExhs"));
				request.setAttribute("exhsMap", exhsMap);
				
//				exhsList = userSerivice.listExhs();
//				request.setAttribute("exhsList", exhsList);
				nextPage = "/users/exh/listExhs.jsp";
			
			// 진행 중 전시 리스트 출력
			} else if(action.equals("/listExhsOngoing.do")) {
				exhsListOngoing = userService.listExhsOngoing();
				request.setAttribute("exhsListOngoing", exhsListOngoing);
				nextPage = "/users/exh/listExhsOngoing.jsp";
				
			// 마감 임박 전시 리스트 출력
			} else if(action.equals("/listExhsNearingDeadline.do")) {
				exhsListNearingDeadline = userService.listExhsNearingDeadline();
				request.setAttribute("exhsListNearingDeadline", exhsListNearingDeadline);
				nextPage = "/users/exh/listExhsNearingDeadline.jsp";
				
			// 예정 전시 리스트 출력	
			} else if(action.equals("/listExhsUpcoming.do")) {
				exhsListUpcoming = userService.listExhsUpcoming();
				request.setAttribute("exhsListUpcoming", exhsListUpcoming);
				nextPage = "/users/exh/listExhsUpcoming.jsp";
			
			// 종료 전시 리스트 출력
			} else if(action.equals("/listExhsClosing.do")) {
				exhsListClosing = userService.listExhsClosing();
				request.setAttribute("exhsListClosing", exhsListClosing);
				nextPage = "/users/exh/listExhsClosing.jsp";
				
			// 전시 세부정보 출력
			} else if(action.equals("/exhDetail.do")) {
				String exhNo = request.getParameter("no");
				// articleNO 는 URL을 타고 오므로 문자열 형태로 전송이 된다
				// 그런데 DB에는 int 로 선언되어있으므로 형변환 필요
				exhsListDetail = userService.listExhsDetail(Integer.parseInt(exhNo));
				System.out.println(exhNo);
				System.out.println(exhsListDetail.get(0).getImage());
			
				request.setAttribute("exhsListDetail", exhsListDetail);
				nextPage = "/users/exh/exhDetail.jsp";
			
			} else if(action.equals("/mainSearch.do")) {
				String keyword = request.getParameter("keyword");
				Map exhsMap = userService.mainSearch(pagingMap, keyword);
				exhsMap.put("section", section);
				exhsMap.put("pageNum", pageNum);
				
				
				request.setAttribute("exhsMap", exhsMap);
				nextPage = "/users/exh/listExhs.jsp";	
				
				/*호준*/
			} else if(action.equals("/UQnas/listQna.do")) {	//listQna.jsp로 넘겨줌
				session = request.getSession();
				UserVO userInfo =  (UserVO) session.getAttribute("user");
				String userId = userInfo.getUserId();
				qnaList = userService.listQna(userId);
				request.setAttribute("qnaList", qnaList);
				nextPage = "/users/qna/listQna.jsp";
				
			} else if(action.equals("/UQnas/writeQna.do")) {	//write.Qna.jsp로 넘겨줌
				String category = request.getParameter("category");
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				session = request.getSession();
				UserVO userInfo =  (UserVO) session.getAttribute("user");
				String userId = userInfo.getUserId();
//				String log1 = (String) session.getAttribute("login");
				
				qnaList=userService.writeQna(category, title, content, userId);
				request.setAttribute("qnaList", qnaList);
				
				nextPage = "/users/qna/listQna.jsp";
				
			} else if(action.equals("/UQnas/listOriginalQna.do")) {	//
				int no = Integer.parseInt(request.getParameter("no"));
				session = request.getSession();
				UserVO userInfo =  (UserVO) session.getAttribute("user");
				String userId = userInfo.getUserId();
//				String log1 = (String) session.getAttribute("login");

				qnaList = userService.listOriginalQna(userId,no);
				System.out.println(qnaList);
				request.setAttribute("qnaList", qnaList);
				nextPage = "/users/qna/listOriginalQna.jsp";
				
			} else if(action.equals("/UQnas/updateListQna.do")) {
				
				int no =Integer.parseInt(request.getParameter("O_no"));
				session = request.getSession();
				UserVO userInfo =  (UserVO) session.getAttribute("user");
				String userId = userInfo.getUserId();
//				String log1 = (String) session.getAttribute("login");
				
				qnaList = userService.listOriginalQna(userId,no);
				
				request.setAttribute("qnaList", qnaList);

				nextPage = "/users/qna/updateQna.jsp";
				
			} else if(action.equals("/UQnas/updateQna.do")) {
				String title = request.getParameter("O_title");
				String category = request.getParameter("O_category");
				String content = request.getParameter("O_content");
				int no =Integer.parseInt(request.getParameter("O_no"));
				session = request.getSession();
				UserVO userInfo =  (UserVO) session.getAttribute("user");
				String userId = userInfo.getUserId();
//				String log1 = (String) session.getAttribute("login");
				
				qnaList = userService.updateQna(title,category,content,userId,no);
				
				request.setAttribute("qnaList", qnaList);
				
				nextPage = "/users/qna/listQna.jsp";
			
			} else if(action.equals("/mainSearch.do")) {
				String keyword = request.getParameter("keyword");
				Map exhsMap = userService.mainSearch(pagingMap, keyword);
				exhsMap.put("section", section);
				exhsMap.put("pageNum", pageNum);
				
				
				request.setAttribute("exhsMap", exhsMap);
				nextPage = "/users/exh/listExhs.jsp";	
			
			} else {
				/* 이외 모든 요청시 --> 메인으로 */
				nextPage = "/userMain.jsp";
			}
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}// doHandle END

}
