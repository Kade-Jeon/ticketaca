package com.ticketaca.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ticketaca.dao.OrderDAO;
import com.ticketaca.dao.UserDAO;
import com.ticketaca.vo.ExhVO;
import com.ticketaca.vo.OrderVO;
import com.ticketaca.vo.QnaVO;
import com.ticketaca.vo.UserVO;
import com.ticketaca.vo.ZzimVO;

public class UserService {
	UserDAO userDAO;
	OrderDAO orderDAO;
	
	
	public UserService() {
		userDAO = new UserDAO();
		orderDAO = new OrderDAO();
		
	}
	
	public void signIn(UserVO userVO) {
	/*유저 회원가입 기능*/	
		userDAO.addUser(userVO);
	}//signIn() END 
	
	public boolean duplicatedId(String userId) {
		/*회원가입시 아이디 중복확인 기능*/	
		boolean result = userDAO.duplicatedId(userId);
		return result;
	}//duplicatedId() END 
	
	public UserVO loginUser(String userId, String userPw) {
		/*유저 로그인 기능*/	
		UserVO userInfo = userDAO.loginUser(userId, userPw);
		return userInfo;
	}//loginUser() END
	
	public void userOut(String userId) {
		/*유저 탈퇴 기능*/
		userDAO.userOut(userId);
	}//userOut() END
	
	
	public UserVO selectOne(String userId) {
		/*유저 1명 조회*/
		UserVO userInfo = userDAO.selectOne(userId);
		return userInfo;
	}//selecOne() END
	
	public void userUpdate(UserVO userVO) {
		/*마이페이지 - 유저 정보 업데이트*/
		userDAO.userUpdate(userVO);
	}//userUpdate() END
	
	public ArrayList<ZzimVO> zzimList(String userId){
		/*찜목록 조회*/
		ArrayList<ZzimVO> zzimList = null;
		zzimList = userDAO.zzimList(userId);
		return zzimList;
	}//zzimList() END
	
	public ArrayList<ZzimVO> removeZzim(String userId, int zzimNo){
		/*선택한 상품 찜목록에서 삭제 후 리스트 결과 재반환*/
		
		/*선택한 상품 찜목록 삭제*/
		userDAO.removeZzim(userId, zzimNo);
		/*찜목록 재조회*/
		ArrayList<ZzimVO> zzimList = null;
		zzimList = userDAO.zzimList(userId);
		return zzimList;
	}//removeZzim() END
	
	/*기철*/
	public List<OrderVO> userOrderList(String userId){
		/* 유저의 결제 정보 조회*/
		System.out.println("service : "+ userId);
		// 테스트 코드
		
		List<OrderVO> userOrderList = orderDAO.getUserOrders(userId);
		System.out.println("userOrderList"+userOrderList); // 테스트 코드
		
		return userOrderList;
	}// userOrderList END
	
	public void userOrderAdd(OrderVO orderVO){
		/* 유저의 결제 정보 추가*/
		orderDAO.insertNewOrder(orderVO);
		
		
	}
	
	/*민지*/
	// 전체 전시 리스트 출력
		public List<ExhVO> listExhs() {
			List<ExhVO> exhsList = userDAO.listExhs();
			return exhsList;
		}
		
		public Map listExhs(Map<String, Integer> pagingMap) {
			Map exhsMap = new HashMap();
			List<ExhVO> exhsList = userDAO.listExhs(pagingMap);
			
			int totalExhs = userDAO.selectTotalExhs();	// 총 전시목록 수
			System.out.println("totalExhstotalExhstotalExhs : " + totalExhs);
			exhsMap.put("exhsList", exhsList);
			exhsMap.put("totalExhs", totalExhs);
			
			return exhsMap;
		}
		
		// 진행 중 전시 리스트 출력
		public List<ExhVO> listExhsOngoing() {
			List<ExhVO> exhsListOngoing = userDAO.listExhsOngoing();
			return exhsListOngoing;
		}
		
		// 마감 임박 전시 리스트 출력
		public List<ExhVO> listExhsNearingDeadline() {
			List<ExhVO> exhsListNearingDeadline = userDAO.listExhsNearingDeadline();
			return exhsListNearingDeadline;
		}
			
		// 예정 전시 리스트 출력
		public List<ExhVO> listExhsUpcoming() {
			List<ExhVO> exhsListUpcoming = userDAO.listExhsUpcoming();
			return exhsListUpcoming;
		}
		
		// 종료 전시 리스트 출력
		public List<ExhVO> listExhsClosing() {
			List<ExhVO> exhsListClosing = userDAO.listExhsClosing();
			return exhsListClosing;
		}
		
		// 전시 세부정보 출력 + 기철 결제하기(userPay.do) 도 사용 합니다.
		public List<ExhVO> listExhsDetail(int exhNo) {
			List<ExhVO> exhsListDetail = userDAO.listExhsDetail(exhNo);
			return exhsListDetail;
		}
		
		public Map mainSearch(Map<String, Integer> pagingMap, String keyword) {
			Map exhsMap = new HashMap();
			List<ExhVO> exhsList = userDAO.mainSearch(pagingMap, keyword);
			
			int totalExhs = userDAO.selectTotalExhs();	// 총 전시목록 수
			System.out.println("totalExhstotalExhstotalExhs : " + totalExhs);
			exhsMap.put("exhsList", exhsList);
			exhsMap.put("totalExhs", totalExhs);
			
			return exhsMap;
		}
		
		/*호준*/
		public List<QnaVO> listQna(String log) {
			List<QnaVO> listQna = userDAO.selectTree(log);

			return listQna;
		}
			
		public List<QnaVO> writeQna(String category, String title, String content, String log) {
			userDAO.writeQna(category, title, content, log);

			List<QnaVO> listQnaUser = userDAO.selectTree(log);
			
			return listQnaUser;
		}
		
		public List<QnaVO> listOriginalQna(String log,int no){
			List<QnaVO> listOriginalQna = userDAO.selectTree(log,no);
			return listOriginalQna;
		}
		
		public List<QnaVO> updateQna(String title,String category,String content,String log, int no ){
			userDAO.updateQna(title, category, content,log, no);
			List<QnaVO> listQna = userDAO.selectTree(log);
			
			return listQna;
		}
		
	
}
