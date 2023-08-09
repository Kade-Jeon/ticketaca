package com.ticketaca.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ticketaca.dao.AdminDAO;
import com.ticketaca.dao.OrderDAO;
import com.ticketaca.vo.AdminVO;
import com.ticketaca.vo.ExhVO;
import com.ticketaca.vo.OrderVO;
import com.ticketaca.vo.QnaVO;
import com.ticketaca.vo.UserVO;

public class AdminService {
	AdminDAO adminDAO;
	OrderDAO orderDAO;
	AdminDAO adminqnaDAO;
	
	public AdminService() {
		adminDAO = new AdminDAO();
		orderDAO = new OrderDAO();
	}
	
	/*총명*/
	public AdminVO loginAdmin(String adminId, String adminPw) {
		/*관리자 로그인 기능*/	
		AdminVO adminInfo = adminDAO.loginAdmin(adminId, adminPw);
		return adminInfo;
	}//loginAdmin() END
	
	public ArrayList<UserVO> selectAllUsers () {
		/*전체 유저 조회 기능*/	
		ArrayList<UserVO> usersInfo = adminDAO.selectAllUsers();
		return usersInfo;
	}//selectAllUsers() END
	
	public ArrayList<UserVO> searchUser(String condition, String keyword){
		/*유저 검색 기능*/	
		ArrayList<UserVO> searchList = adminDAO.searchUser(condition, keyword);
		return searchList;
	}//searchUser() END
	
	public ArrayList<OrderVO> searchUserOrder(String condition, String keyword) {
		ArrayList<OrderVO> searchList = adminDAO.searchUserOrder(condition, keyword);
		return searchList;
	}// searchUserOrder() END
	
	
	
	/*기철*/
	public ArrayList<OrderVO> orderAllList(){
		/*전체 유저 주문목록 조회*/
		ArrayList<OrderVO> orderAllList = orderDAO.orderAllList();
		return orderAllList;
	}//orderAllList() END
	
	public ArrayList<OrderVO> searchOrder(String condition, String keyword){
		ArrayList<OrderVO> searchList = orderDAO.searchOrder(condition, keyword);
		return searchList;
	}//searchOrder() END
	
	
	/*민지*/
	// 전체 전시 리스트 출력
		public List<ExhVO> listExhs() {
			List<ExhVO> exhsList = adminDAO.listExhs();
			return exhsList;
		}
			
		public Map listExhs(Map<String, Integer> pagingMap) {
			Map exhsMap = new HashMap();
			List<ExhVO> exhsList = adminDAO.listExhs(pagingMap);
			
			int totalExhs = adminDAO.selectTotalExhs();	// 총 전시목록 수
			
			exhsMap.put("exhsList", exhsList);
			exhsMap.put("totalExhs", totalExhs);
				
			return exhsMap;
		}
		
		// 진행 중 전시 리스트 출력
		public List<ExhVO> listExhsOngoing() {
			List<ExhVO> exhsListOngoing = adminDAO.listExhsOngoing();
			
			System.out.println(exhsListOngoing);
			return exhsListOngoing;
		}
		
		// 마감 임박 전시 리스트 출력
		public List<ExhVO> listExhsNearingDeadline() {
			List<ExhVO> exhsListNearingDeadline = adminDAO.listExhsNearingDeadline();
			return exhsListNearingDeadline;
		}
				
		// 예정 전시 리스트 출력
		public List<ExhVO> listExhsUpcoming() {
			List<ExhVO> exhsListUpcoming = adminDAO.listExhsUpcoming();
			return exhsListUpcoming;
		}
			
		// 종료 전시 리스트 출력
		public List<ExhVO> listExhsClosing() {
			List<ExhVO> exhsListClosing = adminDAO.listExhsClosing();
			return exhsListClosing;
		}
			
		// 전시 세부정보 출력
		public List<ExhVO> listExhsDetail(int exhNo) {
			List<ExhVO> exhsListDetail = adminDAO.listExhsDetail(exhNo);
			return exhsListDetail;
		}
		
		// 신규 전시 등록
		public int addExh(ExhVO exhVO) {
			return adminDAO.addExh(exhVO);
		}
		
		// 전시 세부 정보 출력 (수정을 위한) #################
		public ExhVO viewExh(int exhNO) {
			ExhVO exh = null;
			exh = adminDAO.selectExh(exhNO);
			return exh;
		}
	
		// 전시 정보 수정 ###############
		public void modExh(ExhVO exh) {
			adminDAO.modExh(exh);
		}
	
		// 전시 정보 삭제 ################
		public void deleteExh(int exhNO) {
			adminDAO.deleteExh(exhNO);
		}
		
		public Map mainSearch(Map<String, Integer> pagingMap, String keyword) {
			Map exhsMap = new HashMap();
			List<ExhVO> exhsList = adminDAO.mainSearch(pagingMap, keyword);
			
			int totalExhs = adminDAO.selectTotalExhs();	// 총 전시목록 수
			System.out.println("totalExhstotalExhstotalExhs : " + totalExhs);
			exhsMap.put("exhsList", exhsList);
			exhsMap.put("totalExhs", totalExhs);
			
			return exhsMap;
		}
		
		/*호준*/
		public List<QnaVO> selectAll() {
			List<QnaVO> adminlistQna = adminDAO.selectAll();
			return adminlistQna;
		}
		
		public List<QnaVO> selectAll(int Qno) {
			List<QnaVO> adminlistQna = adminDAO.selectAll(Qno);
			
			return adminlistQna;
		}
		
		public List<QnaVO> downreplyQna(int Qno) {
			List<QnaVO> adminselectPno = adminDAO.downreplyQna(Qno);
			
			return adminselectPno;
		}
		
		public List<QnaVO> admininsertQna(String category,String title,String id,String content,int Qno) {
			adminDAO.insertadminQna(category,title,id,content,Qno);
			adminDAO.updateinfo(Qno);
			List<QnaVO> adminlistQna = adminDAO.selectAll();
			return adminlistQna;
		}
		

}//Class END
