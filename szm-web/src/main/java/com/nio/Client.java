package com.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author songzhimao
 * @date 2017/4/27
 */
public class Client {
    public static void main(String[] args) {
        try {
            //1.创建客户端Socket，指定服务器地址和端口
            Socket socket = new Socket("192.168.0.188",6379);
            //2.获取输出流，向服务器端发送信息
            OutputStream os=socket.getOutputStream();//字节输出流
            PrintWriter pw=new PrintWriter(os);//将输出流包装为打印流
//            pw.println("info");
//            pw.flush();

            //3.获取输入流，并读取服务器端的响应信息
            InputStream is=socket.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
//            String info=null;
//            while((info=br.readLine())!=null){
//                System.out.println("我是客户端，服务器说："+info);
//            }
            BufferedReader wt = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String str = wt.readLine();
                pw.println(str);
                pw.flush();
                if (str.equals("end")) {
                    break;
                }
                System.out.println(br.readLine());
            }

            Thread.sleep(1000);
            //4.关闭资源
            br.close();
            is.close();
            pw.close();
            os.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}

class ClientThread extends Thread {
    // 和本线程相关的Socket
    Socket socket = null;
    public ClientThread(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        OutputStream os = null;//字节输出流
        try {
            os = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(os);//将输出流包装为打印流
        pw.write("用户名：whf;密码：789");
        pw.write("\n");
        pw.flush();

    }
}