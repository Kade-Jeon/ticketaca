package com.ticketaca.vo;

import java.sql.Date;

public class OrderVO {
	
	private int ordNo;
	private int exhNo;
	private String userId;
	private String ordPay;
	private int ordAQnt;
	private int ordYQnt;
	private int ordCQnt;
	private int ordPrice; 
	private Date ordDate;
	private int seatNo;
	private int ordTQnt;
	private String exhName; 
	private String userName;
	private String userTel;
	private Date exhStrDate;
	private Date exhEndDate;
	private String ordStatus;
	
	public OrderVO() {
	
	}//기본 생성자 END


	public OrderVO(int ordNo, int exhNo, String userId, String ordPay, int ordAQnt, int ordYQnt, int ordCQnt, int ordPrice, Date ordDate,
			int seatNo, int ordTQnt, String exhName, String ordStatus) {
		super();
		this.ordNo = ordNo;
		this.exhNo = exhNo;
		this.userId = userId;
		this.ordPay = ordPay;
		this.ordAQnt = ordAQnt;
		this.ordYQnt = ordYQnt;
		this.ordCQnt = ordCQnt;
		this.ordPrice = ordPrice;
		this.ordDate = ordDate;
		this.seatNo = seatNo;
		this.ordTQnt = ordTQnt;
		this.exhName = exhName;
		this.ordStatus = ordStatus;
	} //오버로딩 생성자 - 유저
	
	
	public OrderVO(int exhNo, String userId,  String ordPay, int ordAQnt, int ordYQnt, int ordCQnt, int ordPrice,
			Date ordDate, int seatNo, String ordStatus) {
		super();
		this.exhNo = exhNo;
		this.userId = userId;
		this.ordPay = ordPay;
		this.ordAQnt = ordAQnt;
		this.ordYQnt = ordYQnt;
		this.ordCQnt = ordCQnt;
		this.ordPrice = ordPrice;
		this.ordDate = ordDate;
		this.seatNo = seatNo;
		this.ordStatus = ordStatus;
		
		
	}	// 오버로딩 생성자 - userOrderAdd.do
	


	public OrderVO(int ordNo, int exhNo, String userId, String ordPay, int ordAQnt, int ordYQnt, int ordCQnt,
			int ordPrice, Date ordDate, int seatNo, int ordTQnt, String exhName, String userName, String userTel,
			Date exhStrDate, Date exhEndDate, String ordStatus) {
		super();
		this.ordNo = ordNo;
		this.exhNo = exhNo;
		this.userId = userId;
		this.ordPay = ordPay;
		this.ordAQnt = ordAQnt;
		this.ordYQnt = ordYQnt;
		this.ordCQnt = ordCQnt;
		this.ordPrice = ordPrice;
		this.ordDate = ordDate;
		this.seatNo = seatNo;
		this.ordTQnt = ordTQnt;
		this.exhName = exhName;
		this.userName = userName;
		this.userTel = userTel;
		this.exhStrDate = exhStrDate;
		this.exhEndDate = exhEndDate;
		this.ordStatus = ordStatus;
	}//오버로딩 생성자 - 관리자
	
	public OrderVO(int ordNo, int exhNo, String userId, String ordPay, int ordAQnt, int ordYQnt, int ordCQnt,
			int ordPrice, Date ordDate, int seatNo, int ordTQnt, String exhName, String userName, String userTel,
			Date exhStrDate, Date exhEndDate) {
		super();
		this.ordNo = ordNo;
		this.exhNo = exhNo;
		this.userId = userId;
		this.ordPay = ordPay;
		this.ordAQnt = ordAQnt;
		this.ordYQnt = ordYQnt;
		this.ordCQnt = ordCQnt;
		this.ordPrice = ordPrice;
		this.ordDate = ordDate;
		this.seatNo = seatNo;
		this.ordTQnt = ordTQnt;
		this.exhName = exhName;
		this.userName = userName;
		this.userTel = userTel;
		this.exhStrDate = exhStrDate;
		this.exhEndDate = exhEndDate;
		
	}


	@Override
	public String toString() {
		return "OrderVO [ordNo=" + ordNo + ", exhNo=" + exhNo + ", userId=" + userId + ", ordPay=" + ordPay
				+ ", ordAQnt=" + ordAQnt + ", ordYQnt=" + ordYQnt + ", ordCQnt=" + ordCQnt + ", ordPrice=" + ordPrice
				+ ", ordDate=" + ordDate + ", seatNo=" + seatNo + ", ordTQnt=" + ordTQnt + ", exhName=" + exhName
				+ ", userName=" + userName + ", userTel=" + userTel + ", exhStrDate=" + exhStrDate + ", exhEndDate="
				+ exhEndDate + ", ordStatus=" + ordStatus + "]";
	}


	public String getOrdStatus() {
		return ordStatus;
	}


	public void setOrdStatus(String ordStatus) {
		this.ordStatus = ordStatus;
	}


	public int getOrdNo() {
		return ordNo;
	}


	public void setOrdNo(int ordNo) {
		this.ordNo = ordNo;
	}


	public int getExhNo() {
		return exhNo;
	}


	public void setExhNo(int exhNo) {
		this.exhNo = exhNo;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getOrdPay() {
		return ordPay;
	}


	public void setOrdPay(String ordPay) {
		this.ordPay = ordPay;
	}


	public int getOrdAQnt() {
		return ordAQnt;
	}


	public void setOrdAQnt(int ordAQnt) {
		this.ordAQnt = ordAQnt;
	}


	public int getOrdYQnt() {
		return ordYQnt;
	}


	public void setOrdYQnt(int ordYQnt) {
		this.ordYQnt = ordYQnt;
	}


	public int getOrdCQnt() {
		return ordCQnt;
	}


	public void setOrdCQnt(int ordCQnt) {
		this.ordCQnt = ordCQnt;
	}


	public int getOrdPrice() {
		return ordPrice;
	}


	public void setOrdPrice(int ordPrice) {
		this.ordPrice = ordPrice;
	}


	public Date getOrdDate() {
		return ordDate;
	}


	public void setOrdDate(Date ordDate) {
		this.ordDate = ordDate;
	}


	public int getSeatNo() {
		return seatNo;
	}


	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}


	public int getOrdTQnt() {
		return ordTQnt;
	}


	public void setOrdTQnt(int ordTQnt) {
		this.ordTQnt = ordTQnt;
	}


	public String getExhName() {
		return exhName;
	}


	public void setExhName(String exhName) {
		this.exhName = exhName;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserTel() {
		return userTel;
	}


	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}


	public Date getExhStrDate() {
		return exhStrDate;
	}


	public void setExhStrDate(Date exhStrDate) {
		this.exhStrDate = exhStrDate;
	}


	public Date getExhEndDate() {
		return exhEndDate;
	}


	public void setExhEndDate(Date exhEndDate) {
		this.exhEndDate = exhEndDate;
	}

	

	
	
}