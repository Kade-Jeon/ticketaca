package com.ticketaca.vo;

public class AdminVO {
	private String adminId;
	private String adminPw;
	private String adminNick;
	public AdminVO() {
		
	}//기본생성자
		
	public AdminVO(String adminId, String adminPw, String adminNick) {
		super();
		this.adminId = adminId;
		this.adminPw = adminPw;
		this.adminNick = adminNick;
	}//overloading 생성자


	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminPw() {
		return adminPw;
	}
	public void setAdminPw(String adminPw) {
		this.adminPw = adminPw;
	}
	public String getAdminNick() {
		return adminNick;
	}
	public void setAdminNick(String adminNick) {
		this.adminNick = adminNick;
	}
	@Override
	public String toString() {
		return "AdminVO [adminId=" + adminId + ", adminPw=" + adminPw + ", adminNick=" + adminNick + "]";
	}
	
}
