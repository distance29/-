package MySql;

import java.sql.*;
import java.util.ArrayList;

public class MySql {
    // MySQL 8.0 ���°汾 - JDBC �����������ݿ� URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false";
    // ���ݿ���û��������룬��Ҫ�����Լ�������
    static final String USER = "root";
    static final String PASS = "123456";
    Connection conn = null;
    Statement stmt = null;
    public MySql() {
        try{
            // ע�� JDBC ����
            Class.forName(JDBC_DRIVER);
            // ������
            System.out.println("�����������ݿ⣬���Ե�......");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        }catch(SQLException se){
            // ���� JDBC ����
            se.printStackTrace();
        }catch(Exception e){
            // ���� Class.forName ����
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
        // ִ�в�ѯ
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
            // ��ɺ�ر�
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowData;
    }

    //����
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
                System.out.println("��ӳɹ�");
            }else{
                System.out.println("���ʧ��");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //�޸�
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
                System.out.println("�޸ĳɹ�");
            }else{
                System.out.println("�޸�ʧ��");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //ɾ��
    public void delete(String num){
        String sql = "delete from students where num=?";
        try {
            PreparedStatement st=conn.prepareStatement(sql);
            st.setString(1,num);
            int d = st.executeUpdate();
            if(d>0){
                System.out.println("ɾ���ɹ�");
            }else{
                System.out.println("ɾ��ʧ��");
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
