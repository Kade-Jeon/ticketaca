package com.ticketaca.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;

/**
 * 전시 정보 관련 VO
 * **/
public class ExhVO {

	private int no = 1;					// 전시번호 (exh_no), 기본키
	private String name = "";			// 전시명 (exh_name)
	private String place = "";			// 전시관 이름 (exh_place)
	private String address = "";		// 전시관 주소 (exh_address)
	private String strDate;				// 시작일자 (exh_strDate)
	private String endDate;				// 종료일자 (exh_endDate)
	private int adultPrice = 0;			// 성인 가격 (exh_aPrice)
	private int youthPrice = 0;			// 청소년 가격 (exh_yPrice)
	private int childPrice = 0;			// 어린이 가격 (exh_cPrice)
	private String content = "";		// 전시 내용 (exh_content)
	private int crrCnt = 0;				// 현재 수량 (exh_crrCnt)
	private int maxCnt = 0;				// 최대 수량 (exh_maxCnt)
	private String exhStatus = "";		// 전시 상태 (exh_ableBuy)
	private String postStatus = "";		// 게시 여부 (exh_postStatus)
	private String image = "";			// 메인 이미지 (exh_thumbImg)
	private String detailImage = "";	// 상세 이미지 (exh_detailImg)
	
	public ExhVO() {
	}
	
	// 일반 리스트 출력	
	public ExhVO(int no, String name, String place, String address, String strDate, String endDate) {
		this.no = no;
		this.name = name;
		this.place = place;
		this.address = address;
		this.strDate = strDate;
		this.endDate = endDate;
	}
	
	// 상세정보를 위한 리스트 출력 (가격 포함)
	public ExhVO(int no, String name, String place, String address, String strDate, String endDate, int adultPrice, int youthPrice, int childPrice) {
		this.no = no;
		this.name = name;
		this.place = place;
		this.address = address;
		this.strDate = strDate;
		this.endDate = endDate;
		this.adultPrice = adultPrice;
		this.youthPrice = youthPrice;
		this.childPrice = childPrice;
	}
	
	// 전시 상품 등록
	public ExhVO(int no, String name, String place, String address, String strDate, String endDate, 
				int adultPrice, int youthPrice, int childPrice, String content, int maxCnt, 
				String exhStatus, String postStatus, String image, String detailImage) {
		this.no = no;
		this.name = name;
		this.place = place;
		this.address = address;
		this.strDate = strDate;
		this.endDate = endDate;
		this.adultPrice = adultPrice;
		this.youthPrice = youthPrice;
		this.childPrice = childPrice;
		this.content = content;
		this.maxCnt = maxCnt;
		this.exhStatus = exhStatus;
		this.postStatus = postStatus;
		this.image = image;
		this.detailImage = detailImage;
	}

	// 변수들의 getter 와 setter
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStrDate() {
		return strDate;
	}

	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getAdultPrice() {
		return adultPrice;
	}

	public void setAdultPrice(int adultPrice) {
		this.adultPrice = adultPrice;
	}

	public int getYouthPrice() {
		return youthPrice;
	}

	public void setYouthPrice(int youthPrice) {
		this.youthPrice = youthPrice;
	}

	public int getChildPrice() {
		return childPrice;
	}

	public void setChildPrice(int childPrice) {
		this.childPrice = childPrice;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCrrCnt() {
		return crrCnt;
	}

	public void setCrrCnt(int crrCnt) {
		this.crrCnt = crrCnt;
	}

	public int getMaxCnt() {
		return maxCnt;
	}

	public void setMaxCnt(int maxCnt) {
		this.maxCnt = maxCnt;
	}

	public String getExhStatus() {
		return exhStatus;
	}

	public void setExhStatus(String exhStatus) {
		this.exhStatus = exhStatus;
	}
	
	public String getPostStatus() {
		return postStatus;
	}

	public void setPostStatus(String postStatus) {
		this.postStatus = postStatus;
	}

	public String getImage() {
		try {
			if (image != null && image.length() != 0) {
				image = URLDecoder.decode(image, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			System.out.println("getImage() ERR : " + e.getMessage());
		}
		return image;
	}

	public void setImage(String image) {
		try {
			if(image != null && image.length() != 0) {
				this.image = URLEncoder.encode(image, "UTF-8");	// 파일 이름에 특수문자가 있을 경우 인코딩
			} else {
				this.image = image;
			}
		} catch(UnsupportedEncodingException e) {
			System.out.println("setImage() ERR : " + e.getMessage());
		}
	}

	public String getDetailImage() {
		try {
			if (detailImage != null && detailImage.length() != 0) {
				detailImage = URLDecoder.decode(detailImage, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			System.out.println("getImage() ERR : " + e.getMessage());
		}
		return detailImage;
	}

	public void setDetailImage(String detailImage) {
		try {
			if(detailImage != null && detailImage.length() != 0) {
				this.detailImage = URLEncoder.encode(detailImage, "UTF-8");	// 파일 이름에 특수문자가 있을 경우 인코딩
			} else {
				this.detailImage = detailImage;
			}
		} catch(UnsupportedEncodingException e) {
			System.out.println("setImage() ERR : " + e.getMessage());
		}
	}

	@Override
	public String toString() {
		return "ExhVO [no=" + no + ", name=" + name + ", place=" + place + ", address=" + address + ", strDate="
				+ strDate + ", endDate=" + endDate + ", adultPrice=" + adultPrice + ", youthPrice=" + youthPrice
				+ ", childPrice=" + childPrice + ", content=" + content + ", crrCnt=" + crrCnt + ", maxCnt=" + maxCnt
				+ ", exhStatus=" + exhStatus + ", postStatus=" + postStatus + ", image=" + image + ", detailImage="
				+ detailImage + "]";
	}	
}
