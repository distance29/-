package Frame;

import MySql.MySql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stu_list extends JFrame implements ActionListener {
    private JTextField jtage = new JTextField(5);//年龄
    private JTextField jtname = new JTextField(10);//姓名
    private JTextField jtnum = new JTextField(10);//学号
    private JComboBox jccity = new JComboBox();//下拉列表框 城市
    private JComboBox jczy = new JComboBox();//专业

    private JButton btupdate = new JButton("修改信息");
    private JButton btdel = new JButton("删除信息");
    private  JButton btins = new JButton("添加信息");
    Stu_list(String sno,String name,String classname,String cs,String age){

        jtnum.setText(sno);// 设置学号
        jtname.setText(name);// 设置姓名
        jczy.setSelectedItem(classname);// 设置班级
        jccity.setSelectedItem(cs);// 设置城市
        jtage.setText(age);// 设置年龄

        JPanel north = new JPanel();
        north.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
        north.add(new JLabel("学号："));
        north.add(jtnum);
        north.add(new JLabel("姓名："));
        north.add(jtname);
        north.add(new JLabel("专业："));
        jczy.addItem("物联网工程");
        jczy.addItem("计算机科学与技术");
        jczy.addItem("战术学");
        jczy.addItem("汉语言文学");
        jczy.addItem("英语");
        jczy.addItem("电子信息工程");
        jczy.addItem("武器与工程");

        north.add(jczy);
        north.add(new JLabel("城市："));
        intiChekBox();
        north.add(jccity);
        north.add(new JLabel("年龄："));
        north.add(jtage);
        north.add(btupdate);
        north.add(btdel);
        north.add(btins);
        north.setPreferredSize(new Dimension(900,320));

        btdel.addActionListener(this);
        btins.addActionListener(this);
        btupdate.addActionListener(this);

        // 添加
        this.add(north,BorderLayout.NORTH);

        this.setBounds(500,200,200,320);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void intiChekBox(){
        jccity.addItem("重庆黔江");
        jccity.addItem("重庆酉阳");
        jccity.addItem("新疆乌鲁木齐");
        jccity.addItem("内蒙古赤峰");
        jccity.addItem("四川成都");
        jccity.addItem("湖南长沙");
        jccity.addItem("云南丽江");

    }

    public void actionPerformed(ActionEvent actionEvent) {
            if(actionEvent.getSource() == btupdate){
                String num = jtnum.getText();
                String name = jtname.getText();
                String age = jtage.getText();
                String grade = (String) jczy.getSelectedItem();
                System.out.println(grade);
                String city = (String) jccity.getSelectedItem();
                MySql mySql = new MySql();
                //修改
                mySql.update(num,name,grade,city,Integer.parseInt(age));
                mySql.close();
        }else if(actionEvent.getSource() == btdel){
                String sno = jtnum.getText();
                MySql mySql = new MySql();
                //删除
                mySql.delete(sno);
                mySql.close();
        }else if(actionEvent.getSource() == btins){
            String sno = jtnum.getText();
            String name = jtname.getText();
            String scores = jtage.getText();
            String classname = (String) jczy.getSelectedItem();
            String course = (String) jccity.getSelectedItem();
            MySql mySql = new MySql();
            //查询
            mySql.insert(sno,name,classname,course,Integer.parseInt(scores));
            mySql.close();
        }
    }
}
