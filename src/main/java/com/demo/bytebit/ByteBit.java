package com.demo.bytebit;

public class ByteBit {
    public static void main(String[] args) {
        String a = "a";
        byte[] bytes = a.getBytes();
        for (byte b : bytes) {
            System.out.println(b);
            String binaryString = Integer.toBinaryString(b);
            System.out.println(binaryString);
        }
    }
}
