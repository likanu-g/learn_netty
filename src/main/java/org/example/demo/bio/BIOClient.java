package org.example.demo.bio;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BIOClient {
    public static void main(String[] args) {
        new Thread(() ->{
            try {
                Socket socket = new Socket("127.0.0.1", 8000);
                while (true) {
                    socket.getOutputStream().write((new Date() + " hello world").getBytes(StandardCharsets.UTF_8));
                    TimeUnit.SECONDS.sleep(2);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
