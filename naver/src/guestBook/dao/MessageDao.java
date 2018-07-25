package guestBook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import guestBook.model.Message;
import jdbc.JdbcUtil;

public class MessageDao {

	// Dao클래스는 기능 클래스이다 --> 여러개의 인스턴스 생성이 불필요.
	// 결론 --> 싱글톤 패턴으로 처리

	// 1. 생성자를 은닉 --> 외부클래스에서 생성자 호출불가 -->클래스 생성 불가
	private MessageDao() {
	}

	// 2. 인스턴스 생성 : 1개 생성하고 공유해서 사용
	private static MessageDao instance = new MessageDao();

	// 3. 외부에서 MessageDao.getInstance()로 호출해서 사용가능
	public static MessageDao getInstance() {
		return instance;
	}

	public int insert(Connection conn, Message message) throws SQLException {

		PreparedStatement pstmt = null;
		String sql = "insert into guestbook_message (message_id, guest_name, message) values (message_id_seq.NEXTVAL, ?,  ?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, message.getGuestName());
			pstmt.setString(2, message.getMessage());
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}

	}

	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from guestbook_message");
			rs.next();
			return rs.getInt(1);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	public List<Message> selectList(Connection conn, int firstRow, int endRow) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 이렇게하면 select하는 양을 줄일수 있을듯? 많은양을 돌릴때는 이렇게 짜는게 좋을것같다.
			// pstmt = conn.prepareStatement(
			// "select message_id, guest_name, password, message from ( "
			// + " select rownum rnum, message_id, guest_name, password, message from ( "
			// + " select * from guestbook_message m order by m.message_id desc "
			// + " ) where rownum <= ? " + ") where rnum >= ?");

			// 이렇게하면 보기 좋을듯? 비트윈으로 한번에 처리하니 가독성이 좋은것같다.
			pstmt = conn.prepareStatement(
					"select * from " + " (select rownum rnum, message_id, guest_name, password, message from "																										
				+ " (select * from guestbook_message order by message_id desc))" 
				+ " where rnum between ? and ?");
			pstmt.setInt(1, firstRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				List<Message> messageList = new ArrayList<Message>();
				do {
					messageList.add(makeMessageFromResultSet(rs));// rs를 넘겨서 처리
				} while (rs.next());
				return messageList;
			} else {
				return Collections.emptyList();
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public Message select(Connection conn, int messageId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from guestbook_message where message_id = ?");
			pstmt.setInt(1, messageId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return makeMessageFromResultSet(rs);
			} else {
				return null;
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public int delete(Connection conn, int messageId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("delete from guestbook_message where message_id = ?");
			pstmt.setInt(1, messageId);
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

	}

	public Message makeMessageFromResultSet(ResultSet rs) throws SQLException {
		Message message = new Message();
		message.setId(rs.getInt("message_id"));
		message.setGuestName(rs.getString("guest_name"));
		message.setPassword(rs.getString("password"));
		message.setMessage(rs.getString("message"));
		return message;
	}

}
