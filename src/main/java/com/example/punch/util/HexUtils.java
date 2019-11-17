package com.example.punch.util;

/**
 * 进制转换工具类
 *
 * @author zzs
 * @date 2019/11/4 10:06
 */
public class HexUtils {

    private HexUtils() {
    }

    /**
     * 16进制转2进制
     *
     * @param str
     * @return
     */
    public static byte[] hexStrToByte(String str) {
        int length = str.length() / 2;
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            bytes[i] = (byte)((Character.digit(str.charAt(i * 2), 16) << 4) |
                    Character.digit(str.charAt((i * 2) + 1), 16));
        }
        return bytes;
    }

    /**
     * 2进制转16进制
     *
     * @param bytes
     * @return
     */
    public static String byteToHexStr(byte[] bytes) {
        StringBuilder buf = new StringBuilder(bytes.length << 1);
        for (byte b : bytes) {
            buf.append(String.format("%02x", new Integer(b & 0xff)));
        }

        return buf.toString();
    }
}
