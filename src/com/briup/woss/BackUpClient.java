package com.briup.woss;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BackUpClient {
	FileWriter fileWriter ;//
	public void importData(String str){
		File file = new File("BackUp.txt");
		try {
			fileWriter = new FileWriter(file,true);//打开文件，追加到文件末尾
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);//向logclient写入数据；
		try {
			bufferedWriter.write(str);//写入文件
			bufferedWriter.newLine();//追加一行
			bufferedWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
