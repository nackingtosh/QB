package nack;
import java.sql.*;
import java.util.*;
import nack.DBManager;

// マルチスレッドを処理するクラス
public class MultiThreadExecutor implements Runnable {
	Connection con = null;
	Statement stmt = null;
	ResultSet result = null;
	ResultSetMetaData meta = null;
	List<Student> list1 = null;
	List<Contact> list2 = null;
	int sqlNum;
	String driverName = null;
	String sql = null;
	List<Hashtable<String, String>> list = null;
	
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
			con = new DBManager( driverName, url, user, password ).getConnection();
			stmt = con.createStatement();
		} catch ( ClassNotFoundException e ) {
			e.printStackTrace();
		}
		catch ( SQLException e ) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		try {
			list = new ArrayList<Hashtable<String, String>>();
			// 結果を取得
			result = stmt.executeQuery(sql);
			// フィールド名を取得
			meta = result.getMetaData();
			while ( result.next() ) {
				// 1件分のデータを格納するテーブル
				Hashtable<String, String> hdata = new Hashtable<String, String>();
				// データを取得し、ハッシュテーブルに格納
				for ( int i = 0; i < meta.getColumnCount(); ++i  ) {
					String field = meta.getColumnName(i + 1);
					String data = result.getString(field);
					if ( data == null ) {
						data = "";
					} else {
						// ハッシュにデータを追加
						hdata.put(field, data);
					}
				}
				// リストにデータを追加
				list.add(hdata);
			}
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
	}
	
	// 結果を表示する
	public void showResult() {
		for ( int i = 0; i < list.size(); ++i ) {
			// キーリストを取得
			Enumeration<String> keyList = list.get(i).keys();
			// キー要素が存在するまでキーと値を出力
			while ( keyList.hasMoreElements() ) {
				String key = (String)keyList.nextElement();
				System.out.println( key + " : " + list.get(i).get(key) );
			}
		}
	}
	
	public List<Hashtable<String, String>> getResult() {
		return this.list;
	}
	
	public void close() {
		try {
			con.close();
			stmt.close();
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
	}
}
