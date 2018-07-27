package com.bitcamp.op.member.model;

import java.util.List;


public class MemberInfoList {
	private int messageTotalCount;// ��ü �޼�����
	private int currentPageNumber;// ���� ������
	private List<MemberInfo>  memberInfoList;
	private int pageTotalCount;// ��ü ��������
	private int messageCountPerPage;//�����ڸ��� �޼��� ����
	private int firstRow;//�������� 1�� �޼���
	private int endRow;//�������� ������ �޼���
	
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
			pageTotalCount = 0;//�޼����������� ���������̴� 0�̵ȴ�.
		} else {
			pageTotalCount = messageTotalCount / messageCountPerPage;//������ ��ü ������ ��ü������/�޼��� ������  �����Ѵ�. ex) 5/3=1 pTC=1
			if (messageTotalCount % messageCountPerPage > 0) {//��ü�������� �޼��� ������ �������� ������  ������������ 1 ���Ѵ�. ex) 5%3=2  pTC=2, ��ü�������� 2
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
