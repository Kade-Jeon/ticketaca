package com.ticketaca.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ticketaca.vo.AdminVO;
import com.ticketaca.vo.ExhVO;
import com.ticketaca.vo.OrderVO;
import com.ticketaca.vo.QnaVO;
import com.ticketaca.vo.UserVO;

public class AdminDAO {
	private DataSource dataFactory;
	Connection conn;
	PreparedStatement pstmt;
	Statement stmt = null;

	public AdminDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("DB연결 실패  ==>" + e.getMessage());
		}
	}//AdminDAO 생성자 종료

	public AdminVO loginAdmin(String _adminId, String _adminPw) {
		/* 기능: ID/PW를 받아 관리자 정보를 받아오는 기능
		 * 사용: 관리자 로그인
		 */
		AdminVO amdinInfo = null;
		try {
			String query = "SELECT * FROM admin WHERE ADM_ID = ? and ADM_PW = ?";
			conn = dataFactory.getConnection();
			System.out.println("작동 쿼리문: " + query);

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, _adminId);
			pstmt.setString(2, _adminPw);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String adminId = rs.getString("ADM_ID");
			String adminPw = rs.getString("ADM_PW");
			String adminNick = rs.getString("ADM_NICK");
		
			amdinInfo = new AdminVO(adminId, adminPw, adminNick);

			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("AdminDAO loginAdmin() ERR :" + e.getMessage());
		}
		return amdinInfo;
	}// loginAdmin() END
	
	public ArrayList<UserVO> selectAllUsers() {
		/* 기능: 전체 유저 정보 받아오기.
		 * 사용: 유저관리 페이지 전체 유저 조회
		 * */
		ArrayList<UserVO> usersInfo = null;
		try {
			String query = "SELECT * FROM users";
			conn = dataFactory.getConnection();
			System.out.println("작동 쿼리문: " + query);

			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			usersInfo = new ArrayList<UserVO>();
			while(rs.next()) {
				String userId = rs.getString("USER_ID");
				String userPw = rs.getString("USER_PW");
				String userName = rs.getString("USER_NAME");
				Date userBirth = rs.getDate("USER_BIRTH");
				String userTel= rs.getString("USER_TEL");
				String userEmail = rs.getString("USER_EMAIL");
				Date userDate = rs.getDate("USER_DATE");
				String userOut = rs.getString("USER_OUT");
				usersInfo.add(new UserVO(userId, userPw, userName,userBirth,userTel,userEmail,userDate,userOut));
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("AdminDAO selectAllUsers() ERR :" + e.getMessage());
		}
		return usersInfo;
	}//selectAllUsers() END
	
	public ArrayList<UserVO> searchUser(String condition, String keyword) {
		/* 기능: 조건과 키워드를 받아와서 해당 조건에 키워드를 contains 하고 있는 값들을 전부 가져옵니다.
		 * 사용: 유저 관리페이지에서 조건에 따른 유저 검색
		 * */
		ArrayList<UserVO> searchList = null;
		System.out.println(condition);
		System.out.println(keyword);
		try {
			String query = "SELECT * FROM users WHERE" + " " + condition + " "+ "LIKE ?";
			conn = dataFactory.getConnection();
			System.out.println("작동 쿼리문: " + query);

			pstmt = conn.prepareStatement(query);
			//pstmt.setString(1, condition);
			pstmt.setString(1, "%" + keyword + "%");
			ResultSet rs = pstmt.executeQuery();
			
			searchList = new ArrayList<UserVO>();
			while(rs.next()) {
				String userId = rs.getString("USER_ID");
				String userPw = rs.getString("USER_PW");
				String userName = rs.getString("USER_NAME");
				Date userBirth = rs.getDate("USER_BIRTH");
				String userTel= rs.getString("USER_TEL");
				String userEmail = rs.getString("USER_EMAIL");
				Date userDate = rs.getDate("USER_DATE");
				String userOut = rs.getString("USER_OUT");
				searchList.add(new UserVO(userId, userPw, userName,userBirth,userTel,userEmail,userDate,userOut));
			}
			System.out.println(searchList);
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("AdminDAO selectAllUsers() ERR :" + e.getMessage());
		}
		return searchList;
	}//searchUser() END
	
	public ArrayList<OrderVO> searchUserOrder(String condition, String keyword) {
		/* 기능: 조건과 키워드를 받아와서 해당 조건에 키워드를 contains 하고 있는 값들을 전부 가져옵니다.
		 * 사용: 유저 관리페이지에서 조건에 따른 주문 검색
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
					+ "WHERE " + condition 
					+ " LIKE ? "
					+ "ORDER BY ORD_DATE DESC";
			
			conn = dataFactory.getConnection();
			System.out.println("작동 쿼리문: " + query);

			pstmt = conn.prepareStatement(query);
			//pstmt.setString(1, condition);
			pstmt.setString(1, "%" + keyword + "%");
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
			System.out.println(searchList);
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("AdminDAO searchUserOrder() ERR :" + e.getMessage());
		}
		return searchList;
	}//searchUserOrder() END
	
	
	/*민지*/
	// 전시 전체 목록 출력 (페이징 X)
		public ArrayList<ExhVO> listExhs() {
			ArrayList<ExhVO> exhsList = new ArrayList<ExhVO>();
				
			try {
				conn = dataFactory.getConnection();
				// 쿼리 확인 완료
				String query = "SELECT exh_no, exh_name, exh_place, exh_address, TO_CHAR(TO_DATE(exh_strDate, 'YYYY-MM-DD'), 'YYYY-MM-DD') AS exh_strDate, TO_CHAR(TO_DATE(exh_endDate, 'YYYY-MM-DD'), 'YYYY-MM-DD') AS exh_endDate"
							+ " FROM exh ORDER BY TO_DATE(exh_strDate, 'YYYY-MM-DD')";
				System.out.println(query);
				
				pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					int no = rs.getInt("exh_no");
					String name = rs.getString("exh_name");
					String place = rs.getString("exh_place");
					String address = rs.getString("exh_address");
					String strDate = rs.getString("exh_strDate");
					String endDate = rs.getString("exh_endDate");
					
					ExhVO exhVO = new ExhVO();
					exhVO.setNo(no);
					exhVO.setName(name);
					exhVO.setPlace(place);
					exhVO.setAddress(address);
					exhVO.setStrDate(strDate);
					exhVO.setEndDate(endDate);
					
					exhsList.add(exhVO);
				}	// end while			
			} catch(Exception e) {
				System.out.println("listExh() ERR : " + e.getMessage());
			} 
			return exhsList;
		}	// end listExhs()
			
		// 전시 전체 목록 출력 (페이징)
		public ArrayList<ExhVO> listExhs(Map<String, Integer> pagingMap) {
			ArrayList<ExhVO> exhsList = new ArrayList<ExhVO>();
			
			int section = (Integer) pagingMap.get("section");
			int pageNum = (Integer) pagingMap.get("pageNum");
			
			try {
				conn = dataFactory.getConnection();
				// 페이징 시에 rownum 생성 전에 서브쿼리문에서 정렬해주기 ★★★ --> 마지막에 정렬하면 rownum이 얽힐 수 있음
				// 쿼리 확인 완료 (WHERE절 제외)
				String query = "SELECT exh_no, exh_name, exh_place, exh_address, exh_strDate, exh_endDate, exh_ableBuy FROM"
						+ " (SELECT ROWNUM as recNum, exh_no, exh_name, exh_place, exh_address,"
							+ " TO_CHAR(TO_DATE(exh_strDate, 'YYYY-MM-DD'), 'YYYY-MM-DD') AS exh_strDate,"
							+ " TO_CHAR(TO_DATE(exh_endDate, 'YYYY-MM-DD'), 'YYYY-MM-DD') AS exh_endDate, exh_ableBuy"
							+ " FROM exh ORDER BY TO_DATE(exh_strDate, 'YYYY-MM-DD'))"
						+ " WHERE recNum between(?-1)*100+(?-1)*10+1 and (?-1)*100+?*10";

				System.out.println(query);
				
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, section);
				pstmt.setInt(2, pageNum);
				pstmt.setInt(3, section);
				pstmt.setInt(4, pageNum);
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					int no = rs.getInt("exh_no");
					String name = rs.getString("exh_name");
					String place = rs.getString("exh_place");
					String address = rs.getString("exh_address");
					String strDate = rs.getString("exh_strDate");
					String endDate = rs.getString("exh_endDate");
					String exhStatus = rs.getString("exh_ableBuy");	// #####
					
					ExhVO exhVO = new ExhVO();
					exhVO.setNo(no);
					exhVO.setName(name);
					exhVO.setPlace(place);
					exhVO.setAddress(address);
					exhVO.setStrDate(strDate);
					exhVO.setEndDate(endDate);
					exhVO.setExhStatus(exhStatus);	// #####
					
					exhsList.add(exhVO);
				}	// end while	
				
				rs.close();
				pstmt.close();
				conn.close();
			} catch(Exception e) {
				System.out.println("listExh() ERR : " + e.getMessage());
			} 
			return exhsList;
		}	// end listExhs()
			
		// 진행 중인 전시 목록 출력
		public ArrayList<ExhVO> listExhsOngoing() {
			ArrayList<ExhVO> exhsListOngoing = new ArrayList<ExhVO>();
					
			try {
				conn = dataFactory.getConnection();
				// 쿼리 확인 완료
				String query = "SELECT exh_no, exh_name, exh_place, exh_address, TO_CHAR(TO_DATE(exh_strDate, 'YYYY-MM-DD'), 'YYYY-MM-DD') AS exh_strDate, TO_CHAR(TO_DATE(exh_endDate, 'YYYY-MM-DD'), 'YYYY-MM-DD') AS exh_endDate, exh_ableBuy FROM exh"
					+ " WHERE TO_DATE(exh_strDate, 'YYYY-MM-DD') <= TRUNC(sysdate) AND TRUNC(sysdate) <= TO_DATE(exh_endDate, 'YYYY-MM-DD') ORDER BY TO_DATE(exh_strDate, 'YYYY-MM-DD')";
				System.out.println(query);
				
				pstmt = conn.prepareStatement(query);
				
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					int no = rs.getInt("exh_no");
					System.out.println(no);
					String name = rs.getString("exh_name");
					String place = rs.getString("exh_place");
					String address = rs.getString("exh_address");
					String strDate = rs.getString("exh_strDate");
					String endDate = rs.getString("exh_endDate");
					String exhStatus = rs.getString("exh_ableBuy");	// #####
					
					ExhVO exhVO = new ExhVO();
					exhVO.setNo(no);
					exhVO.setName(name);
					exhVO.setPlace(place);
					exhVO.setAddress(address);
					exhVO.setStrDate(strDate);
					exhVO.setEndDate(endDate);
					exhVO.setExhStatus(exhStatus);	// #####
					
					exhsListOngoing.add(exhVO);
					
					System.out.println(exhsListOngoing);
				}
				rs.close();
				pstmt.close();
				conn.close();
			} catch(Exception e) {
				System.out.println("listExh() ERR : " + e.getMessage());
			} 
			return exhsListOngoing;
		}	// end listExhsOngoing()
		
		// 마감 임박 전시 목록 출력
		public ArrayList<ExhVO> listExhsNearingDeadline() {
			ArrayList<ExhVO> exhsListNearingDeadline = new ArrayList<ExhVO>();
			
			try {
				conn = dataFactory.getConnection();
				// 마감 임박 전시의 경우 종료 일자를 기준으로 오름차순 (마감이 가장 임박한 전시부터 출력)
				// 쿼리 확인 완료
				String query = "SELECT exh_no, exh_name, exh_place, TO_CHAR(TO_DATE(exh_strDate, 'YYYY-MM-DD'), 'YYYY-MM-DD') AS exh_strDate, TO_CHAR(TO_DATE(exh_endDate, 'YYYY-MM-DD'), 'YYYY-MM-DD') AS exh_endDate, exh_address, exh_ableBuy FROM exh"
						+ " WHERE TRUNC(sysdate) <= TO_DATE(exh_endDate, 'yy/mm/dd')"
						+ " AND TO_DATE(exh_endDate, 'YYYY-MM-DD') < (TRUNC(sysdate)+7) ORDER BY TO_DATE(exh_endDate, 'YYYY-MM-DD')";
				System.out.println(query);
				
				pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery();
					
				while(rs.next()) {
					int no = rs.getInt("exh_no");
					String name = rs.getString("exh_name");
					String place = rs.getString("exh_place");
					String address = rs.getString("exh_address");
					String strDate = rs.getString("exh_strDate");
					String endDate = rs.getString("exh_endDate");
					String exhStatus = rs.getString("exh_ableBuy");	// #####
						
					ExhVO exhVO = new ExhVO();
					exhVO.setNo(no);
					exhVO.setName(name);
					exhVO.setPlace(place);
					exhVO.setAddress(address);
					exhVO.setStrDate(strDate);
					exhVO.setEndDate(endDate);
					exhVO.setExhStatus(exhStatus);	// #####
					
					exhsListNearingDeadline.add(exhVO);
				}
				rs.close();
				pstmt.close();
				conn.close();
			} catch(Exception e) {
				System.out.println("listExh() ERR : " + e.getMessage());
			} 
			return exhsListNearingDeadline;
		}	// end listExhsNearingDeadline()
		
		// 예정 전시 목록 출력
		public ArrayList<ExhVO> listExhsUpcoming() {
			ArrayList<ExhVO> exhsListUpcoming = new ArrayList<ExhVO>();
			
			try {
				conn = dataFactory.getConnection();
				// 쿼리 확인 완료
				String query = "SELECT exh_no, exh_name, exh_place, exh_address, TO_CHAR(TO_DATE(exh_strDate, 'YYYY-MM-DD'), 'YYYY-MM-DD') AS exh_strDate, TO_CHAR(TO_DATE(exh_endDate, 'YYYY-MM-DD'), 'YYYY-MM-DD') AS exh_endDate, exh_ableBuy FROM exh"
					+ " WHERE TO_DATE(exh_strDate, 'YYYY-MM-DD') > TRUNC(sysdate) ORDER BY TO_DATE(exh_strDate, 'YYYY-MM-DD')";
				System.out.println(query);
				
				pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					int no = rs.getInt("exh_no");
					String name = rs.getString("exh_name");
					String place = rs.getString("exh_place");
					String address = rs.getString("exh_address");
					String strDate = rs.getString("exh_strDate");
					String endDate = rs.getString("exh_endDate");
					String exhStatus = rs.getString("exh_ableBuy");	// #####
					
					ExhVO exhVO = new ExhVO();
					exhVO.setNo(no);
					exhVO.setName(name);
					exhVO.setPlace(place);
					exhVO.setAddress(address);
					exhVO.setStrDate(strDate);
					exhVO.setEndDate(endDate);
					exhVO.setExhStatus(exhStatus);	// #####
					
					exhsListUpcoming.add(exhVO);
				}	
				
				rs.close();
				pstmt.close();
				conn.close();
			} catch(Exception e) {
				System.out.println("listExh() ERR : " + e.getMessage());
			} 
			return exhsListUpcoming;
		}	// end listExhsUpcoming()
		
		// 종료 전시 목록 출력
		public ArrayList<ExhVO> listExhsClosing() {
			ArrayList<ExhVO> exhsListClosing = new ArrayList<ExhVO>();
			
			try {
				conn = dataFactory.getConnection();
				// 종료된 전시의 경우 종료 일자를 기준으로 내림차순 (가장 최근에 종료된 전시부터 목록 출력)
				// 쿼리확인완료
				String query = "SELECT exh_no, exh_name, exh_place, exh_address, TO_CHAR(TO_DATE(exh_strDate, 'YYYY-MM-DD'), 'YYYY-MM-DD') AS exh_strDate, TO_CHAR(TO_DATE(exh_endDate, 'YYYY-MM-DD'), 'YYYY-MM-DD') AS exh_endDate, exh_ableBuy FROM exh"
						+ " WHERE TO_DATE(exh_endDate, 'YYYY-MM-DD') < TRUNC(sysdate) ORDER BY TO_DATE(exh_endDate, 'YYYY-MM-DD') DESC";
				System.out.println(query);
				
				pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery();
					
				while(rs.next()) {
					int no = rs.getInt("exh_no");
					String name = rs.getString("exh_name");
					String place = rs.getString("exh_place");
					String address = rs.getString("exh_address");
					String strDate = rs.getString("exh_strDate");
					String endDate = rs.getString("exh_endDate");
					String exhStatus = rs.getString("exh_ableBuy");	// #####
						
					ExhVO exhVO = new ExhVO();
					exhVO.setNo(no);
					exhVO.setName(name);
					exhVO.setPlace(place);
					exhVO.setAddress(address);
					exhVO.setStrDate(strDate);
					exhVO.setEndDate(endDate);
					exhVO.setExhStatus(exhStatus);	// #####
					
					exhsListClosing.add(exhVO);
				}	
				
				rs.close();
				pstmt.close();
				conn.close();
			} catch(Exception e) {
				System.out.println("listExh() ERR : " + e.getMessage());
			} 
			return exhsListClosing;
		}	// end listExhsClosing()
		
		
		// 전시 등록 (전시명, 장소, 주소, 시작일, 종료일, 가격, 전시 내용, 판매수량, 게시여부, 이미지, 상세이미지)
		public int addExh(ExhVO exhVO) {
			System.out.println("AdminDao000 : " );
			int exhNO = getNewExhNO();	// 전시 등록 전 신규 전시에 대한 번호를 가져옴
			System.out.println("AdminDao00 : " + exhNO);
			try {
				System.out.println("AdminDao0");
				conn = dataFactory.getConnection();
				System.out.println("AdminDao1");
				String name = exhVO.getName();
				String place = exhVO.getPlace();
				String address = exhVO.getAddress();
				String strDate = exhVO.getStrDate();
				String endDate = exhVO.getEndDate();
				int adultPrice = exhVO.getAdultPrice();
				int youthPrice = exhVO.getYouthPrice();
				int childPrice = exhVO.getChildPrice();
				String content = exhVO.getContent();
				int maxCnt = exhVO.getMaxCnt();
				String exhStatus = exhVO.getExhStatus();
				String postStatus = exhVO.getPostStatus();
				String image = exhVO.getImage();
				String detailImage = exhVO.getDetailImage();
				System.out.println("AdminDao2");
				String query = "INSERT INTO exh (exh_no, exh_name, exh_place, exh_address, exh_strDate,"
							+ " exh_endDate, exh_aPrice, exh_yPrice, exh_cPrice, exh_content, exh_crrCnt,"
							+ " exh_maxCnt, exh_ableBuy, exh_postStatus";
				System.out.println("AdminDao3");
				
				// 대표 이미지 파일만 있을 경우
				if((image != null && image.length() != 0) && (detailImage == null && detailImage.length() ==0)) {
					query += ", exh_thumbImg) VALUES (exh_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0, ?, ?, ?, ?)";
					
				// 상세 이미지만 있을경우
				} else if ((image == null && image.length() == 0) && (detailImage != null && detailImage.length() !=0) ) {
					query += ", exh_detailImg) VALUES (exh_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0, ?, ?, ?, ?)";
					
				// 대표 이미지 파일과 상세 이미지 파일이 둘다 있을 경우	
				} else if ((image != null && image.length() != 0) && (detailImage != null && detailImage.length() !=0) ) {
					query += ", exh_thumbImg, exh_detailImg) VALUES (exh_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0, ?, ?, ?, ?, ?)";			
				}
				
				System.out.println(query);
				
				pstmt = conn.prepareStatement(query);
				//pstmt.setInt(1, exhNO);
				pstmt.setString(1, name);
				pstmt.setString(2, place);
				pstmt.setString(3, address);
				pstmt.setString(4, strDate);
				pstmt.setString(5, endDate);
				pstmt.setInt(6, adultPrice);
				pstmt.setInt(7, youthPrice);
				pstmt.setInt(8, childPrice);
				pstmt.setString(9, content);
				pstmt.setInt(10, maxCnt);
				pstmt.setString(11, exhStatus);
				pstmt.setString(12, postStatus);
				
				// 대표 이미지 파일만 있을 경우
				if((image != null && image.length() != 0) && (detailImage == null && detailImage.length() ==0)) {
					pstmt.setString(13, image);
								
				// 상세 이미지 파일만 있을경우	
				} else if ((image == null && image.length() == 0) && (detailImage != null && detailImage.length() !=0)) {
					pstmt.setString(13, detailImage);
					
				// 대표 이미지 파일과 상세 이미지 파일이 둘다 있을 경우	
				} else if ((image != null && image.length() != 0) && (detailImage != null && detailImage.length() !=0)) {
					pstmt.setString(13, image);
					pstmt.setString(14, detailImage);						
				} 
				
				pstmt.executeUpdate();
				
				pstmt.close();
				conn.close();
				
			} catch(Exception e) {
				System.out.println("addExh() ERR : " + e.getMessage());
			} 
			return exhNO;
		}
		
		// 기존 전시 번호 중 가장 큰 번호를 조회
		private int getNewExhNO() {
			try {
				conn = dataFactory.getConnection();
				
				String query = "SELECT max(exh_no) FROM exh";
				System.out.println(query);
				
				pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
					return (rs.getInt(1) + 1);	// 가장 큰 번호에 1을 더한 번호를 반환
				}
				
				rs.close();
				pstmt.close();
				conn.close();
				
			} catch(Exception e) {
				System.out.println("getNewExhNO() ERR : " + e.getMessage());
			} 
			return 0;
		}
		
		// 전시 목록 갯수 추출
		public int selectTotalExhs() {
			try {
				conn = dataFactory.getConnection();
				
				String query = "SELECT count(exh_no) FROM exh";
				System.out.println(query);
					
				pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery();
					
				if (rs.next()) {
					return (rs.getInt(1));
				} 
				
				rs.close();
				pstmt.close();
				conn.close();
			} catch(Exception e) {
				System.out.println("selectTotalExhs() ERR : " + e.getMessage());
			} 
			return 0;
		}	// end selectTotalExhs()
			
		// 전시 세부정보 출력 (페이징 X)
		public ArrayList<ExhVO> listExhsDetail(int exhNo) {
			ArrayList<ExhVO> exhsListDetail = new ArrayList<ExhVO>();
				
			try {
				conn = dataFactory.getConnection();
				
				String query = "SELECT exh_no, exh_name, exh_place, exh_address, TO_CHAR(TO_DATE(exh_strDate, 'YYYY-MM-DD'), 'YYYY-MM-DD') AS exh_strDate, TO_CHAR(TO_DATE(exh_endDate, 'YYYY-MM-DD'), 'YYYY-MM-DD') AS exh_endDate,"
							+ " exh_aPrice, exh_yPrice, exh_cPrice, exh_thumbImg, exh_detailImg FROM exh WHERE exh_no = ?";
//				System.out.println(query);
				
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, exhNo);
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					int no = rs.getInt("exh_no");
					String name = rs.getString("exh_name");
					String place = rs.getString("exh_place");
					String address = rs.getString("exh_address");
					String strDate = rs.getString("exh_strDate");
					String endDate = rs.getString("exh_endDate");
					int adultPrice = rs.getInt("exh_aPrice");
					int youthPrice = rs.getInt("exh_yPrice");
					int childPrice = rs.getInt("exh_cPrice");
					String image = rs.getString("exh_thumbImg");
					System.out.println("listExhsDetail() =====> " + image);
					String detailImage = rs.getString("exh_detailImg");
					System.out.println("listExhsDetail() =====> " + detailImage);
					
					ExhVO exhVO = new ExhVO();
					exhVO.setNo(no);
					exhVO.setName(name);
					exhVO.setPlace(place);
					exhVO.setAddress(address);
					exhVO.setStrDate(strDate);
					exhVO.setEndDate(endDate);
					exhVO.setAdultPrice(adultPrice);
					exhVO.setYouthPrice(youthPrice);
					exhVO.setChildPrice(childPrice);
					exhVO.setImage(image);
					exhVO.setDetailImage(detailImage);
					
					exhsListDetail.add(exhVO);
					
					
				}	// end while		
				rs.close();
				pstmt.close();
				conn.close();
			} catch(Exception e) {
				System.out.println("listExh() ERR : " + e.getMessage());
			} 
			return exhsListDetail;
		}	// end listExhsDetail()

		// 시퀀스 넘버로 전시 1개 정보 출력 (수정 폼으로 연결)
	public ExhVO selectExh(int exhNO) {
		ExhVO exh = new ExhVO();
		try {
			conn = dataFactory.getConnection();;			
			
			String query = "SELECT exh_no, exh_name, exh_place, exh_address, exh_strDate, exh_endDate, exh_aPrice, exh_yPrice, exh_cPrice, exh_content,"
						+ " exh_maxCnt, exh_ableBuy, exh_postStatus, NVL(exh_thumbImg, 'null') as exh_thumbImg, NVL(exh_detailImg, 'null') as exh_detailImg"
						+ " FROM exh where exh_no = ?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, exhNO);			
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			int _exhNO = rs.getInt("exh_no");
			String name = rs.getString("exh_name");
			String place = rs.getString("exh_place");
			String address = rs.getString("exh_address");
			String strDate = rs.getString("exh_strDate");
			String endDate = rs.getString("exh_endDate");
			System.out.println(name);
			int adultPrice = rs.getInt("exh_aPrice");
			int youthPrice = rs.getInt("exh_yPrice");
			int childPrice = rs.getInt("exh_cPrice");
			String content = rs.getString("exh_content");
			int maxCnt = rs.getInt("exh_maxCnt");
			String exhStatus = rs.getString("exh_ableBuy");
			String postStatus = rs.getString("exh_postStatus");
			String image = rs.getString("exh_thumbImg");
			String detailImage = rs.getString("exh_detailImg");

			exh.setNo(_exhNO);
			exh.setName(name);
			exh.setPlace(place);
			exh.setAddress(address);
			exh.setStrDate(strDate);
			exh.setEndDate(endDate);
			exh.setAdultPrice(adultPrice);
			exh.setYouthPrice(youthPrice);
			exh.setChildPrice(childPrice);
			exh.setContent(content);
			exh.setMaxCnt(maxCnt);
			exh.setExhStatus(exhStatus);
			exh.setPostStatus(postStatus);
			exh.setImage(image);
			exh.setDetailImage(detailImage);	
		} catch(Exception e) {
			System.out.println("selectExh() ERR : " + e.getMessage());
		} 
		return exh;
	}
	
	// 전시 정보 수정 기능 ####################
	public void modExh(ExhVO exh) {
		int exhNO = exh.getNo();
		String name = exh.getName();
		String place = exh.getPlace();
		String address = exh.getAddress();
		String strDate = exh.getStrDate();
		String endDate = exh.getEndDate();
		int adultPrice = exh.getAdultPrice();
		int youthPrice = exh.getYouthPrice();
		int childPrice = exh.getChildPrice();
		String content = exh.getContent();
		int crrCnt = exh.getCrrCnt();
		int maxCnt = exh.getMaxCnt();
		String exhStatus = exh.getExhStatus();
		String postStatus = exh.getPostStatus();
		String image = exh.getImage();
		String detailImage = exh.getDetailImage();
		
		try {
			conn = dataFactory.getConnection();
			// 이미지가 없을 때
			String query = "UPDATE exh SET exh_name = ?, exh_place = ?, exh_address = ?, exh_strDate = ?, exh_endDate = ?,"
						+ " exh_aPrice = ?, exh_yPrice = ?, exh_cPrice = ?, exh_content = ?, exh_crrCnt = ?, exh_maxCnt = ?, exh_ableBuy = ?, exh_postStatus = ? WHERE exh_no = ?";
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, place);
			pstmt.setString(3, address);
			pstmt.setString(4, strDate);
			pstmt.setString(5, endDate);
			pstmt.setInt(6, adultPrice);
			pstmt.setInt(7, youthPrice);
			pstmt.setInt(8, childPrice);
			pstmt.setString(9, content);
			pstmt.setInt(10, crrCnt);
			pstmt.setInt(11, maxCnt);
			pstmt.setString(12, exhStatus);
			pstmt.setString(13, postStatus);
			pstmt.setInt(14, exhNO);		
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("modExh() ERR : " + e.getMessage());
		} 	
	} // modExh() END
	
	// 전시 정보 삭제 기능
	public void deleteExh(int exhNO) {
		try {
			conn = dataFactory.getConnection();
			String query = "DELETE FROM exh WHERE exh_no = ?";
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, exhNO);
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("deleteExh() ERR : " + e.getMessage());
		} 
	}
	
	
	public ArrayList<ExhVO> mainSearch(Map<String, Integer> pagingMap, String keyword) {
		// 전시 검색
		
	ArrayList<ExhVO> exhsList = null;

	int section = (Integer) pagingMap.get("section");
	int pageNum = (Integer) pagingMap.get("pageNum");

	try {
		conn = dataFactory.getConnection();
		// 페이징 시에 rownum 생성 전에 서브쿼리문에서 정렬해주기 ★★★ --> 마지막에 정렬하면 rownum이 얽힐 수 있음
		// 쿼리 확인 완료 (WHERE절 제외)
		String query = "SELECT exh_no, exh_name, exh_place, exh_address, exh_ableBuy, TO_CHAR(TO_DATE(exh_strDate, 'YYYY-MM-DD'), 'YYYY-MM-DD') AS exh_strDate, TO_CHAR(TO_DATE(exh_endDate, 'YYYY-MM-DD'), 'YYYY-MM-DD') AS exh_endDate FROM exh WHERE EXH_NAME LIKE ? ORDER BY TO_DATE(exh_strDate, 'YYYY-MM-DD')";

		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, "%" + keyword + "%");

		System.out.println(query);
		System.out.println(keyword);
		
		ResultSet rs = pstmt.executeQuery();
		exhsList = new ArrayList<ExhVO>();

		while (rs.next()) {
			int no = rs.getInt("exh_no");
			String name = rs.getString("exh_name");
			System.out.println(name);
			String place = rs.getString("exh_place");
			String address = rs.getString("exh_address");
			String strDate = rs.getString("exh_strDate");
			String endDate = rs.getString("exh_endDate");
			String exhStatus = rs.getString("exh_ableBuy"); 

			ExhVO exhVO = new ExhVO();
			exhVO.setNo(no);
			exhVO.setName(name);
			exhVO.setPlace(place);
			exhVO.setAddress(address);
			exhVO.setStrDate(strDate);
			exhVO.setEndDate(endDate);
			exhVO.setExhStatus(exhStatus);

			exhsList.add(exhVO);
		} // end while

		rs.close();
		pstmt.close();
		conn.close();

	} catch (Exception e) {
		System.out.println("mainSearch() ERR : " + e.getMessage());
	}
	return exhsList;
	} // end mainSearch()
	
	/* 호준 */
	public List<QnaVO> selectAll() {
		List adminqnaList = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			String query="select QNA_NO, QNA_TITLE, QNA_CONTENT, USER_ID, QNA_DATE, ADM_ANSWER"+
			" from qna where QNA_PNO=0" +
			" order by QNA_DATE";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				int QNo = rs.getInt("QNA_NO");
				String QTitle = rs.getString("QNA_TITLE");
				String QContent = rs.getString("QNA_CONTENT");
				String UId = rs.getString("USER_ID");
				Date QDate = rs.getDate("QNA_DATE");
				String AAnswer = rs.getString("ADM_ANSWER");
				
				QnaVO qna = new QnaVO();
				
				qna.setQNo(QNo);
				qna.setQTitle(QTitle);
				qna.setQContent(QContent);
				qna.setUId(UId);
				qna.setQDate(QDate);
				qna.setAAnswer(AAnswer);
				
				adminqnaList.add(qna);
			}
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("selectTree Err ==>" + e.getMessage());
		}
		
		return adminqnaList;
	} //selectAll() End
	
	public List<QnaVO> selectAll(int Qno) {
		List adminqnaList = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			String query="select QNA_NO, USER_ID, QNA_TITLE, QNA_DATE, QNA_CATE, QNA_CONTENT"+
			" from qna where QNA_NO=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Qno);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int QNo = rs.getInt("QNA_NO");
				String UId = rs.getString("USER_ID");
				String QTitle = rs.getString("QNA_TITLE");
				Date QDate = rs.getDate("QNA_DATE");
				String QCate = rs.getString("QNA_CATE");
				String QContent = rs.getString("QNA_CONTENT");
				
				System.out.println("#!@@#!"+QNo+UId+QTitle+QDate+QCate+QContent);
				QnaVO qna = new QnaVO();
				
				qna.setQNo(QNo);
				qna.setUId(UId);
				qna.setQTitle(QTitle);
				qna.setQDate(QDate);
				qna.setQCate(QCate);
				qna.setQContent(QContent);
				
				adminqnaList.add(qna);
			}
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("selectAll(int Qno) Err ==>" + e.getMessage());
		}
		
		return adminqnaList;
	} //selectAll(int Qno) End

	public List<QnaVO> downreplyQna(int Qno) {
		List<QnaVO> adminselectPno = new ArrayList<QnaVO>();
		try {
			conn = dataFactory.getConnection();
			String query = "select QNA_CONTENT,QNA_PNO from QNA where QNA_PNO=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Qno);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String QContent = rs.getString("QNA_CONTENT");
				int Pno = rs.getInt("QNA_PNO");
				QnaVO qna = new QnaVO();
				
				qna.setQContent(QContent);
				qna.setPNo(Pno);
				
				
				adminselectPno.add(qna);
				
			}
		} catch (SQLException e) {
			System.out.println("downreplyQna Err ==> " + e.getMessage());
		}
		return adminselectPno;
	}

	public void insertadminQna(String category,String title,String id,String content,int Qno) {
		int n =0;
		try {
			conn = dataFactory.getConnection();
			String query = "insert into QNA(QNA_NO,QNA_PNO,QNA_CATE,QNA_TITLE,QNA_DATE,USER_ID,QNA_CONTENT,ADM_ANSWER) "
					+ "values(QNA_SEQ.NEXTVAL,?,?,?,SYSDATE,?,?,'T')";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Qno);
			pstmt.setString(2, category);
			pstmt.setString(3, title);
			pstmt.setString(4, id);
			pstmt.setString(5, content);
			
			n = pstmt.executeUpdate();
			
			if(n>0) {
				System.out.println(n+"개의 답글이 추가되었습니다.");
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("insertadminQna Err ==>"+e.getMessage());
		}
	}

	public void updateinfo(int qno) {
		int n = 0;
		try {
			conn = dataFactory.getConnection();
			String query = "update QNA set ADM_ANSWER='T' where QNA_NO=? ";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qno);
			n = pstmt.executeUpdate();
			
			if(n>0) {
				System.out.println(qno+"행이 ADM_ANSWER가 T로 바뀌었습니다.");
			}
		} catch (SQLException e) {
			System.out.println("updateinfo Err ==>" + e.getMessage());
		}
		
	}

	
}//CLASS end
