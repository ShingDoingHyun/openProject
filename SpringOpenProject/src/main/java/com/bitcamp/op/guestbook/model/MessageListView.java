package com.bitcamp.op.guestbook.model;

import java.util.List;

public class MessageListView {
	private int messageTotalCount;// ��ü �޼�����
	private int currentPageNumber;// ���� ������
	private List<Message> messageList;// �������� �޼�����
	private int pageTotalCount;// ��ü ��������
	private int messageCountPerPage;//�����ڸ��� �޼��� ����
	private int firstRow;//�������� 1�� �޼���
	private int endRow;//�������� ������ �޼���

	public MessageListView(List<Message> messageList, int messageTotalCount, int currentPageNumber,
			int messageCountPerPage, int startRow, int endRow) {
		this.messageList = messageList;
		this.messageTotalCount = messageTotalCount;
		this.currentPageNumber = currentPageNumber;
		this.messageCountPerPage = messageCountPerPage;
		this.firstRow = startRow;
		this.endRow = endRow;
		calculatePageTotalCount();
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

	//�����ڷ� ���� �޾ƿ��� ������ setter�� �ʿ䰡 ����.
	public int getMessageTotalCount() {
		return messageTotalCount;
	}

	public int getCurrentPageNumber() {
		return currentPageNumber;
	}

	public List<Message> getMessageList() {
		return messageList;
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

	public boolean isEmpty() {
		return messageTotalCount == 0;
	}

}
