package org.utl.json;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.utl.FileUtil;
import org.utl.StringUtil;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GsonUtil {
	

  /**
   * 清除Gson对象的的子集,不会改变对象的地址
   *
   * @param obj 要清理的对象
   */
	public static void emptyJsonElement(JsonElement obj)
	{
		if (obj.isJsonObject())
		{
			Set<Map.Entry<String, JsonElement>> repls = obj.getAsJsonObject().entrySet();
            repls.clear();			
			
		}else if (obj.isJsonArray())
		{		
			//尺寸为0时退出
			while (obj.getAsJsonArray().size() > 0)
			{					
				Iterator<JsonElement> it = obj.getAsJsonArray().iterator();				
				it.next();
				
				//清除一个
			    it.remove();
			}
			
			//obj = parse("[]");
		}				
	}
	
	public static JsonElement parse(String jsonStr)
	{
		return new JsonParser().parse(jsonStr);		
	}
	
	public static JsonObject parseObject(String jsonStr)
	{
		JsonElement ele = parse(jsonStr);
		if (ele.isJsonObject())
		{
			return (JsonObject)ele;
		}else
		{
			return null;
		}
	}
	
	public static JsonElement clone(JsonElement obj)
	{
		String jsonStr = json2String(obj);
		if (StringUtil.stringIsNotEmpty(jsonStr))
		{
			JsonElement ele = parse(jsonStr);
			return ele;
		}else
		{
			return null;
		}
	}
	
	public static String json2String(JsonElement obj)
	{
		if (obj.isJsonNull()) {
			return "";
		} else if (obj.isJsonObject()) {
			return obj.toString();
		} else if (obj.isJsonArray()) {
			return obj.toString();
		} else {
			return obj.getAsString();
		}		
	}
	
	public static void json2File(JsonObject obj, String fileName) throws UnsupportedEncodingException, IOException
	{
		String data = json2String(obj);
		FileUtil.forceMakeDirectoryOfFile(fileName);
		FileUtil.deleteFile(fileName);
  	    FileUtil.append(fileName, data.getBytes("utf-8"));
	}
	
	public static JsonObject jsonFromFile(String fileName) throws UnsupportedEncodingException, IOException
	{
		if (FileUtil.fileExists(fileName))
		{
			String data = new String(FileUtil.getFileData(fileName, 0, 0), "utf-8");
			if (StringUtil.stringIsEmpty(data))
			{
				return null;
			}else
			{
				return parseObject(data);
			}
		}else
		{
			return null;
		}
	}
	
	public static boolean jsonIsNull(JsonElement obj)
	{
		return (obj==null || obj.isJsonNull());
	}

}
