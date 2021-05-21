package service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class CommunityService {
	public Connection connection;
	public static final String URL ="jdbc:oracle:thin:@localhost:1521:xe";
	public static final String USERID ="COMMUNITYDATA";
	public static final String PASSWORD ="COMMUNITYDATA";
	public Statement statement;
	
	public CommunityService() {
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
	
	public void readAllArticle() {
		String query = "select article.id, customer.userid, article.title, article.boardtime from customer, article where article.author=customer.id";
				
		ResultSet result_set = null;

		try {
			
			this.statement = this.connection.createStatement();
			
//			System.out.println("DB �������� ������ �߾��.");
			System.out.println();
			result_set = this.statement.executeQuery(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			System.out.println("DB �������� ������ �� ���¾��.");
			System.out.println();
		}
//		��´�
		try {
			System.out.printf("%-5s", "��ȣ");
			System.out.printf("%-10s", "�ۼ���");
			System.out.printf("%-30s", "����");
			System.out.printf("%10s", "�ۼ��ð�");
			System.out.println();
			while(result_set.next()) {
				System.out.printf("%-5d",result_set.getInt(1));
				System.out.printf("%-10s",result_set.getString(2));
				System.out.printf("%-30s",result_set.getString(3));
				System.out.printf("%10s",result_set.getDate(4));
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void readOneArticle(int articleId) {
		String query = "select customer.userid, article.title, article.contents, article.boardtime from customer, article where article.author= customer.Id and article.Id = " +  articleId;
		ResultSet result_set = null;

		try {
			
			this.statement = this.connection.createStatement();
			
//			System.out.println("DB �������� ������ �߾��.");
			System.out.println();
			result_set = this.statement.executeQuery(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("DB �������� ������ �� ���¾��.");
			System.out.println();
		}
//		��´�
		try {
			while(result_set.next()) {
				System.out.printf("%-16s","�ۼ���: ");
				System.out.print(result_set.getString(1) + "\n");
				System.out.println();
				
				
				System.out.printf("%-16s","����: ");
				System.out.print(result_set.getString(2) + "\n");
				System.out.println();
				System.out.printf("%20s","����");
				System.out.println();
				System.out.print(result_set.getString(3) + "\n");
				System.out.println();
				System.out.print(result_set.getDate(4)+ "\n");
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void writeArticle(int articleId, String userInfo, String title, String contents) throws SQLException {
		
		String query = "INSERT INTO ARTICLE VALUES (?, ?, ?,?,?)";
		
		PreparedStatement prepared_statement = this.connection.prepareStatement(query);
			
		prepared_statement.setInt(1,  articleId);
		prepared_statement.setInt(2,  Integer.parseInt(userInfo));
		prepared_statement.setString(3, title);
		prepared_statement.setString(4,  contents);
		prepared_statement.setDate(5, Date.valueOf(LocalDate.now()));
		prepared_statement.execute();
				
	}

	public void findArticleById(String userInfo) {
		String query = "select article.id, article.title, article.boardtime from customer, article where article.author=customer.id and article.author = " + userInfo;
		
		ResultSet result_set = null;

		try {
			
			this.statement = this.connection.createStatement();
			
//			System.out.println("DB �������� ������ �߾��.");
			System.out.println();
			result_set = this.statement.executeQuery(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			System.out.println("DB �������� ������ �� ���¾��.");
			System.out.println();
		}
//		��´�
		try {
			System.out.printf("%-5s", "��ȣ");
			System.out.printf("%-30s", "����");
			System.out.printf("%10s", "�ۼ��ð�");
			System.out.println();
			while(result_set.next()) {
				System.out.printf("%-5d",result_set.getInt(1));
				System.out.printf("%-30s",result_set.getString(2));
				System.out.printf("%10s",result_set.getDate(3));
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteArticleById(String userInfo, String articleId4Delete) {
		String query = "delete from article where author= " + userInfo +" and id = " + articleId4Delete ;
		
		ResultSet result_set = null;

		try {
			this.statement = this.connection.createStatement();
			
//			System.out.println("DB �������� ������ �߾��.");
			System.out.println();
			result_set = this.statement.executeQuery(query);
			
		} catch (SQLException e) {
			
		}
		
	}
}
