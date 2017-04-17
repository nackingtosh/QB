package nack;
import java.util.*;
import java.io.Serializable;
import java.sql.*;

// 結果を格納するクラス
public class Result implements Serializable {
	// カラム名、データのセットを格納するハッシュテーブルのリスト
	private List<Hashtable<String, String>> hList = null;
	
	public Result( ResultSet result ) throws SQLException {
		hList = new ArrayList<Hashtable<String, String>>();
		// フィールド名を取得
		ResultSetMetaData meta = result.getMetaData();
		while ( result.next() ) {
		// 1件分のデータを格納するテーブル
		Hashtable<String, String> hdata = new Hashtable<String, String>();
		// データを取得し、ハッシュテーブルに格納
		for ( int i = 0; i < meta.getColumnCount(); ++i  ) {
			String field= meta.getColumnName(i + 1);
			String data = result.getString(field);
			if ( data == null ) {
				data = "";
				} else {
					// ハッシュにデータを追加
					hdata.put(field, data);
					}
			}
		// リストにデータを追加
		hList.add(hdata);
		}
	}
	
	public void show() {
		
		for ( int i = 0; i < hList.size(); ++i ) {
			// キーリストを取得
			Enumeration<String> keyList = hList.get(i).keys();
			// キー要素が存在するまでキーと値を出力
			while ( keyList.hasMoreElements() ) {
				String key = (String)keyList.nextElement();
				System.out.println( key + " : " +  hList.get(i).get(key) );
			}
		}
	}
}
