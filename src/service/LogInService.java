package service;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class LogInService  {
	
	public Connection connection;
	public static final String URL ="jdbc:oracle:thin:@localhost:1521:xe";
	public static final String USERID ="COMMUNITYDATA";
	public static final String PASSWORD ="COMMUNITYDATA";
	public Statement statement;
	
	public LogInService() {
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
	
	public Boolean ckCustomer(String nickName) {
		// sign UP �� ���̵� �ߺ� Ȯ��
		//input: String type, nickName
		//output: boolean type. if it is duplicated, return ture. 
		String query = "SELECT ID, USERID FROM CUSTOMER WHERE USERID LIKE \'" + nickName + "\'";
		ResultSet result_set = null;

		try {
			
			this.statement = this.connection.createStatement();
			
//			System.out.println("DB �������� ������ �߾��.");
			System.out.println();
			result_set = this.statement.executeQuery(query);
			
			if(result_set.next()) {
				return true;
			}else {
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			System.out.println("DB �������� ������ �� ���¾��.");
			System.out.println();
			return true;
		}
	}

	public HashMap<String, String> signIn(String nickName, String password) {
		
		// �α��� ���. ���� ������ ���� ��쿡 ture
		//input: String id, String password
		//output: signIn success or not
		String query = "SELECT ID, userid, password FROM CUSTOMER WHERE USERID LIKE \'" + nickName + "\' AND PASSWORD LIKE \'" + password + "\'";
		ResultSet result_set = null;
		HashMap<String,String> userInfo  = null;

		try {
			
			this.statement = this.connection.createStatement();
			
//			System.out.println("DB �������� ������ �߾��.");
			System.out.println();
			result_set = this.statement.executeQuery(query);
			
			if(result_set.next()) {
				userInfo = new HashMap<String, String>();
				userInfo.put("ID", result_set.getString(1));
				return userInfo;
			}else {
				System.out.println("Access denied");
				return userInfo;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			System.out.println("DB �������� ������ �� ���¾��.");
			System.out.println();
			return userInfo;
		}
	}

	public void findUserIdByNameAndPhone(String name, String phoneNumber){
		String query = "SELECT USERID FROM CUSTOMER WHERE NAME LIKE \'" + name + "\' AND PHONE LIKE \'" + phoneNumber + "\'";
		ResultSet result_set = null;

		try {
			
			this.statement = this.connection.createStatement();
			
//			System.out.println("DB �������� ������ �߾��.");
			System.out.println();
			result_set = this.statement.executeQuery(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			System.out.println("DB �������� ������ �� ���¾��.");
			System.out.println();
		}
		
//		��´�
		try {
			while(result_set.next()) {
				System.out.print("����� ���� ���̵�� \'");
				System.out.print(result_set.getString(1) + "\' �Դϴ�.");
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void findPasswordByUserIdAndNameAndPhone(String userId,String userName, String  userPhone) {
		String query = "SELECT PASSWORD FROM CUSTOMER WHERE USERID LIKE \'"+ userId + "\' AND NAME LIKE \'" + userName + "\' AND PHONE LIKE \'" + userPhone + "\'";
		ResultSet result_set = null;

		try {
			
			this.statement = this.connection.createStatement();
			
//			System.out.println("DB �������� ������ �߾��.");
			System.out.println();
			result_set = this.statement.executeQuery(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			System.out.println("DB �������� ������ �� ���¾��.");
			System.out.println();
		}
		
//		��´�
		try {
			while(result_set.next()) {
				System.out.print("����� ���� ��й�ȣ�� \'");
				System.out.print(result_set.getString(1) + "\' �Դϴ�.");
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
