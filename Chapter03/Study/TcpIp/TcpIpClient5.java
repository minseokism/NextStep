package socket;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

public class TcpIpClient5 {

	public static void main(String[] args) {
		try {
			String serverIp = "127.0.0.1";
			Socket socket = new Socket(serverIp, 7778);
			
			System.out.println("서버에 연결되었습니다.");
			Sender sender = new Sender(socket);
			Receiver receiver = new Receiver(socket);
		
			sender.start();
			receiver.start();
			
		} catch (ConnectException e) {
			e.getStackTrace();
		} catch (IOException e) {
			e.getStackTrace();
		} catch (Exception e) {
			e.getStackTrace();
		}
		
	}
	
}
