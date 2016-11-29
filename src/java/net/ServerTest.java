package java.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerTest {
	private ServerSocket serverSocket;
	private int port;
	private Socket socket;
	private BufferedReader br;
	private PrintWriter pw;
	//解析方法
	public void parser(){
		try {
			serverSocket = new ServerSocket(8888);
			while(true){
				socket = serverSocket.accept();
				br = new BufferedReader(
						new InputStreamReader(
							socket.getInputStream(), "UTF-8"));
				String line = null;
				while((line = br.readLine())!=null){
					System.out.println(line);
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		ServerTest s = new ServerTest();
		s.parser();
	}
}
