package WorkerSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class LoginOrRegisterGUI extends JFrame {
    private JButton loginButton;
    private JButton registerButton;
    private JPanel mainPanel;
    private JLabel backgroundLabel;

    public LoginOrRegisterGUI() {
        setTitle("登录或注册");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        initializeComponents();
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        setResizable(false);
        setLocationRelativeTo(null);  // 居中显示
    }

    private void initializeComponents() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // 背景图片
        try {
            ImageIcon backgroundIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("background.jpg")));
            Image backgroundImg = backgroundIcon.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
            backgroundIcon = new ImageIcon(backgroundImg);
            backgroundLabel = new JLabel(backgroundIcon);
            mainPanel.add(backgroundLabel, BorderLayout.CENTER);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        // 登录按钮
        loginButton = new JButton("登录");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLoginGUI();
            }
        });

        // 注册按钮
        registerButton = new JButton("注册");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRegisterGUI();
            }
        });

        // 按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void openLoginGUI() {
        LoginGUI loginGUI = new LoginGUI();
        loginGUI.setVisible(true);
        this.dispose();  // 关闭当前窗口
    }

    private void openRegisterGUI() {
        RegisterGUI registerGUI = new RegisterGUI();
        registerGUI.setVisible(true);
        this.dispose();  // 关闭当前窗口
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginOrRegisterGUI gui = new LoginOrRegisterGUI();
            gui.setVisible(true);
        });
    }
}