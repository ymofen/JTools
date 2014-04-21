package org.utl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.net.URL;

public class FileUtil {
	public static boolean forceMakeDirectory(String path) {
		File myFilePath = new File(path);
		if (myFilePath.exists()) {
			return true;
		} else {
			return myFilePath.mkdirs();
		}
	}	
	

	public static boolean forceMakeDirectoryOfFile(String file) {
		String path = extractFilePath(file);
		return forceMakeDirectory(path);
	}
	
	public static String PathWithBackslash(String path)
	{
		char lastChar = path.charAt(path.length()-1);  
		if ((lastChar == '\\')||(lastChar =='/'))
		{
		    return path;		
		}else
		{
			return path + "\\";						
		}
	}
	
	public static String PathWithoutBackslash(String path)
	{
		String ret = path.trim();
		int i = 0;
		for (i=ret.length()-1;i>=0;i--)
		{
			char ch = ret.charAt(i);
			if (!((ch=='\\')||(ch =='/')))
			{
				break;
			}
		}
		if (i!=ret.length())
		{
			ret = ret.substring(0, i+1);
		}
		return ret;
	}
	
	public static boolean deleteEmptyDirectory(String path) {
		File pathFile = new File(path);
		if (pathFile.exists()) {
			return pathFile.delete();
		}
		return false;
	}

	public static boolean fileInUse(String file) {
		File f = new File(file);

		return f.renameTo(f);
	}
	
	public static String getRootPath()
	{
		URL url = FileUtil.class.getResource("/");
		if (url != null)
		{
			return url.getPath();
		}else
		{
			return (new File("")).getAbsolutePath() + "\\";
		}
	}
	
	public static long fileSize(String file) {
		File f = new File(file);
		return f.length();
	}

	/***
	 * 修改文件
	 * 
	 * @param 源文件
	 *            (带路径)
	 * @param 新文件名不带路径
	 * @return 修改成功返回true
	 */
	public static boolean rename(String file, String pvNewFileName) {
		String newFileName = extractFilePath(file) + pvNewFileName;
		File target = new File(newFileName);
		if (target.exists()) {
			target.delete();
		}
		File oldfile = new File(file);
		if (oldfile.exists()) {
			return oldfile.renameTo(target);
		} else {
			return false;
		}
	}
	
	/**
	 * 移动文件
	 * @param file 源文件
	 * @param pvNewFileName 新的文件
	 * @return 成功返回true
	 */
	public static boolean moveto(String file, String pvNewFileName) {
		String newFileName = pvNewFileName;
		File target = new File(newFileName);
		if (target.exists()) {
			target.delete();
		}
		File oldfile = new File(file);
		if (oldfile.exists()) {
			return oldfile.renameTo(target);
		} else {
			return false;
		}
	}
	
	/**
	 * 获取文件数据
	 * @param file
	 * @param __start
	 * @param __size  0 时获取剩下部分
	 * @return
	 * @throws IOException
	 */
	public static byte[] getFileData(String file, int __start, int __size) throws IOException {
		  RandomAccessFile randomFile = null;  
		  try {    
		      // 打开一个随机访问文件流，按读方式      
		      randomFile = new RandomAccessFile(file, "r");		      
		      
		      if (__size == 0)
		      {
		    	  __size = (int) (randomFile.length()-__start);
		      }
		      
		      byte[] tempData = new byte[__size];
		      randomFile.seek(__start);
		      
		      int l = randomFile.read(tempData, 0, __size);
		      if (l == __size)
		      {
		    	  return tempData;
		      }else if (l<=0)
		      {
		    	  return new byte[0];
		      }else
		      {
			      byte[] ret = new byte[l];
			      System.arraycopy(tempData, 0, ret, 0, l);
			      return ret;
		      }
		  } catch (IOException e) {
		      e.printStackTrace();  
			  throw e;
		  } finally{ 
		      if(randomFile != null){  
		          try {  
		              randomFile.close();  
		              randomFile = null;
		          } catch (IOException e) {  
		              e.printStackTrace();  
		          }  
		      }  
		  } 
	}
	public static void append(String file, byte[] fileData) throws IOException {

		  RandomAccessFile randomFile = null;  
		  try {    
		      // 打开一个随机访问文件流，按读写方式      
		      randomFile = new RandomAccessFile(file, "rw");     
		      // 文件长度，字节数      
		      long fileLength = randomFile.length();     
		      // 将写文件指针移到文件尾。      
		      randomFile.seek(fileLength);     
		      randomFile.write(fileData);		      
		  } catch (IOException e) {
		      e.printStackTrace();  
			  throw e;
		  } finally{ 
		      if(randomFile != null){  
		          try {  
		              randomFile.close();  
		              randomFile = null;
		          } catch (IOException e) {  
		              e.printStackTrace();  
		          }  
		      }  
		  } 
	}

	/**
	 * 根据路径删除指定的目录或文件，无论存在与否
	 * 
	 * @param sFileOrPath
	 *            要删除的目录或文件
	 * @return 删除成功返回 true，否则返回 false。
	 */
	public static boolean delete(String sFileOrPath) {
		boolean flag = false;
		File file = new File(sFileOrPath);
		// 判断目录或文件是否存在
		if (!file.exists()) { // 不存在返回 false
			return flag;
		} else {
			// 判断是否为文件
			if (file.isFile()) { // 为文件时调用删除文件方法
				return deleteFile(sFileOrPath);
			} else { // 为目录时调用删除目录方法
				return deleteDirectory(sFileOrPath);
			}
		}
	}
	
	public static boolean fileExists(String sFile)
	{
		File file = new File(sFile);
		return file.exists();
	}

	/**
	 * 删除单个文件
	 * 
	 * @param sFile
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String sFile) {
		boolean flag = false;
		File file = new File(sFile);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * 
	 * @param sPath
	 *            被删除目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String sPath) {
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} // 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag)
			return false;
		// 删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}

	public static String extractFilePath(String fileName) {
		if (StringUtil.stringIsNotEmpty(fileName)) {
			int i = fileName.lastIndexOf("\\");
			if (i == -1) {
				i = fileName.lastIndexOf("/");
			}
			if (i >= 0) {
				return fileName.substring(0, i + 1);
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	public static String extractFileName(String fileName) {
		if (StringUtil.stringIsNotEmpty(fileName)) {
			int i = fileName.lastIndexOf("\\");
			if (i == -1) {
				i = fileName.lastIndexOf("/");
			}
			if (i >= 0) {
				return fileName.substring(i + 1);
			} else {
				return fileName;
			}
		} else {
			return fileName;
		}
	}

	public static String extractFileNameWithoutExt(String fileName) {
		String _name = extractFileName(fileName);
		if (StringUtil.stringIsNotEmpty(_name)) {
			int i = _name.lastIndexOf(".");
			if (i >= 0) {
				return _name.substring(0, i);
			} else {
				return _name;
			}
		} else {
			return _name;
		}
	}

	public static String extractFileExt(String fileName, Boolean withdelimiter) {
		if (StringUtil.stringIsNotEmpty(fileName)) {
			int i = fileName.lastIndexOf(".");
			if (i >= 0) {
				if (withdelimiter) {
					return fileName.substring(i);
				} else {
					return fileName.substring(i + 1);
				}
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	@org.junit.Test
	public void Test() {
		forceMakeDirectoryOfFile("C:\\cache\\1\\2\\3\\1.sql");

		if (!this.delete("C:\\cache\\1\\2\\3\\")) {
			System.out.println("删除失败");
		} else {
			System.out.println("删除成功!");
		}

		String fileName = "E:\\Workspace\\eclipse\\EFService\\src\\com\\mofen\\utils\\login.do";
		System.out.println("path:(" + extractFilePath(fileName) + ")");
		System.out.println(extractFileName(fileName));
		System.out.println(extractFileExt(fileName, false));
		System.out.println(extractFileNameWithoutExt(fileName));
	}
	
	@org.junit.Test
	public void fileDataRWTester() throws IOException {
		
		byte[] tempData = FileUtil.getFileData("C:\\1.bmp", 0, 0);
		FileUtil.deleteFile("C:\\x.bmp");
		FileUtil.append("C:\\x.bmp", tempData);
	}
	
	
	@org.junit.Test
	public void appendTester() throws IOException {
		//原文件
		String fileName = "C:\\图标.rar";
		
		//新文件
		String newFileName = "C:\\图标01.rar";
		FileUtil.deleteFile(newFileName);
		
		int pos=0;
		byte[] tempData = null;
		while (true)
		{
			tempData = FileUtil.getFileData(fileName, pos, 1000);
			if ((tempData==null)||(tempData.length==0))
			{
				break;
			}
			FileUtil.append(newFileName, tempData);
			pos = pos + 1000;			
		}
		


	}
}
