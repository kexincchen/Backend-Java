import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// 仅仅用于检测数据库是否reachable
public class GetConnection {

    public static void main(String[] args){
        try{
            //调用Class.forName()方法加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("成功加载MySQL驱动！");
        }catch(ClassNotFoundException e1){
            System.out.println("找不到MySQL驱动!");
            e1.printStackTrace();
        }

        String url="jdbc:mysql://localhost:3306/";    //JDBC的URL
        //调用DriverManager对象的getConnection()方法，获得一个Connection对象
        Connection conn;
        try {
            conn = DriverManager.getConnection(url,"root","Tian3990113");//url 账号 密码
            //创建一个Statement对象
            Statement stmt = conn.createStatement(); //创建Statement对象
            System.out.print("成功连接到数据库！");
            stmt.close();
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}