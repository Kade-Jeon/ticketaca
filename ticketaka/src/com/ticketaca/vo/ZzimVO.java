package com.ticketaca.vo;

import java.sql.Date;

public class ZzimVO {
	private int zzNo;
	private Date zzDate;
	private int exhNo;
	private String exhName;
	private String exhPlace;
	private String exhAddress;
	private Date exhStrDate;
	private Date exhEndDate;
	private String exhAbleBuy;
	private String exhThumbImg;
	
	public ZzimVO() {
	}//기본생성자

	public ZzimVO(int zzNo, Date zzDate, int exhNo, String exhName, String exhPlace, String exhAddress, Date exhStrDate,
			Date exhEndDate, String exhAbleBuy, String exhThumbImg) {
		super();
		this.zzNo = zzNo;
		this.zzDate = zzDate;
		this.exhNo = exhNo;
		this.exhName = exhName;
		this.exhPlace = exhPlace;
		this.exhAddress = exhAddress;
		this.exhStrDate = exhStrDate;
		this.exhEndDate = exhEndDate;
		this.exhAbleBuy = exhAbleBuy;
		this.exhThumbImg = exhThumbImg;
	}//overloading 생성자



	public int getZzNo() {
		return zzNo;
	}

	public void setZzNo(int zzNo) {
		this.zzNo = zzNo;
	}


	public int getExhNo() {
		return exhNo;
	}

	public void setExhNo(int exhNo) {
		this.exhNo = exhNo;
	}

	public Date getZzDate() {
		return zzDate;
	}

	public void setZzDate(Date zzDate) {
		this.zzDate = zzDate;
	}

	public String getExhName() {
		return exhName;
	}

	public void setExhName(String exhName) {
		this.exhName = exhName;
	}

	public String getExhPlace() {
		return exhPlace;
	}

	public void setExhPlace(String exhPlace) {
		this.exhPlace = exhPlace;
	}

	public String getExhAddress() {
		return exhAddress;
	}

	public void setExhAddress(String exhAddress) {
		this.exhAddress = exhAddress;
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

	public String getExhAbleBuy() {
		return exhAbleBuy;
	}

	public void setExhAbleBuy(String exhAbleBuy) {
		this.exhAbleBuy = exhAbleBuy;
	}

	public String getExhThumbImg() {
		return exhThumbImg;
	}

	public void setExhThumbImg(String exhThumbImg) {
		this.exhThumbImg = exhThumbImg;
	}

	@Override
	public String toString() {
		return "ZzimVO [zzNo=" + zzNo + ", zzDate=" + zzDate + ", exhNo=" + exhNo + ", exhName=" + exhName
				+ ", exhPlace=" + exhPlace + ", exhAddress=" + exhAddress + ", exhStrDate=" + exhStrDate
				+ ", exhEndDate=" + exhEndDate + ", exhAbleBuy=" + exhAbleBuy + ", exhThumbImg=" + exhThumbImg + "]";
	}

	
}
