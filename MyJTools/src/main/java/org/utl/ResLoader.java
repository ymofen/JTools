package org.utl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class ResLoader {
	
	public static String getFileStringData(String fileName) throws Exception
	{		
		BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"utf-8"));
		StringBuffer sb = new StringBuffer();		
		String line = reader.readLine(); // 读取第一行 
		while (line != null) { // 如果 line 为空说明读完了 
			sb.append(line); // 将读到的内容添加到 buffer 中 
			sb.append("\n"); // 添加换行符 
		    line = reader.readLine(); // 读取下一行 
		};
		return sb.toString();
	}
	
	public static String getClassResFile(Class cls, String resFile) throws Exception
	{	
		File f = new File("");
		System.out.println(f.getAbsolutePath());
		 
		System.out.println(cls);
		
		System.out.println(ResLoader.class);
		System.out.println(ResLoader.class.getClassLoader());
		System.out.println(ResLoader.class.getClassLoader().getResource("."));
		System.out.println(cls.getClassLoader().getResource("."));
		String fileName = cls.getResource(".").getPath() + resFile;
		return getFileStringData(fileName);
	}

	
	
	public static String getString(String resFile) throws Exception
	{
		String fileName = FileUtil.getRootPath() + resFile;
		return getFileStringData(fileName);
	}

}
