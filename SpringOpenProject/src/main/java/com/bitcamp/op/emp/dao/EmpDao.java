package com.bitcamp.op.emp.dao;



import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.bitcamp.op.emp.model.EmpDTO;
import com.bitcamp.op.emp.model.EmpDetailDTO;

public interface EmpDao {

	public List<EmpDetailDTO> selectList(Connection conn) throws SQLException;
	
}
