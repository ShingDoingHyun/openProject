package member.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import guestBook.model.Message;
import jdbc.JdbcUtil;
import member.model.MemberInfo;

public class MemberDAO {
	private MemberDAO() {
	}

	// 2. �ν��Ͻ� ���� : 1�� �����ϰ� �����ؼ� ���
	private static MemberDAO instance = new MemberDAO();

	// 3. �ܺο��� MessageDao.getInstance()�� ȣ���ؼ� ��밡��
	public static MemberDAO getInstance() {
		return instance;
	}

	public int insertMember(Connection conn, MemberInfo memberInfo) throws SQLException {
		System.out.println(memberInfo.getUserid());
		PreparedStatement pstmt = null;
		String sql = "insert into member values (?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberInfo.getUserid());
			pstmt.setString(2, memberInfo.getPassword());
			pstmt.setString(3, memberInfo.getName());
			pstmt.setDate(4, Date.valueOf(memberInfo.getBirthday()));
			pstmt.setBoolean(5, memberInfo.isGender());
			pstmt.setString(6, memberInfo.getEmail());
			pstmt.setString(7, memberInfo.getPhone());
			pstmt.setString(8, memberInfo.getPhoto());
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
			rs = stmt.executeQuery("select count(*) from member");
			rs.next();
			return rs.getInt(1);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	public List<MemberInfo> selectList(Connection conn, int firstRow, int endRow) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = conn.prepareStatement("select userid, password, NAME, birthday, gender, email, phone, photo  from ( "
					+ " select rownum rnum, userid, password, NAME, birthday, gender, email, phone, photo from ( "
					+ " select * from member m order by m.userid desc " + " ) where rownum <= ? "
					+ ") where rnum >= ?");

			pstmt.setInt(1, endRow);
			pstmt.setInt(2, firstRow);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				List<MemberInfo> memberList = new ArrayList<MemberInfo>();
				do {
					memberList.add(memberResultSet(rs));// rs�� �Ѱܼ� ó��
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

	public List<MemberInfo> selectList(Connection conn) throws SQLException{
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from member");
			if (rs.next()) {
				List<MemberInfo> memberList = new ArrayList<MemberInfo>();
				do {
					memberList.add(memberResultSet(rs));// rs�� �Ѱܼ� ó��
				} while (rs.next());
				return memberList;
			} else {
				return Collections.emptyList();
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	public int deleteMember(Connection conn, String userid) throws SQLException {
		PreparedStatement pstmt = null;
		String sql_update = "delete from member where userid=?";

		try {
			//3. Statement ����
			pstmt = conn.prepareStatement(sql_update);
			pstmt.setString(1, userid);
			return  pstmt.executeUpdate();
			//4. sql ����
		}finally {
			JdbcUtil.close(pstmt);
		}
		
	}

	public MemberInfo memberResultSet(ResultSet rs) throws SQLException {
		MemberInfo memberInfo = new MemberInfo();
		memberInfo.setUserid(rs.getString("userid"));
		memberInfo.setPassword(rs.getString("password"));
		memberInfo.setName(rs.getString("name"));
		memberInfo.setBirthday(rs.getString("birthday"));
		memberInfo.setGender(rs.getBoolean("gender"));
		memberInfo.setEmail(rs.getString("email"));
		memberInfo.setPhone(rs.getString("phone"));
		memberInfo.setPhoto(rs.getString("photo"));
		return memberInfo;
	}



}
