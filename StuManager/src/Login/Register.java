package Login;

import com.mysql.jdbc.PreparedStatement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class Register {
    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Register window = new Register();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public Register() {
        initialize();
    }


    private void initialize() {
        frame = new JFrame();
        frame.setBounds(400, 200, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("用户名：");
        lblNewLabel.setBounds(100, 56, 54, 15);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("密码：");
        lblNewLabel_1.setBounds(100, 102, 54, 15);
        frame.getContentPane().add(lblNewLabel_1);

        textField = new JTextField();
        textField.setBounds(196, 53, 100, 21);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(196, 96,100, 21);
        frame.getContentPane().add(textField_1);
        textField_1.setColumns(10);

        JButton btnNewButton = new JButton("注册");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                connectionlain c = new connectionlain();

                Connection conn = c.getConnect();

                PreparedStatement ps = null;
                try {
                    ps = (PreparedStatement) conn.prepareStatement("insert into users values(?,?)");

                    ps.setString(1, textField.getText());
                    ps.setString(2, textField_1.getText());
                    ps.execute();

                    new prompt();
                    frame.removeNotify();
                    //  System.out.println("成功");

                } catch (SQLException e1) {

                    e1.printStackTrace();
                }

            }
        });
        btnNewButton.setBounds(81, 169, 93, 23);
        frame.getContentPane().add(btnNewButton);

        JButton returnButton = new JButton("返回");
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                new Login();
                frame.removeNotify();

            }
        });
        returnButton.setBounds(223, 169, 93, 23);
        frame.getContentPane().add(returnButton);

    }
}
