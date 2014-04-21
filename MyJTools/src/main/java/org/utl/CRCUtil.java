package org.utl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.CRC32;

public class CRCUtil {
	public static long crc(byte[] data) {
		int crc = 0xFFFFFFFF; // initial contents of LFBSR
		int poly = 0xEDB88320; // reverse polynomial
		byte[] bytes = data;
		for (byte b : bytes) {
			int temp = (crc ^ b) & 0xff;

			// read 8 bits one at a time
			for (int i = 0; i < 8; i++) {
				if ((temp & 1) == 1)
					temp = (temp >>> 1) ^ poly;
				else
					temp = (temp >>> 1);
			}
			crc = (crc >>> 8 ^ temp);
		}
		crc = crc ^ 0xffffffff;
		return crc;
	}

	@org.junit.Test
	public void crctester() throws IOException {
		System.out.println(crc("a".getBytes("utf-8")));

	}
}
// 390611388
