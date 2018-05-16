package com.szm.demo;

import junit.framework.TestCase;
import org.apache.zookeeper.ClientWatchManager;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author songzhimao
 * @date 2017/7/3
 */
public class TransDemo extends junit.framework.TestCase  {
    public static void main(String[] args) throws IOException {
        System.out.println(222);
        if (args.length != 1) {
            System.err.println("usage: java com.szm.demo.TransDemo classfile");
            return;
        }

        File directory = new File("");//设定为当前文件夹
        try {
            System.out.println(directory.getCanonicalPath());//获取标准的路径
            System.out.println(directory.getAbsolutePath());//获取绝对路径
        } catch (Exception e) {

        }

        ClassLib cl = new ClassLib(new FileInputStream(args[0]));
        System.out.printf("Minor version number: %d%n", cl.getMinorVer());
        System.out.printf("Major version number: %d%n", cl.getMajorVer());
        cl.showIS();

        try (FileOutputStream fos = new FileOutputStream("x.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(cl);
        }

        cl = null;

        try (FileInputStream fis = new FileInputStream("x.ser");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            System.out.println();
            cl = (ClassLib) ois.readObject();
            System.out.printf("Minor version number: %d%n", cl.getMinorVer());
            System.out.printf("Major version number: %d%n", cl.getMajorVer());
            cl.showIS();
        } catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe.getMessage());
        }
    }
}

class ClassLib  implements Serializable {
    private transient InputStream is;

    private int majorVer;
    private int minorVer;

    ClassLib(InputStream is) throws IOException {
        System.out.println("com.szm.demo.ClassLib(InputStream) called");
        this.is = is;
        DataInputStream dis;
        if (is instanceof DataInputStream)
            dis = (DataInputStream) is;
        else
            dis = new DataInputStream(is);
        if (dis.readInt() != 0xcafebabe)
            throw new IOException("not a .class file");
        minorVer = dis.readShort();
        majorVer = dis.readShort();
    }

    int getMajorVer() {
        return majorVer;
    }

    int getMinorVer() {
        return minorVer;
    }

    void showIS() {
        System.out.println(is);
    }
}

