package com.bitcamp.op.guestbook.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import com.bitcamp.op.guestbook.model.Message;

public interface MessageDao {

	public int insert(Connection conn, Message message);

	public int selectCount(Connection conn);

	public List<Message> selectList(Connection conn, int firstRow, int endRow);

	public Message select(Connection conn, int messageId);

	public int delete(Connection conn, int messageId);

	public Message makeMessageFromResultSet(ResultSet rs);
}
