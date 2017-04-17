package nack;
import java.io.*;
import java.net.*;

public class ServerAgent {
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private Socket socket;
	private ServerSocket sSocket;
	
	public ServerAgent( int port  ) throws IOException  {
		sSocket = new ServerSocket( port );
		System.out.println("サーバーが起動しました(port="
    			+ sSocket.getLocalPort() + ")");
	}
	
	public void waitClient() throws IOException {
		socket = this.sSocket.accept();
		System.out.println("接続されました "
    			+ socket.getRemoteSocketAddress() );
		this.ois = new ObjectInputStream( socket.getInputStream() );
    	this.oos = new ObjectOutputStream( socket.getOutputStream() );
	}
	
	public Object recv() throws IOException, ClassNotFoundException {
		return ois.readObject();
	}
	
	public void send( Object data ) throws IOException {
		oos.writeObject(data);
	}
	
	public void close() throws IOException {
		oos.close();
		ois.close();
		socket.close();
		sSocket.close();
	}
}
