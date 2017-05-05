package com.nio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author songzhimao
 * @date 2017/4/26
 */
public class NioMain {
    public static void main(String[] args) throws IOException {
        System.out.println(111);
        File file = new File("data.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        FileChannel fileChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        String str = "test java nio";
        byteBuffer.put(str.getBytes());
        byteBuffer.flip();
    }
}
