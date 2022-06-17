package Frame;

import MySql.MySql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stu_list extends JFrame implements ActionListener {
    private JTextField jtage = new JTextField(5);//����
    private JTextField jtname = new JTextField(10);//����
    private JTextField jtnum = new JTextField(10);//ѧ��
    private JComboBox jccity = new JComboBox();//�����б�� ����
    private JComboBox jczy = new JComboBox();//רҵ

    private JButton btupdate = new JButton("�޸���Ϣ");
    private JButton btdel = new JButton("ɾ����Ϣ");
    private  JButton btins = new JButton("�����Ϣ");
    Stu_list(String sno,String name,String classname,String cs,String age){

        jtnum.setText(sno);// ����ѧ��
        jtname.setText(name);// ��������
        jczy.setSelectedItem(classname);// ���ð༶
        jccity.setSelectedItem(cs);// ���ó���
        jtage.setText(age);// ��������

        JPanel north = new JPanel();
        north.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
        north.add(new JLabel("ѧ�ţ�"));
        north.add(jtnum);
        north.add(new JLabel("������"));
        north.add(jtname);
        north.add(new JLabel("רҵ��"));
        jczy.addItem("����������");
        jczy.addItem("�������ѧ�뼼��");
        jczy.addItem("ս��ѧ");
        jczy.addItem("��������ѧ");
        jczy.addItem("Ӣ��");
        jczy.addItem("������Ϣ����");
        jczy.addItem("�����빤��");

        north.add(jczy);
        north.add(new JLabel("���У�"));
        intiChekBox();
        north.add(jccity);
        north.add(new JLabel("���䣺"));
        north.add(jtage);
        north.add(btupdate);
        north.add(btdel);
        north.add(btins);
        north.setPreferredSize(new Dimension(900,320));

        btdel.addActionListener(this);
        btins.addActionListener(this);
        btupdate.addActionListener(this);

        // ���
        this.add(north,BorderLayout.NORTH);

        this.setBounds(500,200,200,320);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void intiChekBox(){
        jccity.addItem("����ǭ��");
        jccity.addItem("��������");
        jccity.addItem("�½���³ľ��");
        jccity.addItem("���ɹų��");
        jccity.addItem("�Ĵ��ɶ�");
        jccity.addItem("���ϳ�ɳ");
        jccity.addItem("��������");

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
                //�޸�
                mySql.update(num,name,grade,city,Integer.parseInt(age));
                mySql.close();
        }else if(actionEvent.getSource() == btdel){
                String sno = jtnum.getText();
                MySql mySql = new MySql();
                //ɾ��
                mySql.delete(sno);
                mySql.close();
        }else if(actionEvent.getSource() == btins){
            String sno = jtnum.getText();
            String name = jtname.getText();
            String scores = jtage.getText();
            String classname = (String) jczy.getSelectedItem();
            String course = (String) jccity.getSelectedItem();
            MySql mySql = new MySql();
            //��ѯ
            mySql.insert(sno,name,classname,course,Integer.parseInt(scores));
            mySql.close();
        }
    }
}
