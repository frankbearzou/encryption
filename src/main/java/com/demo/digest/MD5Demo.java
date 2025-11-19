package com.demo.digest;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.HexFormat;

public class MD5Demo {
    static void main() {
        String input = "Hello MD5";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(input.getBytes());
            String s = HexFormat.of().formatHex(bytes);
            System.out.println("Hex: " + s);
            byte[] encode = Base64.getEncoder().encode(bytes);
            System.out.println("Base64 Encode: " + new String(encode));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
