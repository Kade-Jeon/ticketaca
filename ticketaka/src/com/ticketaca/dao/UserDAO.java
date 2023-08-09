package com.ticketaca.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ticketaca.vo.ExhVO;
import com.ticketaca.vo.QnaVO;
import com.ticketaca.vo.UserVO;
import com.ticketaca.vo.ZzimVO;

public class UserDAO {
	private DataSource dataFactory;
	Connection conn;
	PreparedStatement pstmt;
	
	public UserDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("DB연결 실패  ==>" + e.getMessage());
		}
	}//UserDAO 생성자 종료
	
	public UserVO selectOne(String _userId) {
		/* 기능: 1명의 유저를 조회
		 * 사용: 마이페이지 수정 전 검증
		 * */
		UserVO userInfo = null;
		try {
			String query = "SELECT * FROM users WHERE USER_ID = ?";
			
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, _userId);
			pstmt.executeQuery();
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String userId = rs.getString("USER_ID");
			String userPw = rs.getString("USER_PW");
			String userName = rs.getString("USER_NAME");
			Date userBirth = rs.getDate("USER_BIRTH");
			String userTel= rs.getString("USER_TEL");
			String userEmail = rs.getString("USER_EMAIL");
			Date userDate = rs.getDate("USER_DATE");
			userInfo = new UserVO(userId,userPw,userName,userBirth,userTel,userEmail,userDate);
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("UserDAO selectOne() ERR :" + e.getMessage());
		}
		return userInfo;
	}//selectOne() END
	
	public void userUpdate(UserVO userVO) {
		/* 기능: 유저의 정보를 받아 DB업데이트
		 * 사용: 마이페이지 내용 수정
		 * */
		UserVO userInfo = null;
		try {
		
			String query = "UPDATE users SET USER_PW = ?, USER_NAME = ?, USER_TEL = ?, USER_EMAIL = ? WHERE USER_ID = ?";
			System.out.println("작동 쿼리문: " + query);
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userVO.getUserPw());
			pstmt.setString(2, userVO.getUserName());
			pstmt.setString(3, userVO.getUserTel());
			pstmt.setString(4, userVO.getUserEmail());
			pstmt.setString(5, userVO.getUserId());
			pstmt.executeQuery();
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("UserDAO userUpdate() ERR :" + e.getMessage());
		}
	}//userUpdate() END
	
	public void addUser(UserVO userVO) {
		/* 기능: 유저정보를 받아 데이터베이스에 추가
		 * 사용: 유저 회원가입
		 * */
		try {
			String query = "INSERT INTO users (USER_ID, USER_PW, USER_NAME, USER_BIRTH, USER_TEL, USER_EMAIL) VALUES (?,?,?,?,?,?)";
			
			conn = dataFactory.getConnection();
			String user_Id = userVO.getUserId();
			String user_Pw = userVO.getUserPw();
			String user_Name = userVO.getUserName();
			Date user_Birth = userVO.getUserBirth();
			String user_Tel = userVO.getUserTel();
			String user_Email = userVO.getUserEmail();
			
			System.out.println("작동 쿼리문: " + query);
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_Id);
			pstmt.setString(2, user_Pw);
			pstmt.setString(3, user_Name);
			pstmt.setDate(4, user_Birth);
			pstmt.setString(5, user_Tel);
			pstmt.setString(6, user_Email);
			pstmt.executeQuery();
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("UserDAO addUser() ERR :" + e.getMessage());
		}
	}//addUser() END
	
	
	
	
	public UserVO loginUser(String _userId, String _userPw) {
		/* 기능: ID/PW를 받아 유저 정보를 받아오는 기능
		 * 사용: 유저 로그인 기능
		 * */
		UserVO userInfo = null;
		try {
			String query = "SELECT * FROM users WHERE USER_ID = ? and USER_PW = ?";
			conn = dataFactory.getConnection();
			System.out.println("작동 쿼리문: " + query);
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, _userId);
			pstmt.setString(2, _userPw);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String userId = rs.getString("USER_ID");
			String userPw = rs.getString("USER_PW");
			String userName = rs.getString("USER_NAME");
			Date userBirth = rs.getDate("USER_BIRTH");
			String userTel= rs.getString("USER_TEL");
			String userEmail = rs.getString("USER_EMAIL");
			Date userDate = rs.getDate("USER_DATE");
			String userOut = rs.getString("USER_OUT");
			
			userInfo = new UserVO(userId, userPw, userName, userBirth, userTel, userEmail, userDate, userOut);
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("UserDAO loginUser() ERR :" + e.getMessage());
		}
		return userInfo;
	}//loginUser() END
	
	
	public void userOut (String userId) {
		/* 기능: 회원상태를 F -> T로 업데이트함
		 * 사용: 회원이 회원탈퇴 진행 시.
		 */

		try {
			String query = "UPDATE users SET USER_OUT = ? WHERE USER_ID = ?";
			conn = dataFactory.getConnection();
			System.out.println("작동 쿼리문: " + query);
			System.out.println(userId);
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "T");
			pstmt.setString(2, userId);
			ResultSet rs = pstmt.executeQuery();
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("UserDAO userOut() ERR :" + e.getMessage());
		}

	}//userOut() END
	
	public boolean duplicatedId(String userId) {
		/* 기능: ID를 받아 DB내 중복확인
		 * 사용: 회원 가입시 ID중복검사
		 * */
		boolean result = false;
		try {
			String query = "SELECT DECODE(COUNT(*),1,'true', 'false') AS result FROM users WHERE USER_ID = ?";
			conn = dataFactory.getConnection();
			System.out.println("작동 쿼리문: " + query);
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			result = Boolean.parseBoolean(rs.getString("result"));
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("UserDAO duplicatedId() ERR :" + e.getMessage());
		}
		return result;
		
	}//duplicatedId() END

	public ArrayList<ZzimVO> zzimList(String _userId) {
		/* 기능: ID를 받아 ZZIM LIST 받아오기
		 * 사용: 유저가 로그인 후, 찜 목록 들어가면 본인이 찜한 아이템만 표시해주기.
		 * */
		ArrayList<ZzimVO> zzimList = null;
		try {
			String query = "SELECT * FROM ZZIM z "
					+ "LEFT OUTER JOIN EXH e "
					+ "ON e.EXH_NO = z.EXH_NO "
					+ "WHERE z.USER_ID = ? "
					+ "ORDER BY ZZ_DATE DESC"; 
			
			conn = dataFactory.getConnection();
			System.out.println("작동 쿼리문: " + query);
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, _userId);
			zzimList = new ArrayList<ZzimVO>();
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {				
				int zzNo = rs.getInt("ZZ_NO");
				Date zzDate = rs.getDate("ZZ_DATE");
				int exhNO = rs.getInt("EXH_NO");
				String exhName = rs.getString("EXH_NAME");
				String exhPlace = rs.getString("EXH_PLACE");
				String exhAddress = rs.getString("EXH_ADDRESS");
				Date exhStrDate = rs.getDate("EXH_STRDATE");
				Date exhEndDate = rs.getDate("EXH_ENDDATE");
				String exhAbleBuy = rs.getString("EXH_ABLEBUY");
				String exhThumbImg = rs.getString("EXH_THUMBIMG");
				zzimList.add(new ZzimVO(zzNo,zzDate,exhNO,exhName,exhPlace,exhAddress,exhStrDate,exhEndDate,exhAbleBuy,exhThumbImg));
			}
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("UserDAO zzimList() ERR :" + e.getMessage());
		}
		return zzimList;
	}//zzimList() END
	
	public void removeZzim(String userId, int zzimNo) {
		try {
			String query = "DELETE FROM ZZIM WHERE USER_ID = ? AND ZZ_NO = ?";
			
			conn = dataFactory.getConnection();
			System.out.println("작동 쿼리문: " + query);
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setInt(2, zzimNo);
			pstmt.executeQuery();

			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("UserDAO removeZzim() ERR :" + e.getMessage());
		}
	}//removeZzim() END
	
	
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
				
				rs.close();
				pstmt.close();
				conn.close();
				
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
				String query = "SELECT exh_no, exh_name, exh_place, exh_address, TO_CHAR(TO_DATE(exh_strDate, 'YYYY-MM-DD'), 'YYYY-MM-DD') AS exh_strDate, TO_CHAR(TO_DATE(exh_endDate, 'YYYY-MM-DD'), 'YYYY-MM-DD') AS exh_endDate FROM exh"
						+ " WHERE TO_DATE(exh_strDate, 'YYYY-MM-DD') <= TRUNC(sysdate) AND TRUNC(sysdate) <= TO_DATE(exh_endDate, 'YYYY-MM-DD') ORDER BY TO_DATE(exh_strDate, 'YYYY-MM-DD')";
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
					
					exhsListOngoing.add(exhVO);
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
				String query = "SELECT exh_no, exh_name, exh_place, TO_CHAR(TO_DATE(exh_strDate, 'YYYY-MM-DD'), 'YYYY-MM-DD') AS exh_strDate, TO_CHAR(TO_DATE(exh_endDate, 'YYYY-MM-DD'), 'YYYY-MM-DD') AS exh_endDate, exh_address FROM exh"
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
						
					ExhVO exhVO = new ExhVO();
					exhVO.setNo(no);
					exhVO.setName(name);
					exhVO.setPlace(place);
					exhVO.setAddress(address);
					exhVO.setStrDate(strDate);
					exhVO.setEndDate(endDate);
					
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
				String query = "SELECT exh_no, exh_name, exh_place, exh_address, TO_CHAR(TO_DATE(exh_strDate, 'YYYY-MM-DD'), 'YYYY-MM-DD') AS exh_strDate, TO_CHAR(TO_DATE(exh_endDate, 'YYYY-MM-DD'), 'YYYY-MM-DD') AS exh_endDate FROM exh"
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
					
					ExhVO exhVO = new ExhVO();
					exhVO.setNo(no);
					exhVO.setName(name);
					exhVO.setPlace(place);
					exhVO.setAddress(address);
					exhVO.setStrDate(strDate);
					exhVO.setEndDate(endDate);
					
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
				String query = "SELECT exh_no, exh_name, exh_place, exh_address, TO_CHAR(TO_DATE(exh_strDate, 'YYYY-MM-DD'), 'YYYY-MM-DD') AS exh_strDate, TO_CHAR(TO_DATE(exh_endDate, 'YYYY-MM-DD'), 'YYYY-MM-DD') AS exh_endDate FROM exh"
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
						
					ExhVO exhVO = new ExhVO();
					exhVO.setNo(no);
					exhVO.setName(name);
					exhVO.setPlace(place);
					exhVO.setAddress(address);
					exhVO.setStrDate(strDate);
					exhVO.setEndDate(endDate);
					
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
		
		/*호준*/
		public List selectTree(String log) {
			List qnaList = new ArrayList();
			try {
				conn = dataFactory.getConnection();
				String query ="select LEVEL,QNA_NO,QNA_PNO,QNA_TITLE,QNA_CONTENT,user_id,qna_date,adm_answer" +
						" from qna" +
						" where user_id=?" +
						" START WITH QNA_PNO=0" +
						" CONNECT BY PRIOR QNA_NO=QNA_PNO" +
						" ORDER SIBLINGS BY QNA_NO DESC";
				
				pstmt =  conn.prepareStatement(query);
				pstmt.setString(1, log);
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					int level = rs.getInt("LEVEL");
					int QNo = rs.getInt("QNA_NO");
					int PNo = rs.getInt("QNA_PNO");
					String QTitle = rs.getString("QNA_TITLE");
					String QContent = rs.getString("QNA_CONTENT");
					String UId = rs.getString("user_id");
					Date QDate = rs.getDate("qna_date");
					String AAnswer = rs.getString("adm_answer");
					
					QnaVO qna = new QnaVO();
					qna.setLevel(level);
					qna.setQNo(QNo);
					qna.setPNo(PNo);
					qna.setQTitle(QTitle);
					qna.setQContent(QContent);
					qna.setUId(UId);
					qna.setQDate(QDate);
					qna.setAAnswer(AAnswer);
					qnaList.add(qna);
				}
				rs.close();
				pstmt.close();
				conn.close();
			}catch (Exception e) {
				System.out.println("selectTree(String log) Err ==>" + e.getMessage());
			}
			return qnaList;
		} //selectTree(String log) End
		
		public List selectTree(String log,int no) {
			List qnaList = new ArrayList();
			try {
				conn = dataFactory.getConnection();
				String query ="select LEVEL,QNA_NO,QNA_PNO,QNA_CATE,QNA_TITLE,QNA_CONTENT,user_id,qna_date,adm_answer" +
						" from qna" +
						" where user_id=? and QNA_NO=?"  +
						" START WITH QNA_PNO=0" +
						" CONNECT BY PRIOR QNA_NO=QNA_PNO" +
						" ORDER SIBLINGS BY QNA_NO DESC";
				
				pstmt =  conn.prepareStatement(query);
				pstmt.setString(1, log);
				pstmt.setInt(2, no);
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					int level = rs.getInt("LEVEL");
					int QNo = rs.getInt("QNA_NO");
					int PNo = rs.getInt("QNA_PNO");
					String QCate = rs.getString("QNA_CATE");
					String QTitle = rs.getString("QNA_TITLE");
					String QContent = rs.getString("QNA_CONTENT");
					String UId = rs.getString("user_id");
					Date QDate = rs.getDate("qna_date");
					String AAnswer = rs.getString("adm_answer");
					
					
					QnaVO qna = new QnaVO();
					qna.setLevel(level);
					qna.setQNo(QNo);
					qna.setPNo(PNo);
					qna.setQCate(QCate);
					qna.setQTitle(QTitle);
					qna.setQContent(QContent);
					qna.setUId(UId);
					qna.setQDate(QDate);
					qna.setAAnswer(AAnswer);
					qnaList.add(qna);
				}
				rs.close();
				pstmt.close();
				conn.close();
			}catch (Exception e) {
				System.out.println("selectTree(String log,int no) Err ==>" + e.getMessage());
			}
			return qnaList;
		} //selectTree(String log,int no) End
		
		public void writeQna(String category, String title, String content, String log) {
			int n=0;
			try {
				conn = dataFactory.getConnection();
				String query = "insert into qna(qna_no, qna_pno, qna_cate ,qna_title, qna_date, user_id, qna_content) "
						+ " values(qna_seq.nextval,0,?,?,sysdate,?,?)";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1,category);
				pstmt.setString(2,title);
				pstmt.setString(3,log);
				pstmt.setString(4,content);
				n =pstmt.executeUpdate();
				System.out.println(n+"행이 추가되었습니다");
			
				pstmt.close();
				conn.close();
			}catch (SQLException e) {
				System.out.println("writeQna Err ==>" + e.getMessage());
			}
		}//writeQna End

		public void updateQna(String title,String category,String content,String log, int no) {
			int n=0;
			try {
				conn = dataFactory.getConnection();
				String query ="update qna set QNA_TITLE=?, QNA_CATE=?, QNA_CONTENT = ?, QNA_DATE = SYSDATE where USER_ID=? and QNA_NO =?";
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1,title);
				pstmt.setString(2,category);
				pstmt.setString(3,content);
				pstmt.setString(4,log);
				pstmt.setInt(5,no);
				n = pstmt.executeUpdate();
				
				if(n>0) {
					System.out.println(no+"행이 수정되었습니다");
				}
				
			} catch (SQLException e) {
				System.out.println("updateQna Err" + e.getMessage());
			}
		}//updateQna End
		
		
		
}//class END
