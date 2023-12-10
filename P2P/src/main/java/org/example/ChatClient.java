package org.example;

import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) throws IOException {
        String hostName = "localhost"; // 或者服务器的IP地址
        int port = 12345; // 与服务器端口相同

        try (Socket socket = new Socket(hostName, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

            String userInput, serverResponse;

            // 读取用户输入并发送到服务器，同时接收服务器的响应
            while (true) {
                System.out.print("请输入消息: ");
                userInput = stdIn.readLine();
                if (userInput != null) {
                    out.println(userInput);
                    serverResponse = in.readLine();
                    System.out.println("服务器: " + serverResponse);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("找不到主机: " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("无法获取I/O连接到: " + hostName);
            System.exit(1);
        }
    }
}
