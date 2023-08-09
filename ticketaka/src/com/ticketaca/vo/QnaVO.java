package com.ticketaca.vo;

import java.util.Date;

public class QnaVO {
	private int Level;			//트리 구조 계층 번호
	private int QNo;			//글 번호
	private int PNo;			//답변번호(정렬용)
	private String QCate;		//질문 카테고리
	private int ENo;			//상품 번호
	private String QTitle;		//QnA 제목
	private Date QDate;			//문의 일자
	private String UId;			//회원 ID
	private String QContent;	//문의 내용
	private String URead;		//유저가 읽었는지 확인
	private String ARead;		//관리자가 읽었는지 확인
	private String AAnswer;		//관리자 답변 여부
	
	public QnaVO() {
	}

	

	public QnaVO(int level, int qNo, int pNo, String qCate, String qTitle, Date qDate, String uId, String qContent) {
		super();
		Level = level;
		QNo = qNo;
		PNo = pNo;
		QCate = qCate;
		QTitle = qTitle;
		QDate = qDate;
		UId = uId;
		QContent = qContent;
	}



	public QnaVO(int level, int qNo, int pNo, String qTitle, Date qDate, String uId, String qContent, String aAnswer) {
		super();
		Level = level;
		QNo = qNo;
		PNo = pNo;
		QTitle = qTitle;
		QDate = qDate;
		UId = uId;
		QContent = qContent;
		AAnswer = aAnswer;
	}



	public QnaVO(int qNo, int pNo, String qCate, int eNo, String qTitle, Date qDate, String uId, String qContent,
			String uRead, String aRead, String aAnswer) {
		super();
		QNo = qNo;
		PNo = pNo;
		QCate = qCate;
		ENo = eNo;
		QTitle = qTitle;
		QDate = qDate;
		UId = uId;
		QContent = qContent;
		URead = uRead;
		ARead = aRead;
		AAnswer = aAnswer;
	}





	public QnaVO(String qContent) {
		super();
		QContent = qContent;
	}



	public int getLevel() {
		return Level;
	}

	public void setLevel(int level) {
		Level = level;
	}

	public int getQNo() {
		return QNo;
	}

	public void setQNo(int qNo) {
		QNo = qNo;
	}

	public int getPNo() {
		return PNo;
	}

	public void setPNo(int pNo) {
		PNo = pNo;
	}

	public String getQCate() {
		return QCate;
	}

	public void setQCate(String qCate) {
		QCate = qCate;
	}

	public int getENo() {
		return ENo;
	}

	public void setENo(int eNo) {
		ENo = eNo;
	}

	public String getQTitle() {
		return QTitle;
	}

	public void setQTitle(String qTitle) {
		QTitle = qTitle;
	}

	public Date getQDate() {
		return QDate;
	}

	public void setQDate(Date qDate) {
		QDate = qDate;
	}

	public String getUId() {
		return UId;
	}

	public void setUId(String uId) {
		UId = uId;
	}

	public String getQContent() {
		return QContent;
	}

	public void setQContent(String qContent) {
		QContent = qContent;
	}

	public String getURead() {
		return URead;
	}

	public void setURead(String uRead) {
		URead = uRead;
	}

	public String getARead() {
		return ARead;
	}

	public void setARead(String aRead) {
		ARead = aRead;
	}

	public String getAAnswer() {
		return AAnswer;
	}

	public void setAAnswer(String aAnswer) {
		AAnswer = aAnswer;
	}

	@Override
	public String toString() {
		return "QnaVO [Level=" + Level + ", QNo=" + QNo + ", PNo=" + PNo + ", QCate=" + QCate + ", ENo=" + ENo
				+ ", QTitle=" + QTitle + ", QDate=" + QDate + ", UId=" + UId + ", QContent=" + QContent + ", URead="
				+ URead + ", ARead=" + ARead + ", AAnswer=" + AAnswer + "]";
	}
	
	

	
	
}
