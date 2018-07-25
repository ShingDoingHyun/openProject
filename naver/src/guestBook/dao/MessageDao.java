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

	// DaoŬ������ ��� Ŭ�����̴� --> �������� �ν��Ͻ� ������ ���ʿ�.
	// ��� --> �̱��� �������� ó��

	// 1. �����ڸ� ���� --> �ܺ�Ŭ�������� ������ ȣ��Ұ� -->Ŭ���� ���� �Ұ�
	private MessageDao() {
	}

	// 2. �ν��Ͻ� ���� : 1�� �����ϰ� �����ؼ� ���
	private static MessageDao instance = new MessageDao();

	// 3. �ܺο��� MessageDao.getInstance()�� ȣ���ؼ� ��밡��
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
			// �̷����ϸ� select�ϴ� ���� ���ϼ� ������? �������� �������� �̷��� ¥�°� �����Ͱ���.
			// pstmt = conn.prepareStatement(
			// "select message_id, guest_name, password, message from ( "
			// + " select rownum rnum, message_id, guest_name, password, message from ( "
			// + " select * from guestbook_message m order by m.message_id desc "
			// + " ) where rownum <= ? " + ") where rnum >= ?");

			// �̷����ϸ� ���� ������? ��Ʈ������ �ѹ��� ó���ϴ� �������� �����Ͱ���.
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
					messageList.add(makeMessageFromResultSet(rs));// rs�� �Ѱܼ� ó��
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
