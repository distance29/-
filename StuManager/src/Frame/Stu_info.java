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
    private JTextField cx = new JTextField(20);//文本框
    private JButton btcx = new JButton("查询");//查询
    private JButton shuaxin = new JButton("刷新");



    private JTable jTable;//表格
    private String Updatenum = null;
    public Stu_info(){
        super("学生信息管理系统");
        this.setLayout(new BorderLayout());

        //center
        JPanel center = new JPanel();
        center.setLayout(new FlowLayout());
        this.jTable = centerList(center);//表格初始化
        center.setPreferredSize(new Dimension(900,1000));




        //south
        JPanel south = new JPanel();
        south.setLayout(new FlowLayout());
        south.add(cx);
        south.add(btcx);
        south.add(shuaxin);
        south.setPreferredSize(new Dimension(900,50));

        //实现监听
        this.jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index = jTable.getSelectedRow();//判断哪一行被选中了,如果没有选中，返回值为-1
                Updatenum = (String) jTable.getValueAt(index,0);//保存修改学号
                String sno = (String) jTable.getValueAt(index,0);// 设置学号
                String name = (String) jTable.getValueAt(index,1);// 设置姓名
                String classname = (String) jTable.getValueAt(index,2);// 设置专业
                String cs = (String) jTable.getValueAt(index,3);// 设置城市
                String age = (String) jTable.getValueAt(index,4);// 设置年龄
                // 打开详细页面
                new Stu_list(sno,name,classname,cs,age);
            }
        });

        //绑定监听
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
        final  String[] columnNames = {"学号", "姓名", "专业","城市", "年龄"};
        MySql mySql = new MySql();
        ArrayList<String[]> rowData = mySql.select(null);
        mySql.close();

        String[][] stringArray = rowData.toArray(new String[rowData.size()][]);
        JTable jt1 = new JTable (stringArray, columnNames);
        jt1.setPreferredScrollableViewportSize(new Dimension(800, 400));//设置表格的大小
        jt1.setRowHeight (30);//设置每行的高度为20
        jt1.setRowHeight (0, 30);//设置第1行的高度为15
        jt1.setRowMargin (5);//设置相邻两行单元格的距离
        jt1.setRowSelectionAllowed (true);//设置可否被选择.默认为false
        jt1.setSelectionBackground (Color.white);//设置所选择行的背景色
        jt1.setSelectionForeground (Color.blue);//设置所选择行的前景色
        jt1.setGridColor (Color.yellow);//设置网格线的颜色

        //设置表格居中
        DefaultTableCellRenderer dc=new DefaultTableCellRenderer();
        dc.setHorizontalAlignment(JLabel.CENTER);
        jt1.setDefaultRenderer(Object.class, dc);

        // 将表格添加到布局中 需要搭配JScrollPane实现滚动效果
        center.add(new JScrollPane(jt1));
        return jt1;
    }


    public void initTable(String query){
        // 打开连接
        MySql mySql = new MySql();
        ArrayList<String[]> rowData = mySql.select(query);
        String[][] stringArray = rowData.toArray(new String[rowData.size()][]);
        //关闭连接
        mySql.close();
        // 重新设置数据源
        //定义一个DefaultTableModel类，来实现TableModel接口
        DefaultTableModel model=new DefaultTableModel(stringArray,new String[]{"学号", "姓名", "专业","城市", "年龄"});
        this.jTable.setModel(model);
        this.jTable.repaint();
        this.jTable.updateUI();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == btcx){
            // 查询按钮
            String query = cx.getText();
            initTable(query);
        }else if (actionEvent.getSource() == shuaxin){
            initTable(null);
        }
    }
}
