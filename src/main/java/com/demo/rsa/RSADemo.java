package com.demo.rsa;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import javax.crypto.Cipher;

public class RSADemo {
    public static void main(String[] args) {
        String plainText = "Hello World!";
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            Base64.Encoder encoder = Base64.getMimeEncoder(64, "\n".getBytes());
            String encodedPublicKey = encoder.encodeToString(publicKey.getEncoded());
            System.out.println("Public Key: \n" + encodedPublicKey);
            String encodedPrivateKey = encoder.encodeToString(privateKey.getEncoded());
            System.out.println("\n\nPrivate Key: \n" + encodedPrivateKey);

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encrypted = cipher.doFinal(plainText.getBytes());
            System.out.println("\nencrypted text: \n" + Base64.getEncoder().encodeToString(encrypted));

            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decrypted = cipher.doFinal(encrypted);
            System.out.println("\ndecrypted text: " + new String(decrypted));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
