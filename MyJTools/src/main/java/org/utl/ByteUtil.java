package org.utl;

public class ByteUtil {
	
	/**
	 * 把byte数组中的数据用字符的方式拼接起来,可以进行输出
	 * @param b
	 * @return 拼接好的字符串 
	 *    sample:[35 -46 -69 35 -70 -29 35]
	 */
	public static String bytes2String(byte[] b)
	{
		String ret = "";
		for (int i=0; i< b.length; i++)
		{
			ret = ret + b[i] + " ";
		}
		ret.trim();
		ret = "[" + ret.trim() + "]";		
		return ret;
	}

	public static int byteToInt(byte[] b) {
		int mask = 0xff;
		int temp = 0;
		int n = 0;
		for (int i = 0; i < 4; i++) {
			n <<= 8;
			temp = b[i] & mask;
			n |= temp;
		}
		return n;
	}
	
	

	public static byte[] intToByte(int number) {
		int temp = number;
		byte[] b = new byte[4];
		for (int i = b.length - 1; i > -1; i--) {
			b[i] = new Integer(temp & 0xff).byteValue(); // 将最高位保存在最低位
			temp = temp >> 8; // 向右移8位
		}
		return b;
	}

	// 字符到字节转换
	public static byte[] charToByte(char ch) {
		int temp = (int) ch;
		byte[] b = new byte[2];
		for (int i = b.length - 1; i > -1; i--) {
			b[i] = new Integer(temp & 0xff).byteValue(); // 将最高位保存在最低位
			temp = temp >> 8; // 向右移8位
		}
		return b;
	}

	// 字节到字符转换
	public static char byteToChar(byte[] b) {
		int s = 0;
		if (b[0] > 0)
			s += b[0];
		else
			s += 256 + b[0];
		s *= 256;
		if (b[1] > 0)
			s += b[1];
		else
			s += 256 + b[1];
		char ch = (char) s;
		return ch;
	}

	// 浮点到字节转换
	public static byte[] doubleToByte(double d) {
		byte[] b = new byte[8];
		long l = Double.doubleToLongBits(d);
		for (int i = 0; i < 8; i++) {
			b[i] = new Long(l).byteValue();
			l = l >> 8;
		}
		return b;
	}
	
    /**将二进制转换成16进制 
     * @param buf 
      * @return 
      */   
     public static String parseByte2HexStr(byte buf[]) {   
             StringBuffer sb = new StringBuffer();   
             for (int i = 0; i < buf.length; i++) {   
                     String hex = Integer.toHexString(buf[i] & 0xFF);   
                     if (hex.length() == 1) {   
                             hex = '0' + hex;   
                     }   
                     sb.append(hex.toUpperCase());   
             }   
             return sb.toString();   
     }   
       
     /**将16进制转换为二进制 
      * @param hexStr 
       * @return 
       */   
      public static byte[] parseHexStr2Byte(String hexStr) {   
              if (hexStr.length() < 1)   
                      return null;   
              byte[] result = new byte[hexStr.length()/2];   
              for (int i = 0;i< hexStr.length()/2; i++) {   
                      int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);   
                      int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);   
                      result[i] = (byte) (high * 16 + low);   
              }   
              return result;   
      }   
        

	// 字节到浮点转换
	public static double byteToDouble(byte[] b) {
		long l;
		l = b[0];
		l &= 0xff;
		l |= ((long) b[1] << 8);
		l &= 0xffff;
		l |= ((long) b[2] << 16);
		l &= 0xffffff;
		l |= ((long) b[3] << 24);
		l &= 0xffffffffl;
		l |= ((long) b[4] << 32);
		l &= 0xffffffffffl;
		l |= ((long) b[5] << 40);
		l &= 0xffffffffffffl;
		l |= ((long) b[6] << 48);
		l |= ((long) b[7] << 56);
		return Double.longBitsToDouble(l);
	}

}
