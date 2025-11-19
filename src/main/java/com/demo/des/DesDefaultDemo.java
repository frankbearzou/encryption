package com.demo.des;

import java.security.Key;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class DesDefaultDemo {
    public static String encrypt(String input, String keyStr) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        Key desKey = new SecretKeySpec(keyStr.getBytes(), "DES");
        cipher.init(Cipher.ENCRYPT_MODE, desKey);
        byte[] bytes = cipher.doFinal(input.getBytes());
        byte[] encode = Base64.getEncoder().encode(bytes);
        return new String(encode);
    }

    public static String decrypt(String input, String keyStr) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        Key desKey = new SecretKeySpec(keyStr.getBytes(), "DES");
        cipher.init(Cipher.DECRYPT_MODE, desKey);
        byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(input));
        return new String(bytes);
    }

    static void main() {
        String input = "abcdefg";
        String keyStr = "12345678";
        try {
            String encryptedStr = encrypt(input, keyStr);
            System.out.println("encrypted String: " + encryptedStr);
            String decryptedStr = decrypt(encryptedStr, keyStr);
            System.out.println("decrypted String: " + decryptedStr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
