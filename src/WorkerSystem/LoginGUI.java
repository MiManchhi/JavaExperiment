package WorkerSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private String Password;

    public LoginGUI() {
        setTitle("登录");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLayout(new GridLayout(3, 2));
        setLocationRelativeTo(null);

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("登录");
        registerButton = new JButton("注册");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = new String(passwordField.getPassword());
                LoginGUI.this.Password = password;

                if (authenticate()) {
                    // JOptionPane.showMessageDialog(LoginGUI.this, "登录成功");
                } else {
                    JOptionPane.showMessageDialog(LoginGUI.this, "登录失败");
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 创建并显示注册界面
                //setVisible(false);
                new RegisterGUI().setVisible(true);
            }
        });

        add(new JLabel("用户名:"));
        add(usernameField);
        add(new JLabel("密码:"));
        add(passwordField);
        add(registerButton);
        add(loginButton);
    }

    public boolean authenticate() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        String jdbcUrl = "jdbc:mysql://localhost:3306/javaexperiment";
        String dbUsername = "root";
        String dbPassword = "123456";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword)) {
            String sql = "SELECT * FROM login_info WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String GetPassword() {
        return this.Password;
    }

    public JButton getLoginButton() {
        return loginButton;
    }
}