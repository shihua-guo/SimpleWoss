package com.briup.woss;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database {
	public static void CreateDatabase() throws Exception{
		//	TODO Auto-generated method stub
		// 和数据库做链接，本地的，jdbc链接
		// 注册驱动
		Class.forName("com.mysql.jdbc.Driver");		
		Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=root");
		
		//Statement 对象表示基本语句，其中将单个方法应用于某一目标和一组参数，以返回结果
		Statement stmt = conn.createStatement();
		
		//create table创建数据表
		String sqlCommand;			
		sqlCommand = "create table t_detail_"+"(AAA_login_name varchar(10)," +
				"login_ip varchar(32),login_date date,logout_date date,NAS_ip varchar(32),time_duration int(10));";
		
		//executeUpdate用于增删改查
		stmt.executeUpdate(sqlCommand);		
	}
	
	public static void SaveDatabase(BIDR bidr)  throws  Exception{
		//	TODO Auto-generated method stub
		// 和数据库做链接，本地的，jdbc链接
		// 注册驱动
		Class.forName("com.mysql.jdbc.Driver");		
		Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=root");
		
		
		Statement stmt = conn.createStatement();
		
		//insert into（插入数据）
		String sqlCommand;
		sqlCommand="insert into t_detail_"+"(AAA_login_name,login_ip,login_date,logout_date,NAS_ip,time_duration)"
		+ " values('"+bidr.AAA_login_name+"',"+ "'"+bidr.login_ip+"',"+ "'"+bidr.login_date+"',"
		+ "'"+bidr.logout_date+"',"+ "null,'"+bidr.time_duration+"');";
		
		//executeUpdate用于增删改查
		stmt.executeUpdate(sqlCommand);
	}
}
