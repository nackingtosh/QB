package nack;

import java.sql.*;

public class DBConnection {
	private String driverName;
	private String url;
	private String user;
	private String password;
	private Connection conn;
	private Result res;
	
	public DBConnection( String driverName, String url, String user, String password ) {
		this.driverName = driverName;
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	// DBに接続する
	public void connect() throws SQLException, ClassNotFoundException {
		// ドライバのロード
		Class.forName( driverName );
		// DBとコネクションを確立する
		conn = DriverManager.getConnection( url, user, password );
	}
	
	// データベースの処理を行う
	public void execute( String sql ) throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(sql);
		res = new Result( result );
	}
	
	// 結果を返す
	public Result getResult() {
		return res;
	}
	
	public void close() throws SQLException {
		conn.close();
	}
}