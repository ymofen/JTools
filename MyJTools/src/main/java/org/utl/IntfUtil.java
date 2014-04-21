package org.utl;

public class IntfUtil {
    /**************************************************************************
     * 判断对象o实现的所有接口中是否有szInterface 
     * 2008-08-07 修正多继承中判断接口的功能，
     * 以及修正接口继承后的判断功能
     * package test;
     * 
     * public interface ITest extends Serializable
     * public class Test1 implements ITest
     * public class Test2 extends Test1
      * public class Test3 extends Test2 
      * 
      * isInterface(Test3.class, "java.io.Serializable") = true
      * isInterface(Test3.class, "test.ITest") = true
      * @param c
      * @param szInterface
     * @return 
     * @return 
      * @return
      */      
     public static boolean isInterface(Class c, Class intf)
     {
             Class[] face = c.getInterfaces();
             for (int i = 0, j = face.length; i < j; i++) 
             {
                 if(face[i].getName().equals(intf.getName()))
                 {
                         return true;
                 }
                 else
                 { 
                     Class[] face1 = face[i].getInterfaces();
                     for(int x = 0; x < face1.length; x++)
                     {
                         if(face1[x].getName().equals(intf.getName()))
                         {
                            return true;
                         }else if(isInterface(face1[x], intf))
                         {
                            return true;
                         }
                     }
                 }
             }
             if (null != c.getSuperclass())
             {
                 return isInterface(c.getSuperclass(), intf);
             }
             return false;
     }
}
