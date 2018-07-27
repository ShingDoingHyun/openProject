package com.bitcamp.op.guestbook.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import com.bitcamp.op.guestbook.model.Message;

public class MessageDaoImpl implements MessageDao {

	@Override
	public int insert(Connection conn, Message message) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectCount(Connection conn) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Message> selectList(Connection conn, int firstRow, int endRow) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message select(Connection conn, int messageId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Connection conn, int messageId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Message makeMessageFromResultSet(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

}
