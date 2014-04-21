package org.utl;

public class ClassUtil {
	
	//判断cls是否支持intf接口
	public static boolean SupportInterface(Class cls, Class intf){
		Class<?>[] c1 = cls.getInterfaces(); // 获取cls反射的类所实现的所有接口数组
		for (Class<?> i : c1) {
			System.out.println(i.getName());
			if (i == intf) {
				return true;
			}
		}
		return false;
	}
}
