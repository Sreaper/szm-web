package com.nio.tutorial;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author songzhimao
 * @date 2017/6/11
 */
public class RandomAccessTest {
    public static void main(String[] args) throws Exception{
        RandomAccessFile file = new RandomAccessFile("D:\\test.txt", "rw");
        FileChannel channel = file.getChannel();
        MappedByteBuffer byteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 1024);
        byteBuffer.put(10, (byte) 97);
        byteBuffer.put(12, (byte) 98);
        channel.close();
    }

}