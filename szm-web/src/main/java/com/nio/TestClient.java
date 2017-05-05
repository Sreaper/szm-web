package com.nio;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author songzhimao
 * @date 2017/5/5
 */
public class TestClient {
    public static void main(String[] args) {
        try{
            Socket so = new Socket("127.0.0.1",2222);
            DataInputStream dis = new
                    DataInputStream(so.getInputStream());
            DataOutputStream dos = new
                    DataOutputStream(so.getOutputStream());
            InputStreamReader isr = new
                    InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            while(true){
                String str = br.readLine();
                //1 没有通知服务器
                dos.writeUTF(str);
                if("bye".equalsIgnoreCase(str)) break;
                String s = dis.readUTF();
                System.out.println(s);
            }
            dis.close();dos.close();
            so.close();
        }catch(Exception ee){
            ee.printStackTrace();
        }
    }
}
