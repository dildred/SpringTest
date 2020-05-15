package com.project.emp.other;

public class AutoPaging {
	/**현재 페이지*/
	private int page;
	/**한 페이지에 쓸 게시글의 갯수7*/
	private int limit; 
	private int startPage; //현재 페이지에서 보이는 가장 처음 숫자(1 2 3 4 5 6 7 8 9 10 에서 1을 담당함)
	private int endPage; //현재 페이지에서 보이는 가장 마지막 숫자(11 12 13 14 15 16 17 18 19 20에서 20을 담당함)
	private int maxPage; //최종 페이지
	private int limitA; //A개부터
	private int limitB; //B개까지
	private int pageCount; //현재 페이지에서 보이는 처음 숫자와 마지막 숫자 사이의 숫자 개수 (1 2 3 4 5 6 7) => 7개
	private int listCount; //총 게시글의 갯수7
	private boolean prev; //이전 페이지의 유무를 확인하는 값
	private boolean next; //다음 페이지의 유무를 확인하는 값
	private int prevPageNum; //이전 페이지의 페이지 번호
	private int nextPageNum; //다음 페이지의 페이지 번호
	
	/**생성자 생성시 현재 페이지와 페이지의 개수, 페이지 사이의 개수를 받아오게 함.<br>
	예시 1~10까지 3페이지에서 불러오게 하고 싶고 개수는 한 페이지당 게시글 20개만 불러오고 싶을 때<br>
	AutoPaging paging = new AutoPaging(3,20,10);
	@param page 현재 페이지
	@param limit 한 페이지의 게시글 개수
	@param pageConunt 한 사이트에 보이는 첫 페이지와 마지막 페이지 사이의 개수
	*/
	public AutoPaging(int page, int limit, int pageCount) {
		this.page = page;
		this.limit = limit;
		this.pageCount = pageCount;
		setAutoStartPage();
	}
	
	public boolean isPrev() {
		return prev;
	}

	public AutoPaging setPrev(boolean prev) {
		this.prev = prev;
		return this;
	}

	public boolean isNext() {
		return next;
	}

	public AutoPaging setNext(boolean next) {
		this.next = next;
		return this;
	}

	public int getPage() {
		return page;
	}
	public AutoPaging setPage(int page) {
		this.page = page;
		return this;
	}
	public int getLimit() {
		return limit;
	}
	public AutoPaging setLimit(int limit) {
		this.limit = limit;
		return this;
	}
	public int getStartPage() {
		return startPage;
	}
	public AutoPaging setStartPage(int startPage) {
		this.startPage = startPage;
		return this;
	}
	public int getEndPage() {
		return endPage;
	}
	public AutoPaging setEndPage(int endPage) {
		this.endPage = endPage;
		return this;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public AutoPaging setMaxPage(int maxPage) {
		this.maxPage = maxPage;
		return this;
	}
	public int getPageCount() {
		return pageCount;
	}
	public AutoPaging setPageCount(int pageCount) {
		this.pageCount = pageCount;
		return this;
	}
	public int getListCount() {
		return listCount;
	}
	//리스트 개수를 쿼리문에서 불러와서 여기에 저장시키면 끝
	public AutoPaging setListCount(int listCount) {
		this.listCount = listCount;
		limitA = ((page-1)*limit);
		limitB = limit;
		if(((page)*limit) > listCount) {
			limitB = listCount%limit;
		}
		return setAutoMaxPage();
	}
	
	//오토 페이징(StartPage후 자동으로 EndPage까지 해줌)
	public AutoPaging setAutoStartPage() {
		startPage = (int)(((double)page/pageCount)-0.05)*pageCount+1;
		return setAutoEndPage();
	}
	public AutoPaging setAutoEndPage() {
		endPage = (startPage+pageCount-1);
		return this;
	}
	//ListCount이후 MaxPage까지 잡아줌
	private AutoPaging setAutoMaxPage() {
		// TODO Auto-generated method stub
		maxPage = (int)(((double)listCount/limit)+0.99);
		return endMaxisBig();
	}
	//MaxPage가 EndPage보다 작을 때
	private AutoPaging endMaxisBig() {
		// TODO Auto-generated method stub
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		return isNextPrev();
	}
	//다음페이지와 이전페이지가 필요한지 유무를 만들어줌
	private AutoPaging isNextPrev() {
		if(startPage != 1) {
			prev = true;
			prevPageNum = startPage-1;
		} else {
			prev = false;
		}
		if(endPage < maxPage) {
			next = true;
			nextPageNum = endPage+1;
		} else {
			next = false;
		}
		
		return this;
	}

	public int getLimitA() {
		return limitA;
	}

	public void setLimitA(int limitA) {
		this.limitA = limitA;
	}

	public int getLimitB() {
		return limitB;
	}

	public void setLimitB(int limitB) {
		this.limitB = limitB;
	}
	
    public int getPrevPageNum() {
        return prevPageNum;
    }
    
    public void setPrevPageNum(int prevPageNum) {
        this.prevPageNum = prevPageNum;
    }

    public int getNextPageNum() {
        return nextPageNum;
    }

    public void setNextPageNum(int nextPageNum) {
        this.nextPageNum = nextPageNum;
    }

    @Override
    public String toString() {
        return "AutoPaging [page=" + page + ", limit=" + limit + ", startPage=" + startPage + ", endPage=" + endPage
                + ", maxPage=" + maxPage + ", limitA=" + limitA + ", limitB=" + limitB + ", pageCount=" + pageCount
                + ", listCount=" + listCount + ", prev=" + prev + ", next=" + next + "]";
    }
	
	
	
}
