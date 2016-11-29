package com.briup.woss;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Log {
	public static void importData(BIDR bidr,File file){
		FileWriter fileWriter = null ;//fileWriter置空
		String dataStr = bidr.AAA_login_name+"\t\t"+bidr.login_ip+"\t"+bidr.login_date+"\t"+
		bidr.logout_date+"\t"+null+"\t"+bidr.time_duration;//把BIDR对象的数据存入STR。用于存入文件
		try {
			fileWriter = new FileWriter(file,true);//打开文件，追加到文件末尾
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);//向logclient写入数据；
		try {
			bufferedWriter.write(dataStr);//写入文件
			bufferedWriter.newLine();//追加一行
			bufferedWriter.flush();//flush
			bufferedWriter.close();//关闭
			fileWriter.close();//关闭
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
