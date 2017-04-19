package nack;

import java.sql.*;
import java.util.concurrent.Callable;

// マルチスレッドを処理するクラス
public class MultiThreadExecutor implements Callable {
	DBConnection conn;
	Result res;
	String driverName;
	String sql;
	
	public MultiThreadExecutor( String url, String user, String password, String sql, String sqlType ) {
		this.sql = sql;
		// ドライバを設定
		if ( sqlType.toLowerCase().matches("mysql") ) {
			driverName = "com.mysql.jdbc.Driver";
		} else if ( sqlType.toLowerCase().matches("postgresql") ) {
			driverName = "org.postgresql.Driver";
		} else {
			System.out.println( "データベースを選択してください" );
			return;
		}
		// データベースに接続する
		try {
			conn = new DBConnection( driverName, url, user, password );
			conn.connect();
		} catch ( ClassNotFoundException e ) {
			e.printStackTrace();
		}
		catch ( SQLException e ) {
			e.printStackTrace();
		}
	}
	
	public Result call() throws SQLException {
			// 結果を取得
			conn.execute( sql );
			res = conn.getResult();
			return res;
	}
}
