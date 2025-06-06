package com.sns.common;

import java.security.MessageDigest;

public class SHA256 {
    private static final String SHA = "SHA-256";

    public SHA256() {
        super();
    }

    /**
     * 단방향 암호화
     * @param passWord
     * @return
     * @throws Exception
     */
    public static String encrypt(String passWord) throws Exception {
        try {
            MessageDigest messagedigest = null;
            messagedigest = MessageDigest.getInstance(SHA);
            messagedigest.update(passWord.getBytes());
            byte[] bytes = messagedigest.digest();
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < bytes.length; i++) {
                buffer.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return buffer.toString();
        } catch (Exception e) {
            throw e;
        }
    }
}