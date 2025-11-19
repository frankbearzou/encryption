package com.demo.rsa;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.HexFormat;
import javax.crypto.Cipher;

public class RSADemo {
    public static void main(String[] args) {
        String plainText = "Hello World!";
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encrypted = cipher.doFinal(plainText.getBytes());
            System.out.println("encrypted text: " + Base64.getEncoder().encodeToString(encrypted));

            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decrypted = cipher.doFinal(encrypted);
            System.out.println("decrypted text: " + new String(decrypted));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
