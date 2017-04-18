package nack;

import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;

public class Server {
	
	
	
	public static void main( String[] args ){
	    try {
	    	// クライアントの要求に応えるサーバエージェントを用意
	    	ServerAgent serverAgent = new ServerAgent( 5555 );
	    	// クライアントからの接続を待つ
	    	serverAgent.waitClient();
	    	// 接続したら、データをもらう
	    	String data = (String)serverAgent.recv();
	    	DBConnection dbConn = new DBConnection( "com.mysql.jdbc.Driver", "jdbc:mysql://localhost/test", "root", "gorira$2845" );
	    	dbConn.connect();
	    	dbConn.execute( data );
	    	Result res = dbConn.getResult();
	    	serverAgent.send( res );
	    	// リソースを閉じる
	    	serverAgent.close();
	    } catch (IOException e) {
	    		e.printStackTrace();
	    } catch ( ClassNotFoundException e ) {
	    	e.printStackTrace();
	    } catch ( SQLException e ) {
	    	e.printStackTrace();
	    }
	}
}
