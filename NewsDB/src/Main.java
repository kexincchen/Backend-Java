import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.sql.*;

public class Main {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/news_server";
    static final String USER = "root";
    static final String PASS = "Tian3990113";

//    public static void main(String args[]) throws Exception {
//        //Registering the Driver
//        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//        //Getting the connection
//        Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
//        System.out.println("Connection established......");
//        //Initialize the script runner
//        ScriptRunner sr = new ScriptRunner(con);
//        String filePath = "/Users/clarec/Documents/GitHub/SIMENS-SDE-Intern/NewsDB/src/";
//        String filename = "test.sql";
//        //Creating a reader object
//        Reader reader = new BufferedReader(new FileReader(filePath.concat(filename)));
//        //Running the script
//        sr.runScript(reader);
//
//
//    }

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM News WHERE NewsID=2";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("NewsID");
                String name = rs.getString("Title");
                String url = rs.getString("Body");

                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", Title: " + name);
                System.out.print(", ViewCount: " + url);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Done!");
    }
}