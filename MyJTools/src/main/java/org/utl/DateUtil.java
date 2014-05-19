package org.utl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class DateUtil {
	
	/**
	 * 一天的毫秒数1000 * 60 * 60 * 24
	 */
	private static long dayMillisecond = 86400000;
	
	/**
	 * 一秒钟多少毫秒
	 */
	private static long secMillisecond = 1000;
	
	/**
	 * 一分钟多少毫秒
	 */
	private static long minsMillisecond = 1000 * 60;
	
	/**
	 * 一小时多少毫秒
	 */
	private static long hourMillisecond = 1000 * 60 * 60;
	
	/**
	 * -25567.333333333332 sql中时间(0)对应的JAVA中的浮点值
	 */
	//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
	//		Date zeroDate = format.parse("1900-01-01 00:00:00.000");  //SQL的0为1900-01-01		
	//		double dayTime = 1000 * 3600 * 24;
	//		double sqlValue = zeroDate.getTime()/dayTime;
	//		System.out.println(sqlValue);
	private static double sqlZeroTimeValue = -25567.333333333332; 
	
	
	public static String format(Date _date, String _style)
	{
		SimpleDateFormat dt=new SimpleDateFormat(_style);
		return dt.format(_date);
	}
	
	public static Date stringToDate(String dateStr, String _style) throws ParseException
	{
		Date ret = null; 
		DateFormat sdf = new SimpleDateFormat(_style);   
       	ret = sdf.parse(dateStr);   
        return ret;
	}
	
	public static Date stringToDateTime(String dateStr) throws ParseException
	{
        return stringToDate(dateStr, "yyyy-MM-dd HH:mm:ss");
	}
	
	public static Date stringToDate(String dateStr) throws ParseException
	{
        return stringToDate(dateStr, "yyyy-MM-dd");
	}
	
	public static String format(String _style)
	{		
		return format(new Date(),_style);
	}
	
	
	public static String dateTime2Str(Date _date)
	{
		if (_date==null)
		{
			return format(new Date(),"yyyy-MM-dd HH:mm:ss");
		}else
		{
			return format(_date,"yyyy-MM-dd HH:mm:ss");
		}
	}
	
	
    
	
	/**
	 * 
	 * @param dateStr
	 *   yyyy-mm-dd hh:mm:ss[.64] 这样的格式，中括号表示可选，否则报错！！！
	 *   2012-12-05 18:58:02.654
	 * @return
	 */
	public static Date stringToTimestamp(String dateStr)
	{
		Timestamp ret = new Timestamp(System.currentTimeMillis());   
       	ret = Timestamp.valueOf(dateStr);   
        return ret;
	}
	
    public static Timestamp timestampToDate(Date date)
    {
    	 return new Timestamp(date.getTime());
    }
	
	
	public static Date double2Date(Double d) {
		return null;
	}	
	
	/**
	 * 将一个JAVA的时间转成SQL中对应浮点值
	 * @param JAVA中的时间
	 * @return double
	 */
	public static Double date2sqlDouble(Date _date)
	{
		//0-sqlZeroTimeValue 意思是 JAVA中对应的0与sql中0对应时间的差数
		return ((double)_date.getTime()/dayMillisecond) + (0-sqlZeroTimeValue);
		
//		try {
//			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
//			Date zeroDate = format.parse("1900-01-01 00:00:00.000");  //SQL的0为1900-01-01
//
//			double sqlTime = _date.getTime() - zeroDate.getTime();
//			return new Double(sqlTime / (1000 * 3600 * 24));	
//			
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}	
	}
	
	/**
	 * sql日期转换出来的浮点数转成时间
	 * @param sql中的日期浮点值
	 * @return 返回一个日期类型的值(带时间)
	 */
	public static Date sqlDouble2Date(double sqlDateTimeDouble)
	{
		
		double sqlTime = (sqlDateTimeDouble + sqlZeroTimeValue) * dayMillisecond;
		Date dt =new Date();
		dt.setTime((long)sqlTime);
		return dt;
		
		
//以下为原形
//		try {
//			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//			Date dt = format.parse("1900-01-01");  //SQL的0为1900-01-01			
//			//Date dt =new Date();
//				
//			double sqlTime = d * (1000 * 3600 * 24) + dt.getTime();
//			//long sqlTime =(long)(d * (1000 * 3600 * 24) + dt.getTime());			
//			dt.setTime((long)sqlTime);			
//			return dt;	
//			
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}

	}
	
	@Test
	public void testDouble() throws ParseException
	{
		
		double sqlTime = 0;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		Date zeroDate = format.parse("1900-01-01 00:00:00.000");  //SQL的0为1900-01-01
		
		double dayTime = 1000 * 3600 * 24;		
		double sqlValue = zeroDate.getTime()/dayTime;
		
		sqlTime = 41134.333287037 * dayTime + sqlValue * dayTime;
		System.out.println(sqlTime);
		
		sqlTime = (41134.333287037 + sqlValue) * dayTime;
		System.out.println(sqlTime);
	}
	
	/**
	 * 两个时间的相差毫秒数
	 * @param _now
	 * @param _then
	 * @return 返回相差的毫秒数(绝对值)都为正数
	 */
	public static double spanOf(Date _now, Date _then)
	{
		if (_now==null)
		{
			_now = new Date();
		}
		double v = _then.getTime() - _now.getTime();
		if (v<0)
		{
			v = -v;
		}
		return v;
	}
	
	public static double secondSpan(Date _now, Date _then)
	{
		double ret = spanOf(_now, _then) / secMillisecond;
		if (ret<0)
		{
			ret = -ret;
		}
		return 	ret;
	}
	
	public static double minuteSpan(Date _now, Date _then)
	{
		double ret = spanOf(_now, _then) / minsMillisecond;
		if (ret<0)
		{
			ret = -ret;
		}
		return 	ret;
	}
	
	public static double hourSpan(Date _now, Date _then)
	{
		double ret = spanOf(_now, _then) / hourMillisecond;
		if (ret<0)
		{
			ret = -ret;
		}
		return 	ret;
	}
	
	public static double daySpan(Date _now, Date _then)
	{
		double ret = spanOf(_now, _then) / dayMillisecond;
		if (ret<0)
		{
			ret = -ret;
		}
		return 	ret;
	}
	
	@Test
	public void test() throws ParseException
	{
		Date date = this.stringToDateTime("2013-01-01 00:00:01");
		Date date02 = this.stringToDateTime("2013-01-02 00:00:01");
		
		System.out.println(secondSpan(date, date02));
		System.out.println(minuteSpan(date, date02));		
		System.out.println(hourSpan(date, date02));	
		System.out.println(daySpan(date, date02));	
		//System.out.println(secondSpan(date,date02));
		
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
//		
//		Date zeroDate = format.parse("1900-01-01 00:00:00.000");  //SQL的0为1900-01-01		
//		double dayTime = 1000 * 3600 * 24;
//		double sqlValue = zeroDate.getTime()/dayTime;
//		sqlValue = 0-sqlValue;
//		System.out.println(sqlValue);
	}
	
	/**
	 *   获取这个日期的开始的时间点
	 * @param date
	 * @return
	 */
	public static Date startOfTheDay(Date date)
	{		 
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(date);
		
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	/**
	 *   获取这个日期的最后一个时间点
	 * @param date
	 * @return
	 */
	public static Date endOfTheDay(Date date)
	{		 
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(date);
		
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 997);
		return cal.getTime();
	}
	
	/**
	 *   获取这个日期的所在月份的最后一个时间点
	 * @param date
	 * @return
	 */
	public static Date endOfTheMonth(Date date)
	{		 
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(date);
		
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 997);
		
		//cal.set(Calendar.DAY_OF_MONTH, 1); 
		cal.roll(Calendar.DAY_OF_MONTH, -1); 
		return cal.getTime();
	}
	
	/**
	 *  获取这个日期的所在月份的开始时间点
	 * @param date
	 * @return
	 */
	public static Date startOfTheMonth(Date date)
	{		 
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(date);
		
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}
	
	/**
	 *   在日期上增加天数
	 * @param date
	 * @return
	 */
	public static Date incDay(Date date, int iDay)
	{		 
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, iDay);
		return cal.getTime();
	}
	
	@Test
	public void testDateTools() throws Exception
	{
		Date dt = null;
		
		System.out.println("incDay");
		dt =  this.stringToDateTime("2013-12-11");
		
		dt = incDay(dt,1);
		System.out.println(format(dt, "yyyy-MM-dd HH:mm:ss.S"));	
		dt = incDay(dt, -1);
		System.out.println(format(dt, "yyyy-MM-dd HH:mm:ss.S"));
	}
	
	@Test
	public void testDouble2Date() throws Exception
	{
		Date dt = new Date();		
		
		System.out.print("现在时间:");
		System.out.println(format(dt, "yyyy-MM-dd HH:mm:ss.S"));
		
		Double sqlDouble = date2sqlDouble(dt);
		System.out.println(sqlDouble);
		
		dt= sqlDouble2Date(sqlDouble);	
		System.out.print("转换后时间:");
		System.out.println(format(dt, "yyyy-MM-dd HH:mm:ss.S"));
	}
	
}
