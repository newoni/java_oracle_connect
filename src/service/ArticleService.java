package service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class ArticleService extends BaseService{

	public void create(int id, int authorId, String title, String contents) {
		String query = "INSERT INTO ARTICLE VALUES (?, ?, ?,?,?)";
		
		try {
			PreparedStatement prepared_statement = this.connection.prepareStatement(query);
			prepared_statement.setInt(1,  id);
			prepared_statement.setInt(2, authorId);
			prepared_statement.setString(3, title);
			prepared_statement.setString(4, contents);
			
			//현재 시간을 넣기 위한 date 형변환
			Calendar cal = Calendar.getInstance();
			Date d = new Date(cal.getTimeInMillis());
			
			prepared_statement.setDate(5, d);
			
			prepared_statement.execute();
//			System.out.println("INSERT INTO ARTICLE 성공");
			System.out.println();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
//			System.out.printf("%s", "INSERT INTO ARTICLE 비성공");
			System.out.println();
		}

	}

	@Override
	public ResultSet read(int id) {
		String query = "SELECT * FROM ARTICLE WHERE ID = " + id;
		ResultSet rs = runSQL(query);
		
		return rs;
	}

	@Override
	public void update() {}

	@Override
	public void delete(int id) {
		String query = "DELETE FROM ARTICLE WHERE ID = " + id;
		runSQL(query);
	}
	
}
