package member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import guestBook.model.MessageListView;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDAO;
import member.model.MemberInfo;
import member.model.MemberInfoList;

public class GetMemberListService {
	private GetMemberListService() {
	}

	private static GetMemberListService instance = new GetMemberListService();

	public static GetMemberListService getInstance() {
		return instance;
	}
	
	// 한 페이지에 보여줄 메시지의 수
		private static final int MESSAGE_COUNT_PER_PAGE = 10;

		public MemberInfoList getMessageList(int pageNumber) throws ServiceException {
			Connection conn = null;
			
			int currentPageNumber = pageNumber > 0 ? pageNumber : 1; //이동하는 페이지가 음수거나 0으로 이동시 자동으로 첫페이지1로 변경해준다.
			try {
				conn = ConnectionProvider.getConnection();
				MemberDAO memberDAO = MemberDAO.getInstance();
				// 전체 메시지 구하기
				int messageTotalCount = memberDAO.selectCount(conn);//전체 메세지 갯수 구하기
				List<MemberInfo> memberList = null;
				int firstRow = 0;
				int endRow = 0;
				if (messageTotalCount > 0) {
					firstRow = (pageNumber - 1) * MESSAGE_COUNT_PER_PAGE + 1;	//(페이지-1) * 페이지별 메세지 크기+1 1~4~7
					endRow = firstRow + MESSAGE_COUNT_PER_PAGE - 1;//(첫메세지 위치) + 페이지별 메세지 크기 3~6~9
					memberList = memberDAO.selectList(conn, firstRow, endRow);
				} else {
					currentPageNumber = 0;
					memberList = Collections.emptyList();//null이 아닌 size를 0으로 반환
				}
				return new MemberInfoList(memberList, messageTotalCount, currentPageNumber, MESSAGE_COUNT_PER_PAGE, firstRow, endRow);
			} catch (SQLException e) {
				throw new ServiceException("메시지 목록 구하기 실패: " + e.getMessage(), e);
			} finally {
				JdbcUtil.close(conn);
			}
		}
		
		public MemberInfoList getMessageList() throws ServiceException {
			Connection conn = null;
			try {
				conn = ConnectionProvider.getConnection();
				MemberDAO memberDAO = MemberDAO.getInstance();
				List<MemberInfo> memberList = null;
				memberList = memberDAO.selectList(conn);
				return new MemberInfoList(memberList);
			} catch (SQLException e) {
				throw new ServiceException("메시지 목록 구하기 실패: " + e.getMessage(), e);
			} finally {
				JdbcUtil.close(conn);
			}
		}
		
		
}
