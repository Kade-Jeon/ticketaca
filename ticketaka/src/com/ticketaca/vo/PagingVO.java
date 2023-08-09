package com.ticketaca.vo;

public class PagingVO {

	public PagingVO() {
	}
	
	// paging
	private int totalCount = 0;	// 전체 게시글 수
	private int pageNum = 0;	// 현재 조회하는 페이지 넘버
	private int currentPage = 0;	// 현재 페이지 수
	private int totalPage = 0;	// 전체 페이지 수
	
	// pagination
	private int displayPage = 10;	// 한 페이지에 몇 개의 페이지를 보일 것인지
	private int displayRow = 10; // 한 페이지에 몇개의 로우
	private int beginPage = 1;	// 페이징 시작 페이지 수
	private int endPage = 0;	// 페이징 종료 페이지 수
	private boolean prev, next;	// 이전, 다음 버튼 활성화 여부
	
	// 전체 변수에 대한 getter, setter
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getDisplayPage() {
		return displayPage;
	}
	public void setDisplayPage(int displayPage) {
		this.displayPage = displayPage;
	}
	public int getDisplayRow() {
		return displayRow;
	}
	public void setDisplayRow(int displayRow) {
		this.displayRow = displayRow;
	}
	public int getBeginPage() {
		return beginPage;
	}
	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	@Override
	public String toString() {
		return "PagingVO [totalCount=" + totalCount + ", currentPage=" + currentPage + ", totalPage=" + totalPage
				+ ", displayPage=" + displayPage + ", displayRow=" + displayRow + ", beginPage=" + beginPage
				+ ", endPage=" + endPage + "]";
	}
}
