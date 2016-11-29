package com.briup.woss;

import java.awt.HeadlessException;
import java.awt.List;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.LinkedList;

public class NetClient {
	public static void sendBIDr(LinkedList<BIDR> list) throws Exception{
		Socket socket = null;//初始化socket
		socket = new Socket("127.0.0.1", 8000);		
		CThread cThread = null;//初始化Thread		
		cThread = new CThread(socket);	
		cThread.start();
		cThread.sendMeg(list);
	}
}
class CThread extends Thread implements java.io.Serializable{
	Socket socket;
	static ObjectOutputStream out;
	static ObjectInputStream in;
	public CThread(Socket socket) throws Exception
	{
		this.socket=socket;
		out=new ObjectOutputStream(socket.getOutputStream());
		in=new ObjectInputStream(socket.getInputStream());
	}
	
	public void sendMeg(LinkedList<BIDR> list)  throws  HeadlessException, ClassNotFoundException, IOException
	{
				out.writeObject(list);
				out.flush();
	}
	
	public void run()
	{
		while (true)
		{
			
		}
	}
}

