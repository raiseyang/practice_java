package com.raise.rxjava2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class Main {


    public static void main(String[] args) {
        System.out.println("艾拉比解密工具");

//        String path = args[0];
        String path = "D:\\temporary\\ec01.log";
//        byte[] encrypt = encrypt("onHandleIntent() 实例初始中...");
//        System.out.println("--encrypt :" + new String(encrypt));
//        String decrypt = new String(decrypt(encrypt));
//        System.out.println("--decrypt :" + decrypt);

        decryptFile(path);
    }

    public static void decryptFile(String path) {
        File file = new File(path);
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(file);
            fos = new FileOutputStream(new File(file.getParent(), file.getName() + ".dec"));
//            BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\temporary\\iport_log_de.txt"));
            int len = 0;
            byte[] b = new byte[2048];
            while ((len = fis.read(b)) != -1) {
                byte[] avali = new byte[len];
//                for (int i = 0; i < len; i++) {
                System.arraycopy(b, 0, avali, 0, len);
                byte[] decrypt = decrypt(avali);
//                    // 应该有换行问题
            System.out.println("写入文件的数据："+new String(decrypt, Charset.forName("UTF8")));
                fos.write(decrypt);
            }
            fos.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static byte[] encrypt(String text) {
        byte[] bytes = new byte[0];
        try {
            bytes = text.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < bytes.length; i++) {
//            byte c = bytes[i];
//            byte a = (byte) (c ^ 0xff);
//            newBytes[i] = a;
            bytes[i] = (byte) (bytes[i] ^ 0xff);
        }
        return bytes;
    }

    public static byte[] decrypt(byte[] data) {
//        for (int i = 0; i < 100; i++) {
        for (int i = 0; i < data.length; i++) {
//            System.out.println("解密前byte[" + i + "]=" +  Integer.toHexString(data[i]));
            byte c = data[i];
            if (c == 0x0a) {
                data[i] = c;
                continue;
            }
            byte a = (byte) (c ^ 0xff);
            data[i] = a;
//            System.out.println("吼吼吼byte[" + i + "]=" + Integer.toHexString(data[i]));
        }
//        try {
//            return new String(data, "ASCII");
        return data;
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return null;
    }


}
