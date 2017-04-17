package nack;
import java.io.*;
import java.net.*;
import java.util.*;

public class ClientAgent {
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private Socket socket;
	
	public ClientAgent( String host, int port ) throws UnknownHostException, IOException {
		this.socket = new Socket( host, port );
		System.out.println("接続しました "
				+ this.socket.getRemoteSocketAddress() );
		this.oos = new ObjectOutputStream( this.socket.getOutputStream() );
		this.ois = new ObjectInputStream( this.socket.getInputStream() );	
	}
	
	// オブジェクトを送る
	public void send( Object data ) throws IOException {
		oos.writeObject( data );
	}
	
	// オブジェクトを受け取る
	public Object recv() throws IOException, ClassNotFoundException {
		return ois.readObject();
	}
	
	// リソースを閉じる
	public void close() throws IOException {
		ois.close();
		oos.close();
		socket.close();	
	}
}
