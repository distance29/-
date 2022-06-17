package Frame;

import MySql.MySql;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Stu_info extends JFrame implements ActionListener {
    private JTextField cx = new JTextField(20);//�ı���
    private JButton btcx = new JButton("��ѯ");//��ѯ
    private JButton shuaxin = new JButton("ˢ��");



    private JTable jTable;//���
    private String Updatenum = null;
    public Stu_info(){
        super("ѧ����Ϣ����ϵͳ");
        this.setLayout(new BorderLayout());

        //center
        JPanel center = new JPanel();
        center.setLayout(new FlowLayout());
        this.jTable = centerList(center);//����ʼ��
        center.setPreferredSize(new Dimension(900,1000));




        //south
        JPanel south = new JPanel();
        south.setLayout(new FlowLayout());
        south.add(cx);
        south.add(btcx);
        south.add(shuaxin);
        south.setPreferredSize(new Dimension(900,50));

        //ʵ�ּ���
        this.jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index = jTable.getSelectedRow();//�ж���һ�б�ѡ����,���û��ѡ�У�����ֵΪ-1
                Updatenum = (String) jTable.getValueAt(index,0);//�����޸�ѧ��
                String sno = (String) jTable.getValueAt(index,0);// ����ѧ��
                String name = (String) jTable.getValueAt(index,1);// ��������
                String classname = (String) jTable.getValueAt(index,2);// ����רҵ
                String cs = (String) jTable.getValueAt(index,3);// ���ó���
                String age = (String) jTable.getValueAt(index,4);// ��������
                // ����ϸҳ��
                new Stu_list(sno,name,classname,cs,age);
            }
        });

        //�󶨼���
        btcx.addActionListener(this);
        shuaxin.addActionListener(this);
        this.add(center,BorderLayout.CENTER);
        this.add(south,BorderLayout.SOUTH);

        this.setBounds(500,200,800,620);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

    }
    public JTable centerList(JPanel center){
        final  String[] columnNames = {"ѧ��", "����", "רҵ","����", "����"};
        MySql mySql = new MySql();
        ArrayList<String[]> rowData = mySql.select(null);
        mySql.close();

        String[][] stringArray = rowData.toArray(new String[rowData.size()][]);
        JTable jt1 = new JTable (stringArray, columnNames);
        jt1.setPreferredScrollableViewportSize(new Dimension(800, 400));//���ñ��Ĵ�С
        jt1.setRowHeight (30);//����ÿ�еĸ߶�Ϊ20
        jt1.setRowHeight (0, 30);//���õ�1�еĸ߶�Ϊ15
        jt1.setRowMargin (5);//�����������е�Ԫ��ľ���
        jt1.setRowSelectionAllowed (true);//���ÿɷ�ѡ��.Ĭ��Ϊfalse
        jt1.setSelectionBackground (Color.white);//������ѡ���еı���ɫ
        jt1.setSelectionForeground (Color.blue);//������ѡ���е�ǰ��ɫ
        jt1.setGridColor (Color.yellow);//���������ߵ���ɫ

        //���ñ�����
        DefaultTableCellRenderer dc=new DefaultTableCellRenderer();
        dc.setHorizontalAlignment(JLabel.CENTER);
        jt1.setDefaultRenderer(Object.class, dc);

        // �������ӵ������� ��Ҫ����JScrollPaneʵ�ֹ���Ч��
        center.add(new JScrollPane(jt1));
        return jt1;
    }


    public void initTable(String query){
        // ������
        MySql mySql = new MySql();
        ArrayList<String[]> rowData = mySql.select(query);
        String[][] stringArray = rowData.toArray(new String[rowData.size()][]);
        //�ر�����
        mySql.close();
        // ������������Դ
        //����һ��DefaultTableModel�࣬��ʵ��TableModel�ӿ�
        DefaultTableModel model=new DefaultTableModel(stringArray,new String[]{"ѧ��", "����", "רҵ","����", "����"});
        this.jTable.setModel(model);
        this.jTable.repaint();
        this.jTable.updateUI();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == btcx){
            // ��ѯ��ť
            String query = cx.getText();
            initTable(query);
        }else if (actionEvent.getSource() == shuaxin){
            initTable(null);
        }
    }
}
