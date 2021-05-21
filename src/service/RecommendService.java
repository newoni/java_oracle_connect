package service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class RecommendService extends BaseService {

	public void create(int id, int authorId, int targetArticle) {
		String query = "INSERT INTO RECOMMEND VALUES (?,?,?,?)";
		
		try {
			PreparedStatement prepared_statement = this.connection.prepareStatement(query);
			prepared_statement.setInt(1,  id);
			prepared_statement.setInt(2, authorId);
			prepared_statement.setInt(3, targetArticle);
			
			//현재 시간을 넣기 위한 date 형변환
			Calendar cal = Calendar.getInstance();
			Date d = new Date(cal.getTimeInMillis());
			
			prepared_statement.setDate(4, d);
			
			prepared_statement.execute();
			System.out.println("INSERT INTO RECOMMEND 성공");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.printf("%s", "INSERT INTO RECOMMEND 비성공");
		}

	}
	
	@Override
	public ResultSet read(int id) {
		String query = "SELECT * FROM RECOMMEND WHERE ID = " + id;
		ResultSet rs = runSQL(query);
		
		return rs;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		String query = "DELETE FROM RECOMMEND WHERE ID = " + id;
		runSQL(query);		
	}

}
