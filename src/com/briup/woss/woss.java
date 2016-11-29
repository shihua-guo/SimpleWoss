package com.briup.woss;

import java.awt.List;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class woss {
	public static void main (String[]args){
		new CollectionClient().readerFile(new File("wtmpx"));
	}	
}
