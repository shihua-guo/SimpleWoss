package com.briup.woss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class CollectionClient {
	BIDR bidr = new BIDR();
	Map<String, String> mapCollecttion = new HashMap();
	LinkedList<BIDR> saveList = new LinkedList();

	// 读取文件
	public void readerFile(File file) {
		FileReader fileReader = null;
		Map<String, Integer> mapCountOnOff = new HashMap();
		long longTime;
		BufferedReader reader;
		// try{
		try {
			fileReader = new FileReader(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reader = new BufferedReader(fileReader);
		String strAll = null;
		try {
			strAll = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (strAll != null) {
			// 保留未分割的字符
			String[] strDiv = strAll.split(":");// 分割字符串，以便判断！！！
			// if (tempString[8]) {
			// }
			if (strDiv[strDiv.length - 1].length() < 3) {// 判断是否存在IP，

				try {
					strAll = reader.readLine();// !!!!!!!!!!!
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				continue;
			}
			/*
			 * 符合要求 收集数据
			 */
			// 设置map的键值
			// String key = strDiv[0] + strDiv[strDiv.length - 1];
			String key = strDiv[0];
			if (mapCollecttion.containsKey(key)) {// 如果匹配上，就在原来的countMap上面+-
				if (strDiv[4].equals("7")) {// 如果是上线，count就++
					int tempCount = mapCountOnOff.get(key);// 先把当前的
					tempCount++;// count++
					mapCountOnOff.put(key, tempCount);// 覆盖原来的count
				} else {// 如果是下线，count就--
					int tempCount = mapCountOnOff.get(key);
					tempCount--;// count--
					mapCountOnOff.put(key, tempCount); // 覆盖原来的count
				}
			} else {// 如果没有匹配上，就重新new一个count
				int count = 1;// 第一个一定是7，所以直接赋予1
				mapCountOnOff.put(key, count);
				mapCollecttion.put(key, strAll);// 在map中存入所有数据，只需要记录第一次的
			}

			if (mapCountOnOff.get(key) == 0) {// 如果count==0，就开始存入数据
				String[] strDivFromMap = ((String) mapCollecttion.get(key)).split(":");// 再次分割字符串
				// 计算第一次上线与最后一次下线的时间差
				longTime = (((Long.parseLong(strDiv[5])) - (Long.parseLong(strDivFromMap[5]))) + 59) / 60;
				bidr = new BIDR(strDiv[0], strDiv[strDiv.length - 1], transToDate(strDivFromMap[5]),
						transToDate(strDiv[5]), longTime);
				saveList.add(bidr);
				mapCollecttion.remove(key);// 清除配对成功的。留下的都是失败的
				File fileLog = new File("logClient.txt");
				new Log().importData(bidr, fileLog);

			}
			try {
				strAll = reader.readLine();//
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		// 遍历所有失败的记录
		Set entrySet = mapCollecttion.entrySet();// mapA的键值对对象
		Iterator iEntry = entrySet.iterator();// 赋值进入迭代器
		while (iEntry.hasNext()) {
			Map.Entry mEntry = (Map.Entry) iEntry.next();// 把object强转为entry
			String str = (String) mEntry.getValue();// 拿到key值
			// System.out.println(str);
			new BackUpClient().importData(str);
		}
		try {
			fileReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("未成功配对数：" + mapCollecttion.size());
		System.out.println("成功配对数：" + saveList.size());

		// 发送BIDR
		try {
			NetClient.sendBIDr(saveList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 时间戳转化为日期
	public String transToDate(String ms) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return sdf.format(new Date(Long.parseLong(ms + "000")));
	}

}
