package member.model;

import java.util.List;


public class MemberInfoList {
	private int messageTotalCount;// 전체 메세지수
	private int currentPageNumber;// 현재 페이지
	private List<MemberInfo>  memberInfoList;
	private int pageTotalCount;// 전체 페이지수
	private int messageCountPerPage;//페이자마다 메세지 갯수
	private int firstRow;//페이지별 1번 메세지
	private int endRow;//페이지별 마지막 메세지
	
	public MemberInfoList( List<MemberInfo> memberInfoList,int messageTotalCount, int currentPageNumber,
			 int messageCountPerPage, int firstRow, int endRow) {
		super();
		this.messageTotalCount = messageTotalCount;
		this.currentPageNumber = currentPageNumber;
		this.memberInfoList = memberInfoList;
		this.messageCountPerPage = messageCountPerPage;
		this.firstRow = firstRow;
		this.endRow = endRow;
		calculatePageTotalCount();
	}


	public MemberInfoList(List<MemberInfo> memberInfoList) {
		this.memberInfoList = memberInfoList;
	}


	private void calculatePageTotalCount() {
		if (messageTotalCount == 0) {
			pageTotalCount = 0;//메세지가없으면 페이지길이는 0이된다.
		} else {
			pageTotalCount = messageTotalCount / messageCountPerPage;//페이지 전체 갯수를 전체페이지/메세지 갯수로  설정한다. ex) 5/3=1 pTC=1
			if (messageTotalCount % messageCountPerPage > 0) {//전체페이지에 메세지 객수로 나눈값이 있으면  페이지갯수를 1 더한다. ex) 5%3=2  pTC=2, 전체페이지는 2
				pageTotalCount++;
			}
		}
	}

	public int getMessageTotalCount() {
		return messageTotalCount;
	}

	public int getCurrentPageNumber() {
		return currentPageNumber;
	}

	public List<MemberInfo> getMemberInfoList() {
		return memberInfoList;
	}

	public int getPageTotalCount() {
		return pageTotalCount;
	}

	public int getMessageCountPerPage() {
		return messageCountPerPage;
	}

	public int getFirstRow() {
		return firstRow;
	}

	public int getEndRow() {
		return endRow;
	}


	@Override
	public String toString() {
		return "MemberInfoList [messageTotalCount=" + messageTotalCount + ", currentPageNumber=" + currentPageNumber
				+ ", memberInfoList=" + memberInfoList + ", pageTotalCount=" + pageTotalCount + ", messageCountPerPage="
				+ messageCountPerPage + ", firstRow=" + firstRow + ", endRow=" + endRow + "]";
	}
	
	

	
	
}
