package nack;
import java.util.*;
import java.io.*;
import java.sql.*;
import parser.*;
import java.net.*;

public class Client {
	String driverName = null;
	String url = null;
	String user = null;
	String password = null;
	
	public static void main( String[] args ) {
		String host = "localhost";
		int port = 5555;
		try {
            // ソケットを作成
            ClientAgent clientAgent = new ClientAgent( host, port );
            // データを送る
            clientAgent.send( "select * from student;" );
            // シリアライズされたオブジェクトを受け取る
            Result res = (Result)clientAgent.recv();
            // 結果を出力
            res.show();
            // 閉じる
            clientAgent.close();
        } catch (IOException e) {
        	e.printStackTrace();
        } catch ( ClassNotFoundException e ) {
        	e.printStackTrace();
        }
	}
}
