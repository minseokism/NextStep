package socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TcpIpServer2 {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ServerSocket severSocket = null;
		
		try {
			severSocket = new ServerSocket(7778);
			System.out.println(getTime()+" 서버가 준비되었습니다.");
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		while (true) {
			try { 
				System.out.println(getTime()+" 연결 요청을 기다립니다.");
				Socket socket = severSocket.accept();
				System.out.println(getTime()+socket.getInetAddress()+"로 부터 연결요청이 들어왔습니다.");
				
				System.out.println("getPort():"+socket.getPort());
				System.out.println("getLocalPort():"+socket.getLocalPort());
					
				OutputStream out = socket.getOutputStream();
				DataOutputStream dos = new DataOutputStream(out);
				
				dos.writeUTF("[Notice] Test message1 from Server.");
				System.out.println(getTime()+" 데이터를 전송했습니다.");
				
				dos.close();
				socket.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	static String getTime() {
		SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
		return f.format(new Date());
	}
	
}
