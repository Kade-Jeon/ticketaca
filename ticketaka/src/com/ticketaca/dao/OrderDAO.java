package com.ticketaca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ticketaca.vo.OrderVO;
import com.ticketaca.vo.UserVO;

public class OrderDAO {
	private DataSource dataFactory;
	Connection conn;
	PreparedStatement pstmt;
	
	public OrderDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("DB연결 실패  ==>" + e.getMessage());
		}
	}//UserDAO 생성자 종료
	
	public ArrayList<OrderVO> orderAllList() {
		/* 기능: 전체 주문리스트 조회
		 * 사용: 관리자에서 전체 회원 주문관리 용
		 * */
		ArrayList<OrderVO> orderAllList = null;
		try {
			String query = "SELECT * FROM ORDERS o "
					+ "LEFT OUTER JOIN USERS u "
					+ "ON u.USER_ID = o.USER_ID "
					+ "LEFT OUTER JOIN EXH e "
					+ "ON e.EXH_NO = o.EXH_NO "
					+ "ORDER BY ORD_DATE DESC";
			
			conn = dataFactory.getConnection();
			System.out.println("작동쿼리: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.executeQuery();
			
			ResultSet rs = pstmt.executeQuery();
			orderAllList = new ArrayList<OrderVO>();
			while(rs.next()) {
				int ordNo = rs.getInt("ORD_NO");
				int exhNo = rs.getInt("EXH_NO");
				String userId = rs.getString("USER_ID");
				String ordPay = rs.getString("ORD_PAY");
				int ordAQnt = rs.getInt("ORD_AQNT");
				int ordYQnt = rs.getInt("ORD_YQNT");
				int ordCQnt = rs.getInt("ORD_CQNT");
				int ordPrice = rs.getInt("ORD_PRICE");
				Date ordDate = rs.getDate("ORD_DATE");
				int seatNo = rs.getInt("SEAT_NO");
				int ordTQnt = ordAQnt + ordYQnt + ordCQnt;
				String exhName = rs.getString("EXH_NAME");
				String userName = rs.getString("USER_NAME");
				String userTel = rs.getString("USER_TEL");
				Date exhStrDate = rs.getDate("EXH_STRDATE");
				Date exhEndDate = rs.getDate("EXH_ENDDATE");
				orderAllList.add(new OrderVO(ordNo,exhNo,userId,ordPay,ordAQnt,ordYQnt,ordCQnt,ordPrice,
						ordDate,seatNo,ordTQnt,exhName,userName,userTel,exhStrDate,exhEndDate));
			}
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("OrderDAO orderAllList() ERR :" + e.getMessage());
		}
		return orderAllList;
	}//orderAllList() END
	
	public ArrayList<OrderVO> searchOrder(String condition, String keyword) {
		/* 기능: 조건과 키워드를 받아와서 해당 조건에 키워드를 contains 하고 있는 값들을 전부 가져옵니다.
		 * 사용: 주문 관리페이지에서 조건에 따른 유저 검색
		 * */
		ArrayList<OrderVO> searchList = null;
		System.out.println(condition);
		System.out.println(keyword);
		try {
			String query = "SELECT * FROM ORDERS o "
					+ "LEFT OUTER JOIN USERS u "
					+ "ON u.USER_ID = o.USER_ID "
					+ "LEFT OUTER JOIN EXH e "
					+ "ON e.EXH_NO = o.EXH_NO "
					+ "WHERE ?"
					+ " LIKE ? "
					+ "ORDER BY ORD_DATE DESC";
			conn = dataFactory.getConnection();
			System.out.println("작동 쿼리문: " + query);
			/*쿼리문 잘 작성하였으나 이상하게 작동이 안됨 !!*/
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, condition);
			pstmt.setString(2, "%" + keyword + "%");
			System.out.println(condition);
			ResultSet rs = pstmt.executeQuery();
			
			searchList = new ArrayList<OrderVO>();
			while(rs.next()) {
				int ordNo = rs.getInt("ORD_NO");
				int exhNo = rs.getInt("EXH_NO");
				String userId = rs.getString("USER_ID");
				String ordPay = rs.getString("ORD_PAY");
				int ordAQnt = rs.getInt("ORD_AQNT");
				int ordYQnt = rs.getInt("ORD_YQNT");
				int ordCQnt = rs.getInt("ORD_CQNT");
				int ordPrice = rs.getInt("ORD_PRICE");
				Date ordDate = rs.getDate("ORD_DATE");
				int seatNo = rs.getInt("SEAT_NO");
				int ordTQnt = ordAQnt + ordYQnt + ordCQnt;
				String exhName = rs.getString("EXH_NAME");
				String userName = rs.getString("USER_NAME");
				String userTel = rs.getString("USER_TEL");
				Date exhStrDate = rs.getDate("EXH_STRDATE");
				Date exhEndDate = rs.getDate("EXH_ENDDATE");
				searchList.add(new OrderVO(ordNo,exhNo,userId,ordPay,ordAQnt,ordYQnt,ordCQnt,ordPrice,
						ordDate,seatNo,ordTQnt,exhName,userName,userTel,exhStrDate,exhEndDate));
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("AdminDAO selectAllUsers() ERR :" + e.getMessage());
		}
		return searchList;
	}//searchOrder() END
	
	
	public List<OrderVO> getUserOrders(String userId){
		/* 기능: userId를 받아 userId에 해당하는 orders 값을 가져옴
		 * 사용: 사용자 결제내역 페이지에서 내역목록 출력
		 * */
		System.out.println("OrderDAO : " + userId);
		// DAO 값 확인 테스트
		
		List<OrderVO> userOrders = new ArrayList<OrderVO>();
		
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT * FROM ORDERS o "
				    + "LEFT OUTER JOIN USERS u "
				    + "ON u.USER_ID = o.USER_ID "
				    + "LEFT OUTER JOIN EXH e "
				    + "ON e.EXH_NO = o.EXH_NO "
				    + "WHERE u.USER_ID = ? " 
				    + "ORDER BY ORD_DATE DESC";
			System.out.println(query);
			// 작동쿼리 확인
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();
		
			while(rs.next()) {
				OrderVO orderVO = new OrderVO();
				orderVO.setOrdNo(rs.getInt("ord_no"));
			    orderVO.setExhNo(rs.getInt("exh_no"));
			    orderVO.setUserId(rs.getString("user_id"));
                orderVO.setOrdPay(rs.getString("ord_pay"));
                orderVO.setOrdAQnt(rs.getInt("ord_aqnt"));
                orderVO.setOrdYQnt(rs.getInt("ord_yqnt"));
                orderVO.setOrdCQnt(rs.getInt("ord_cqnt"));
                orderVO.setOrdPrice(rs.getInt("ord_price"));
                orderVO.setOrdDate(rs.getDate("ord_date"));
                orderVO.setSeatNo(rs.getInt("seat_no"));
                
             // 구매한 티켓의 총 갯수 구하기
                int ord_tqnt = orderVO.getOrdAQnt() + orderVO.getOrdYQnt() + orderVO.getOrdCQnt();
	            orderVO.setOrdTQnt(ord_tqnt);
	            orderVO.setOrdStatus(rs.getString("ord_status"));
	            orderVO.setExhName(rs.getString("exh_name"));
//	            orderVO.setUserName(rs.getString("user_name"));
//	            orderVO.setUserTel(rs.getString("user_tel"));
	            orderVO.setExhStrDate(rs.getDate("exh_strdate"));
	            orderVO.setExhEndDate(rs.getDate("exh_enddate"));
	            
	            userOrders.add(orderVO);
	            
	            System.out.println(orderVO);
	            System.out.println("userOrders");
	            
			}
			rs.close();
			pstmt.close();
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return userOrders;
	}// getUserOrders END
	
	/*INSERT INTO orders (ord_no, exh_no, user_id, ord_pay, ord_aqnt, ord_yqnt, ord_cqnt, ord_price, ord_date, seat_no, ord_status)
	VALUES (ord_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) */
	public void insertNewOrder(OrderVO orderVO) {
		
		try {
			conn = dataFactory.getConnection();
			
			String query = "INSERT INTO orders (ord_no, exh_no, user_id, ord_pay, ord_aqnt, ord_yqnt, ord_cqnt, ord_price, ord_date, seat_no, ord_status)"
					+ " VALUES (ord_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, sysdate, ?, ?)";
			
			//String ord_no = "ord_seq.NEXTVAL";
			int exhNo = orderVO.getExhNo();
			String userId = orderVO.getUserId();
			String ordPay= orderVO.getOrdPay();
			int ordAQnt = orderVO.getOrdAQnt();
			int ordYQnt = orderVO.getOrdYQnt();
			int ordCQnt = orderVO.getOrdCQnt();
			int ordPrice = orderVO.getOrdPrice();
			//Date ordDate = orderVO.getOrdDate();
			int seatNo = orderVO.getSeatNo();
			String ordStatus = orderVO.getOrdStatus();
			
			System.out.println(query);
			// 작동쿼리 확인
			
			pstmt = conn.prepareStatement(query);
			//pstmt.setString(1, ord_no);
			pstmt.setInt(1, exhNo);
			pstmt.setString(2, userId);
			pstmt.setString(3, ordPay);
			pstmt.setInt(4, ordAQnt);
			pstmt.setInt(5, ordYQnt);
			pstmt.setInt(6, ordCQnt);
			pstmt.setInt(7, ordPrice);
			//pstmt.setDate(8, ordDate);
			pstmt.setInt(8, seatNo);
			pstmt.setString(9, ordStatus);
			pstmt.executeQuery();
			
			
			pstmt.close();
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}// insertNewOrder END
	
	
}//class END
