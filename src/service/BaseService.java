package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import ifs.ServiceIfs;

public abstract class BaseService implements ServiceIfs{
	
	public Connection connection;
	public static final String URL ="jdbc:oracle:thin:@localhost:1521:xe";
	public static final String USERID ="COMMUNITYDATA";
	public static final String PASSWORD ="COMMUNITYDATA";
	public Statement statement;
	
	// �߻�Ŭ���� ������ ����
	public BaseService() {
//		����̹� �ε�
		super();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
//			System.out.printf("%s \r\n", "����̹��� �ε� �Ǿ����ϴ�.");
			System.out.println();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			System.out.printf("%s \r\n", "����̹��� �ε� �� �Ǿ����ϴ�.");
			System.out.println();
		}
		
//		DB ����
		try {
			this.connection = DriverManager.getConnection(URL, USERID, PASSWORD);
//			System.out.printf("%s \r\n", "������ ���̽� ���� ����");
			System.out.println();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			System.out.printf("%s \r\n", "������ ���̽� ���� �񼺰�");
			System.out.println();
		}

	}
	
	public ResultSet runSQL(String paramQquery) {
		String query = paramQquery;
		ResultSet result_set = null;

		try {
			
			this.statement = this.connection.createStatement();
			
//			System.out.println("DB �������� ������ �߾��.");
			System.out.println();
			result_set = this.statement.executeQuery(query);
			
			//--check. �Ӽ� ���� ���
			ResultSetMetaData rsmd = result_set.getMetaData();
			System.out.println(rsmd.getColumnCount());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			System.out.println("DB �������� ������ �� ���¾��.");
			System.out.println();
			
		}
		
		return result_set;
	}
	
	public void disconnectionDB() {
		try {
			this.connection.close();
//			System.out.printf("%s \r\n", "DB ���� ���������� ����");
			System.out.println();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			System.out.printf("%s \r\n", "DB ���� ���� ����");
			System.out.println();
		}
	}

}
