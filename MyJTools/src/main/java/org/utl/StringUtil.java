package org.utl;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class StringUtil {
	
	public static Boolean stringIsEmpty(String s)
	{
		return (s == null || s.length() <= 0);
	}
	
	public static Boolean stringIsNotEmpty(String s)
	{
		return (!(s == null || s.length() <= 0));
	}
	
	public static Boolean stringIsNumeric(String s)
	{
		if (stringIsEmpty(s))
		{
			return false;
		}else
		{			
			return s.matches("^(-?\\d+)(\\.\\d+)?");
		}
	}
	
	public static String replaceAll(String s, String _old, String _new)
	{
		if (stringIsEmpty(s))
		{
			return s;
		}else
		{
			return s.replaceAll("(?i)"+Pattern.quote(_old), _new);
		}
	}
	
	
	/**
	* 日期转换成字符串
	* @param date 
	* @return str
	*/
	public static String DateToStr(Date date) {
	  
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   String str = format.format(date);
	   return str;
	} 

	/**
	* 字符串转换成日期
	* @param str
	* @return date
	*/
	public static Date StrToDate(String str) {
	  
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   Date date = null;
	   try {
	    date = format.parse(str);
	   } catch (ParseException e) {
	    e.printStackTrace();
	   }
	   return date;
	}
	
	public static String repeatString(String s, int _times)
	{
		String _ret = s;
		for(int i=1;i<_times;i++)
		{
			_ret = _ret + s;			
		}
		return _ret;	 
	}
	
	public static String fillWidth(int i, int _width)
	{
		return String.format("%1$0"+_width+"d", i);
	}
	
	public static String serialNO(String s, int _incValue, int _digitNum)
	{		
		if (stringIsEmpty(s))
		{
			return String.format("%1$0"+_digitNum+"d", _incValue);
		}else
		{
			//获取最后几位数字字符
			String _reg = "([0-9]{1,"+String.valueOf(_digitNum)+"})$"; 
			Pattern pattern = Pattern.compile(_reg);
			Matcher isNum = pattern.matcher(s);			
			String str="";
			if (isNum.find()) 
			{   //符合编号规则
				str = isNum.group();
				s = s.substring(0, s.length()-str.length());
				
				int v = Integer.valueOf(str) + _incValue;				
				return s+String.format("%1$0"+str.length()+"d", v);
				
		    }else
		    {
		    	return s + String.format("%1$0"+_digitNum+"d", _incValue);
		    }		
		}
	}
	
	@Test
	public void test()
	{
		if (stringIsNumeric("-12.35"))
		{
			System.out.print("是数字");
		}
	}
}
