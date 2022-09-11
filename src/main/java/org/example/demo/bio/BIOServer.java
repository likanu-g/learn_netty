package org.example.demo.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        new Thread(() ->{
            while (true) {
                try {
                    //阻塞获取新连接
                    Socket socket = serverSocket.accept();
                    new Thread(() ->{

                        try {
                            int len = 0;
                            byte[] data = new byte[1024];
                            InputStream inputStream = socket.getInputStream();
                            while((len = inputStream.read(data)) != -1) {
                                System.out.println(new String(data, 0, len));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
