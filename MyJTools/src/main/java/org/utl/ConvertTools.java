package org.utl;

public class ConvertTools {
	
	/**
	 *  字符串转Double, 转换失败返回__default值
	 * @param sDouble
	 * @param __default
	 * @return 
	 */
	public static Double string2Double(String sDouble, Double __default)
	{		
		try
		{
			return Double.parseDouble(sDouble);
		}catch(NumberFormatException e)
		{
			return __default;	
		}
	}
	
	/**
	 *  字符串转Double, 转换失败返回0
	 * @param sDouble
	 * @return
	 */
	public static Double string2Double(String sDouble)
	{		
		return string2Double(sDouble, 0.0);
	}

}
