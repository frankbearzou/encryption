package com.demo.rsa;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;

public class KeyPairDemo {
    public static String getPublicKeyStr(PublicKey publicKey) {
        Base64.Encoder encoder = Base64.getMimeEncoder(64, "\n".getBytes());
        return encoder.encodeToString(publicKey.getEncoded());
    }

    public static String getPrivateKeyStr(PrivateKey privateKey) {
        Base64.Encoder encoder = Base64.getMimeEncoder(64, "\n".getBytes());
        return encoder.encodeToString(privateKey.getEncoded());
    }

    static void main() {
        String plainText = "Hello World!";
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            String publicKeyStr = getPublicKeyStr(keyPair.getPublic());
            String privateKeyStr = getPrivateKeyStr(keyPair.getPrivate());

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.getMimeDecoder().decode(publicKeyStr));
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.getMimeDecoder().decode(privateKeyStr));
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] cipherText = cipher.doFinal(plainText.getBytes());
            String cipherTextStr = Base64.getEncoder().encodeToString(cipherText);
            System.out.println(cipherTextStr);

            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedText = cipher.doFinal(cipherText);
            String decryptedTextStr = new String(decryptedText);
            System.out.println(decryptedTextStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
