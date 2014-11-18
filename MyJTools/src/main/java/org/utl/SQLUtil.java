package org.utl;

import java.sql.Timestamp;

public class SQLUtil {
	
	/**
	 * 获取当前的时间
	 * @return
	 */
	public static Timestamp getNow()
	{
		return new Timestamp(System.currentTimeMillis()); 
	}
	
	public static String toSQLString(String value)
	{
		if (!StringUtil.stringIsEmpty(value))
		{
			return StringUtil.replaceAll(value, "'", "''");				
		}else
		{
			return value;
		}
	}

}
