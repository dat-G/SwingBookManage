package com.lys.bms.dataTemplate;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class md5 {
    public static String generateMD5(String input) {
        try {
            // 获取MD5摘要算法的 MessageDigest 对象
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要信息
            md.update(input.getBytes());
            // 得到密文（即：MD5加密后的字符串）
            byte[] mdBytes = md.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte b : mdBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5加密算法在当前环境中不可用", e);
        }
    }
}
