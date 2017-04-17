package nack;

import java.sql.*;
import java.util.*;

public class DBExecutor {
	String jdbc;
	String url;
	String user;
	String password;
	String sql = null;
	Connection conn = null;
	DBManager dbMngr = null;
	ResultSet result = null;
	Result res = null;
	
	public DBExecutor ( String jdbc, String url, String user, String password, String sql ) {
		this.jdbc = jdbc;
		this.url = url;
		this.user = user;
		this.password = password;
		this.sql = sql;
		// コネクションを確立
		try {
			conn = new DBManager( jdbc, url, user, password ).getConnection();
		} catch ( SQLException e ) {
			e.printStackTrace();
		} catch ( ClassNotFoundException e ) {
			e.printStackTrace();
		}
	}
	
	public void executeSQL() throws SQLException {
		try {
			Statement stmt = conn.createStatement();
			result = stmt.executeQuery(sql);
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
	}
	
	public Result getResult() throws SQLException {
		res = new Result( result );
		return res;
	}
}
