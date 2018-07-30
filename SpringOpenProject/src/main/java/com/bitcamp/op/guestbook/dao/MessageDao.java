package com.bitcamp.op.guestbook.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.bitcamp.op.guestbook.model.Message;

public interface MessageDao {


	public int insert(Connection conn, Message message) throws SQLException;

	public int selectCount(Connection conn) throws SQLException;

	public List<Message> selectList(Connection conn, int firstRow, int endRow) throws SQLException;

	public Message select(Connection conn, int messageId) throws SQLException;

	public int delete(Connection conn, int messageId) throws SQLException;

	public Message makeMessageFromResultSet(ResultSet rs) throws SQLException;

}
