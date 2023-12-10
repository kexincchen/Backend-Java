package org.example;

import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) throws IOException {
        int port = 12345; // 可以更改为任意常用端口
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("服务器启动，正在监听端口：" + port);

        try (Socket clientSocket = serverSocket.accept();
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

            String inputLine, outputLine;

            // 读取客户端消息并回复
            while ((inputLine = in.readLine()) != null) {
                System.out.println("客户端: " + inputLine);
                System.out.print("请输入回复: ");
                outputLine = stdIn.readLine();
                if (outputLine != null) {
                    out.println(outputLine);
                }
            }
        } catch (IOException e) {
            System.out.println("连接出现错误: " + e.getMessage());
        }
    }
}
