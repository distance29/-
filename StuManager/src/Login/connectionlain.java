package Login;

import java.sql.Connection;
import java.sql.DriverManager;

public class connectionlain {
    public Connection getConnect() {
        Connection conn=null;
        String url="jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false";
        String user="root";
        String password="123456";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection(url, user, password);
            return conn;
        }catch(ClassNotFoundException e){
            System.out.println("´íÎó");
            e.printStackTrace();
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
