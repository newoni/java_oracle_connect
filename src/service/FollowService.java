package service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class FollowService extends BaseService {
	public void create(int id, int followerId, int followedId) {
		String query = "INSERT INTO FOLLOW VALUES (?,?,?,?)";
		
		try {
			PreparedStatement prepared_statement = this.connection.prepareStatement(query);
			prepared_statement.setInt(1,  id);
			prepared_statement.setInt(2, followerId);
			prepared_statement.setInt(3, followedId);
			
			//���� �ð��� �ֱ� ���� date ����ȯ
			Calendar cal = Calendar.getInstance();
			Date d = new Date(cal.getTimeInMillis());
			
			prepared_statement.setDate(4, d);
			
			prepared_statement.execute();
			System.out.println("INSERT INTO FOLLOW ����");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.printf("%s", "INSERT INTO FOLLOW �񼺰�");
		}

	}
	
	@Override
	public ResultSet read(int id) {
		String query = "SELECT * FROM FOLLOW WHERE ID = " + id;
		ResultSet rs = runSQL(query);
		
		return rs;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		String query = "DELETE FROM FOLLOW WHERE ID = " + id;
		runSQL(query);			
	}

}
