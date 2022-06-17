package MySql;

import java.sql.*;
import java.util.ArrayList;

public class MySql {
    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false";
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "123456";
    Connection conn = null;
    Statement stmt = null;
    public MySql() {
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
            // 打开链接
            System.out.println("正在连接数据库，请稍等......");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
    }

    public ArrayList<String[]> select(String query){
        String sql;
        if(query == null){
            sql = "SELECT * FROM students";
        }else {
            sql = "SELECT * FROM students WHERE CONCAT(num,zy,name) LIKE '%"+query+"%'";
            System.out.println(sql);
        }
        ArrayList<String[]> rowData = new ArrayList<>();
        // 执行查询
        try {
            stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String num = rs.getString("num");
                String name = rs.getString("name");
                String zy = rs.getString("zy");
                String city = rs.getString("city");
                int age = rs.getInt("age");
                rowData.add(new String[]{num,name,zy,city,Integer.toString(age)});
            }
            // 完成后关闭
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowData;
    }

    //插入
    public void insert(String num,String name,String zy, String city ,int age){
        String sql = "insert into students values(?,?,?,?,?)";
        try {
            PreparedStatement st=conn.prepareStatement(sql);
            st.setString(1,num);
            st.setString(2,name);
            st.setString(3,zy);
            st.setString(4,city);
            st.setInt(5,age);
            int d = st.executeUpdate();
            if(d>0){
                System.out.println("添加成功");
            }else{
                System.out.println("添加失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //修改
    public void update(String num,String name,String zy, String city ,int age){
        String sql = "update students set name=?,zy=?,city=?,age=? where num=?";
        try {
            PreparedStatement st=conn.prepareStatement(sql);
            st.setString(1,name);
            st.setString(2,zy);
            st.setString(3,city);
            st.setInt(4,age);
            st.setString(5,num);
            int d = st.executeUpdate();
            if(d>0){
                System.out.println("修改成功");
            }else{
                System.out.println("修改失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //删除
    public void delete(String num){
        String sql = "delete from students where num=?";
        try {
            PreparedStatement st=conn.prepareStatement(sql);
            st.setString(1,num);
            int d = st.executeUpdate();
            if(d>0){
                System.out.println("删除成功");
            }else{
                System.out.println("删除失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void close(){
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
