package com.bitcamp.op.emp.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.bitcamp.op.emp.dao.EmpDao;
import com.bitcamp.op.emp.model.EmpDetailDTO;
import com.bitcamp.op.jdbc.ConnectionProvider;
import com.bitcamp.op.jdbc.JdbcUtil;

public class EmpListService {

	EmpDao empDao;

	public void setEmpDao(EmpDao empDao) {
		this.empDao = empDao;
	}

	public List<EmpDetailDTO> empList() throws SQLException {

		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			return empDao.selectList(conn);
		} finally {
			JdbcUtil.close(conn);
		}

	}

}
