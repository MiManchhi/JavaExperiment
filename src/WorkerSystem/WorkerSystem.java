package WorkerSystem;

import WorkerManager.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.*;

public class WorkerSystem {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginGUI loginGUI = new LoginGUI();
            loginGUI.setVisible(true);
            loginGUI.getLoginButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (loginGUI.authenticate()) {
                        loginGUI.setVisible(false);
                        WorkerManager wm = new WorkerManager();
                        //创建窗口
                        JFrame frame = new JFrame("职工信息管理系统");
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        // 创建一个面板，并设置GridLayout
                        JPanel panel = new JPanel();
                        GridLayout gridLayout = new GridLayout(0, 1); // 单列网格布局，垂直排列
                        panel.setLayout(gridLayout);
                        // 创建多个按钮
                        JButton button1 = new JButton("增加职工信息");
                        JButton button2 = new JButton("显示职工信息");
                        JButton button3 = new JButton("删除离职职工");
                        JButton button4 = new JButton("修改职工信息");
                        JButton button5 = new JButton("查找职工信息");
                        JButton button6 = new JButton("按照编号排序");
                        JButton button7 = new JButton("清空所有文档");
                        JButton button8 = new JButton("退出管理程序");
                        // 将按钮添加到面板
                        panel.add(button1);
                        panel.add(button2);
                        panel.add(button3);
                        panel.add(button4);
                        panel.add(button5);
                        panel.add(button6);
                        panel.add(button7);
                        panel.add(button8);
                        // 将面板添加到窗口
                        frame.add(panel);
                        //设置窗口大小
                        frame.setSize(300, 600);
                        //显示窗口
                        frame.setVisible(true);
                        //处理事件
                        //增加职工信息
                        button1.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                wm.AddWorker();
                            }
                        });
                        //显示职工信息
                        button2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                wm.ShowWorker();
                            }
                        });
                        //删除离职职工
                        button3.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                wm.DeleteWorker();
                            }
                        });
                        //修改职工信息
                        button4.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                wm.ModWorker();
                            }
                        });
                        //查找职工信息
                        button5.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                wm.SearchWorker();
                            }
                        });
                        //按照编号排序
                        button6.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                wm.SortWorker();
                            }
                        });
                        //清空所有文档
                        button7.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                wm.ClearFile();
                            }
                        });
                        //退出管理程序
                        button8.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                System.exit(0);
                            }
                        });
                        frame.setVisible(true);
                    }
                    else {
                        // 处理登录失败的情况，可以给出相应的提示
                        JOptionPane.showMessageDialog(null, "登录失败，程序将退出。", "失败", JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                    }
                }
            });
        });
    }
}
