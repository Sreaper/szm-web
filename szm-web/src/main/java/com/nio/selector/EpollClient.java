package com.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by szm on 2018/8/20.
 */
public class EpollClient{
		public static void main(String[] args) {
			try {
				SocketChannel socketChannel = SocketChannel.open();
				socketChannel.connect(new InetSocketAddress("127.0.0.1", 8000));

				ByteBuffer writeBuffer = ByteBuffer.allocate(32);
				ByteBuffer readBuffer = ByteBuffer.allocate(32);

				writeBuffer.put("hello".getBytes());
				writeBuffer.flip();

				while (true) {
					writeBuffer.rewind();
					socketChannel.write(writeBuffer);
					readBuffer.clear();
					socketChannel.read(readBuffer);
					System.out.println("received : " + new String(readBuffer.array()));
					try {
						Thread.sleep(2000L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
			}
		}
}
