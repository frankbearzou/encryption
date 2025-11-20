package com.demo.sign;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

public class RSASignatureDemo {

    public static String sign(String message, PrivateKey privateKey) throws Exception {
        Signature rsa = Signature.getInstance("SHA256withRSA");
        rsa.initSign(privateKey);
        rsa.update(message.getBytes());
        return Base64.getEncoder().encodeToString(rsa.sign());
    }

    public static boolean verify(String message, String signature, PublicKey publicKey) throws Exception {
        Signature rsa = Signature.getInstance("SHA256withRSA");
        rsa.initVerify(publicKey);
        rsa.update(message.getBytes());
        return rsa.verify(Base64.getDecoder().decode(signature));
    }

    static void main() {
        String plainText = "Hello World";
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            String signature = sign(plainText, keyPair.getPrivate());
            boolean verified = verify(plainText, signature, keyPair.getPublic());
            System.out.println(verified);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
