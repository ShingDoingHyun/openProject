package com.bitcamp.op.emp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bitcamp.op.emp.model.EmpDTO;
import com.bitcamp.op.emp.model.EmpDetailDTO;
import com.bitcamp.op.jdbc.JdbcUtil;

public class EmpDaoEmpl implements EmpDao {

	@Override
	public List<EmpDetailDTO> selectList(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		pstmt = conn.prepareStatement(
				"select e.empno, e.ename, e.job, ee.ename, e.hiredate, e.sal, nvl(e.comm, 0), e.deptno, d.dname, d.loc "
						+ " from emp e, emp ee, dept d " + " where e.mgr=ee.empno and e.deptno=d.deptno "
						+ " order by e.ename ");

		try {
			rs = pstmt.executeQuery();
			if (rs.next()) {
				List<EmpDetailDTO> memberList = new ArrayList<EmpDetailDTO>();
				do {
					memberList.add(makeEmpFromResultSet(rs));// rs�� �Ѱܼ� ó��
				} while (rs.next());
				return memberList;
			} else {
				return Collections.emptyList();
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private EmpDetailDTO makeEmpFromResultSet(ResultSet rs) throws SQLException {
		EmpDetailDTO empDetailDTO = new EmpDetailDTO();
		empDetailDTO.setEmpno(rs.getInt(1));
		empDetailDTO.setEname(rs.getString(2));
		empDetailDTO.setJob(rs.getString(3));
		empDetailDTO.setMgr(rs.getString(4));
		empDetailDTO.setHiredate(rs.getString(5));
		empDetailDTO.setSal(rs.getInt(6));
		empDetailDTO.setComm(rs.getInt(7));
		empDetailDTO.setDeptno(rs.getInt(8));
		empDetailDTO.setDname(rs.getString(9));
		empDetailDTO.setLoc(rs.getString(10));
		return empDetailDTO;
	}

}
