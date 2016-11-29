package com.briup.woss;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedList;

public class NetServer{
	public static void main(String[] args) throws Exception
	{
		/**
		 * Created by Tao
		 * 基于TCP协议的Socket通信，实现用户登录
		 * 服务器端
		 */
		 
		//1、创建一个服务器端Socket,即ServerSocket, 指定绑定的端口，并监听此端口
		ServerSocket server = null;
		server = new ServerSocket(8000);
		
		//调用accept()方法开始监听，等待客户端的链接
		Socket socket=server.accept();
		//创建一个新的线程
		ServerThread  sThread=new ServerThread(socket);
		//启动线程
		sThread.start();
		//得到客户端传来的信息
		sThread.getMeg();
	}
}

class ServerThread extends Thread
{
	//和本线程相关的Socket
	Socket socket;
	ObjectInputStream in;
	ObjectOutputStream out;
	
	//新建日志文件
	File fileLog = new File("logServer.txt");
	public ServerThread(Socket socket) throws IOException
	{
		in = new ObjectInputStream(socket.getInputStream());
		out = new ObjectOutputStream(socket.getOutputStream());
	}
	
	public void getMeg() throws Exception
	{
		LinkedList<BIDR>  list;
		try {//创建表
			Database.CreateDatabase();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			list = (LinkedList<BIDR>)in.readObject();
			Iterator<BIDR>  listIterator = list.iterator();
			while( listIterator.hasNext()){//遍历
				BIDR  temp = listIterator.next();
				Log.importData(temp, fileLog);
				Database.SaveDatabase(temp);
				System.out.println(temp.AAA_login_name+temp.login_ip+"存入");
			}
		
	}
	
	public void run()
	{
		while (true)
		{
		}
	}
	
}
