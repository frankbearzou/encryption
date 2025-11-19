package com.demo.des;

import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DesCbcDemo {
    public static String encrypt(String transformation, String keyStr, String input) throws Exception {
        Cipher cipher = Cipher.getInstance(transformation);
        SecretKeySpec key = new SecretKeySpec(keyStr.getBytes(), "DES");
        byte[] ivBytes = new byte[8];
        SecureRandom random = new SecureRandom();
        random.nextBytes(ivBytes);
        IvParameterSpec iv = new IvParameterSpec(ivBytes);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] encrypted = cipher.doFinal(input.getBytes());
        byte[] output = new byte[ivBytes.length + encrypted.length];
        System.arraycopy(ivBytes, 0, output, 0, ivBytes.length);
        System.arraycopy(encrypted, 0, output, ivBytes.length, encrypted.length);
        return Base64.getEncoder().encodeToString(output);
    }

    public static String decrypt(String transformation, String keyStr, String encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance(transformation);
        SecretKeySpec key = new SecretKeySpec(keyStr.getBytes(), "DES");
        byte[] decode = Base64.getDecoder().decode(encrypted);
        byte[] ivBytes = new byte[8];
        System.arraycopy(decode, 0, ivBytes, 0, ivBytes.length);
        IvParameterSpec iv = new IvParameterSpec(ivBytes);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] cipherText = new byte[decode.length - ivBytes.length];
        System.arraycopy(decode, ivBytes.length, cipherText, 0, cipherText.length);
        byte[] decrypted = cipher.doFinal(cipherText);
        return new String(decrypted);
    }

    static void main() {
        String transformation = "DES/CBC/PKCS5Padding";
        String keyStr = "12345678";
        String input = "0123456789";
        try {
            String encrypted = encrypt(transformation, keyStr, input);
            System.out.println("encrypted string: " + encrypted);
            String decrypted = decrypt(transformation, keyStr, encrypted);
            System.out.println("decrypted string: " + decrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
