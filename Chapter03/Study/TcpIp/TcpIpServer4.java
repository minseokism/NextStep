package socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TcpIpServer4 implements Runnable {
	ServerSocket severSocket;
	Thread[] threadArr;
	
	public static void main(String[] args) {
		TcpIpServer4 server = new TcpIpServer4(5);
		server.start();
	}
	
	public TcpIpServer4 (int num) {
		try {
			severSocket = new ServerSocket(7778);
			System.out.println(getTime()+" 서버가 준비되었습니다.");
			
			threadArr = new Thread[num];
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void start () {
		for (int i = 0 ; i < threadArr.length ; i ++) {
			threadArr[i] = new Thread(this);
			threadArr[i].start();
		}
	}
	
	public void run() {	
		while (true) {
			try { 
				System.out.println(getTime()+" 연결 요청을 기다립니다.");
				
				Socket socket = severSocket.accept();
				System.out.println(getTime()+socket.getInetAddress()+"로 부터 연결요청이 들어왔습니다.");
					
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
		String name = Thread.currentThread().getName();
		SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
		return f.format(new Date()) + name;
	}
	
}
