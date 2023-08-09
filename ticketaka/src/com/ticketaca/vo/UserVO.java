package com.ticketaca.vo;

import java.sql.Date;

public class UserVO {
	private String userId;
	private String userPw;
	private String userName;
	private Date userBirth;
	private String userTel;
	private String userEmail;
	private Date userDate;
	private String userOut;
	
	public UserVO() { //기본생성자
	
	}

	

	public UserVO(String userId, String userPw, String userName, Date userBirth, String userTel, String userEmail) {
		/*회원가입*/
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userBirth = userBirth;
		this.userTel = userTel;
		this.userEmail = userEmail;
	} //overloading 생성자 

	public UserVO(String userId, String userPw, String userName, Date userBirth, String userTel, String userEmail,
			Date userDate) {
		/*로그인*/
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userBirth = userBirth;
		this.userTel = userTel;
		this.userEmail = userEmail;
		this.userDate = userDate;
	}//overloading 생성자 

	
	public UserVO(String userId, String userPw, String userName, Date userBirth, String userTel, String userEmail,
			Date userDate, String userOut) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userBirth = userBirth;
		this.userTel = userTel;
		this.userEmail = userEmail;
		this.userDate = userDate;
		this.userOut = userOut;
	}//overloading 생성자 - 관리자에서 리스트조회



	public UserVO(String userId, String userPw, String userName, String userTel, String userEmail) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userTel = userTel;
		this.userEmail = userEmail;
	}//overloading 생성자 



	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getUserBirth() {
		return userBirth;
	}

	public void setUserBirth(Date userBirth) {
		this.userBirth = userBirth;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Date getUserDate() {
		return userDate;
	}

	public void setUserDate(Date userDate) {
		this.userDate = userDate;
	}


	public String getUserOut() {
		return userOut;
	}


	public void setUserOut(String userOut) {
		this.userOut = userOut;
	}



	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", userPw=" + userPw + ", userName=" + userName + ", userBirth=" + userBirth
				+ ", userTel=" + userTel + ", userEmail=" + userEmail + ", userDate=" + userDate + ", userOut="
				+ userOut + "]";
	}



}
