package com.example.punch.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密解密工具类
 *
 * @author zzs
 * @date 2019/11/13 20:20
 */
public class EncryptUtils {

    private EncryptUtils() {
    }

    /**
     * MD5加密
     */
    public static String encryptMd5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] output = md.digest(str.getBytes());
            //return Base64.encodeBase64String(output);
            return HexUtils.byteToHexStr(output);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
