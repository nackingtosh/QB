package nack;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {
	private String driverName = null;
	private String url = null;
	private String user = null;
	private String password = null;
	
	public DBManager( String driverName, String url, String user, String password ) {
		this.driverName = driverName;
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	public Connection getConnection() throws ClassNotFoundException,
	SQLException {
		Class.forName( driverName );
		Connection con = DriverManager.getConnection( url,user, password );		
		return con;
	}
}