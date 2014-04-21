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

}
