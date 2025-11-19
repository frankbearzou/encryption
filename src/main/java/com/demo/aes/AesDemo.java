package com.demo.aes;

import java.security.Key;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AesDemo {
    private static String encrypt(String input, String keyStr) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        Key secretKey = new SecretKeySpec(keyStr.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(input.getBytes());
        return new String(Base64.getEncoder().encode(encrypted));
    }

    private static String decrypt(String input, String keyStr) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        Key secretKey = new SecretKeySpec(keyStr.getBytes(), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(input));
        return new String(decrypted);
    }

    public static void main(String[] args) {
        String input = "abcdefg";
        String keyStr = "1234567812345678";
        try {
            System.out.println("input : " + input);
            String encryptedStr = encrypt(input, keyStr);
            System.out.println("encrypted : " + encryptedStr);
            String decryptedStr = decrypt(encryptedStr, keyStr);
            System.out.println("decrypted : " + decryptedStr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
