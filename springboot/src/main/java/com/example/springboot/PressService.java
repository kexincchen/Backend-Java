package com.example.springboot;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

@Service
public class PressService {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/news_server";
    static final String USER = "root";
    static final String PASS = "Tian3990113";


    public Press getPressById(int id) throws SQLException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());

        System.out.println("Connection established......");

        Press press = null;
        try (
                //Getting the connection
                Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM News WHERE NewsID = ?");
        ) {
            System.out.println(" 实例化Statement对象...");
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if(rs.next()) {
                    String name = rs.getString("Title");
                    String content = rs.getString("Body");

                    press = new Press(id, name, content);

                    System.out.print("ID: " + id);
                    System.out.print(", Title: " + name);
                    System.out.print(", Body: " + content);
                    System.out.print("\n");
                }
            }
        }

        return press;
    }


}
