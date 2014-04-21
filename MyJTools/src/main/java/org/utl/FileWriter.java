package org.utl;

import java.io.IOException;
import java.io.RandomAccessFile;

public class FileWriter {
	
	/**
	 * 写入文件数据
	 * @param 文件名
	 * @param 写入的数据
	 * @param 开始写入的位置,大于现有文件尺寸或者负数则加入到最后
	 * @param 要写入的长度
	 * @return 返回写入的文件长度
	 * @throws IOException 
	 */
	public static int WriteFileData(String file, byte[] fileData, long position, int writeCount) throws IOException
	{
		  RandomAccessFile randomFile = null;  
		  try {    
		      // 打开一个随机访问文件流，按读写方式      
		      randomFile = new RandomAccessFile(file, "rw");     
		      // 文件长度，字节数      
		      long fileLength = randomFile.length();    
		      
		      if (position<0||position>fileLength)
		      {		      
		    	  // 将写文件指针移到文件尾。      
		    	  randomFile.seek(fileLength);
		      }else
		      {
		    	  randomFile.seek(position);
		      }
		      
		      randomFile.write(fileData, 0, writeCount);
		      
		      return writeCount;
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
	
        

}
