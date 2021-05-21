package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerService extends BaseService{

	public void create(int id, String userId, String password, String name, String phone, String email) throws SQLException {
		String query = "INSERT INTO CUSTOMER VALUES (?,?,?,?,?,?)";
		
//		try {
		PreparedStatement prepared_statement = this.connection.prepareStatement(query);
		prepared_statement.setInt(1,  id);
		prepared_statement.setString(2, userId);
		prepared_statement.setString(3, password);
		prepared_statement.setString(4,  name);
		prepared_statement.setString(5, phone);
		prepared_statement.setString(6, email);
		
		prepared_statement.execute();
//		System.out.println("INSERT INTO CUSTOMER 성공");
		System.out.println();
			
			
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.printf("%s", "INSERT INTO CUSTOMER 비성공");
//		}

	}

	@Override
	public ResultSet read(int id) {
		String query = "SELECT * FROM CUSTOMER WHERE ID = " + id;
		ResultSet rs = runSQL(query);
		
		return rs;
	}

	@Override
	public void update() {}

	@Override
	public void delete(int id) {
		String query = "DELETE FROM CUSTOMER WHERE ID = " + id;
		runSQL(query);
	}

}
