package Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class prompt {
    private JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    prompt window = new prompt();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


     // ����Ӧ�ó���
    public prompt() {
        initialize();
    }

    //��������
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(400, 200, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("  ע��ɹ�");
        lblNewLabel.setFont(new Font("�����п�", Font.PLAIN, 41));
        lblNewLabel.setBounds(100, 51, 215, 46);
        frame.getContentPane().add(lblNewLabel);

        JButton btnNewButton = new JButton("���ص�¼");
        btnNewButton.setBounds(50,50,200,40);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Login();
                frame.removeNotify();


            }
        });
        btnNewButton.setBounds(157, 153, 103, 33);
        frame.getContentPane().add(btnNewButton);
    }
}
