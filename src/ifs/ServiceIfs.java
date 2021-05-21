package ifs;

import java.sql.ResultSet;

public interface ServiceIfs {
	public ResultSet read(int id);
	
	public void update();
	
	public void delete(int id);
}
