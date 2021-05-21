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
	
	// 추상클래스 생성자 실행
	public BaseService() {
//		드라이버 로드
		super();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
//			System.out.printf("%s \r\n", "드라이버가 로드 되었습니다.");
			System.out.println();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			System.out.printf("%s \r\n", "드라이버가 로드 안 되었습니다.");
			System.out.println();
		}
		
//		DB 연결
		try {
			this.connection = DriverManager.getConnection(URL, USERID, PASSWORD);
//			System.out.printf("%s \r\n", "데이터 베이스 연결 성공");
			System.out.println();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			System.out.printf("%s \r\n", "데이터 베이스 연결 비성공");
			System.out.println();
		}

	}
	
	public ResultSet runSQL(String paramQquery) {
		String query = paramQquery;
		ResultSet result_set = null;

		try {
			
			this.statement = this.connection.createStatement();
			
//			System.out.println("DB 서버에서 응답을 했어요.");
			System.out.println();
			result_set = this.statement.executeQuery(query);
			
			//--check. 속성 갯수 출력
			ResultSetMetaData rsmd = result_set.getMetaData();
			System.out.println(rsmd.getColumnCount());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			System.out.println("DB 서버에서 응답을 안 보냈어요.");
			System.out.println();
			
		}
		
		return result_set;
	}
	
	public void disconnectionDB() {
		try {
			this.connection.close();
//			System.out.printf("%s \r\n", "DB 연결 성공적으로 해제");
			System.out.println();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			System.out.printf("%s \r\n", "DB 연결 해제 실패");
			System.out.println();
		}
	}

}
