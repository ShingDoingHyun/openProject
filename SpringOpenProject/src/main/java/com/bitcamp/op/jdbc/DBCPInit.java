package com.bitcamp.op.jdbc;

import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class DBCPInit extends HttpServlet {

	@Override
	public void init() throws ServletException {
		loadJDBCDriver();
		initConnectionPool();
	}

	// �����ͺ��̽��� ����̹� �ε�
	private void loadJDBCDriver() {

		try {

			// ����ϰ����ϴ� �����ͺ��̽� ����̹� �ε�
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("연결완료!!");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// POOL ����̹� �ε�, ����, Ǯ ���
	private void initConnectionPool() {

		try {

			String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			String username = "test";
			String pw = "1234";

			// Ŀ�ؼ�Ǯ�� ���ο� Ŀ�ؼ��� ������ �� ����� Ŀ�ؼ����丮�� ����.
			ConnectionFactory connFactory = new DriverManagerConnectionFactory(jdbcUrl, username, pw);

			///////////////////////////////////////////////////////////////////////////

			// PoolableConnection�� �����ϴ� ���丮 ����.
			// DBCP�� Ŀ�ؼ��� ������ �� PoolableConnection �� ���
			// ���� Ŀ�ؼ��� ��� ��������, Ŀ�ؼ� Ǯ�� �����ϴµ� �ʿ��� ����� �����Ѵ�.
			// Ŀ�ؼ��� close�ϸ� �������� �ʰ� Ŀ�ؼ� Ǯ�� ��ȯ
			PoolableConnectionFactory poolableConnFactory = new PoolableConnectionFactory(connFactory, null);
			// Ŀ�ؼ��� ��ȿ���� ���θ� �˻��� �� ����ϴ� ������ �����Ѵ�.
			poolableConnFactory.setValidationQuery("select 1");

			/////////////////////////////////////////////////////////////////

			// Ŀ�ؼ� Ǯ�� ���� ������ �����Ѵ�.
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			// ���� Ŀ�ؼ� �˻� �ֱ�
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);
			// Ǯ�� �������� Ŀ�ؼ��� ��ȿ���� �˻����� ���� ����
			poolConfig.setTestWhileIdle(true);
			// Ŀ�ؼ� �ּ� ����
			poolConfig.setMinIdle(4);
			// Ŀ�ؼ� �ִ� ����
			poolConfig.setMaxTotal(50);

			//////////////////////////////////////////////////////////////////////

			// Ŀ�ؼ� Ǯ�� ����. �����ڴ� PoolabeConnectionFactory�� GenericObjectPoolConfig�� ���
			GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<PoolableConnection>(poolableConnFactory,
					poolConfig);
			// PoolabeConnectionFactory���� Ŀ�ؼ� Ǯ�� ����
			poolableConnFactory.setPool(connectionPool);

			////////////////////////////////////////////////////////////////////////

			// Ŀ�ؼ� Ǯ�� �����ϴ� jdbc ����̹��� ���.
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			// ������ Ŀ�ؼ� Ǯ ����̹��� ������ Ŀ�ؼ� Ǯ�� ����Ѵ�. �̸��� pooltest�̴�.
			driver.registerPool("pooltest", connectionPool);
			
			System.out.println("pooltest 커넥션 풀 완료!!");
			
			
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	
	
	
	
	
	
	

}
