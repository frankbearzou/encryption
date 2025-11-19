package com.demo.digest;

import java.security.MessageDigest;
import java.util.HexFormat;

public class SHA512Demo {
    static void main() {
        String input = "Hello SHA512";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(input.getBytes());
            String s = HexFormat.of().formatHex(bytes);
            System.out.println("Hex: " + s);
            System.out.println("Hex length: " + s.length());
            System.out.println("bytes length: " + bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
