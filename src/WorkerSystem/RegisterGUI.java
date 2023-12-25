package WorkerSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class RegisterGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;

    public RegisterGUI() {
        setTitle("注册");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 关闭当前窗口，而不是退出整个应用
        setSize(300, 150);
        setLayout(new GridLayout(3, 2));
        setLocationRelativeTo(null);

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        registerButton = new JButton("注册");

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (register(username, password)) {
                    JOptionPane.showMessageDialog(RegisterGUI.this, "注册成功");
                } else {
                    JOptionPane.showMessageDialog(RegisterGUI.this, "注册失败");
                }
            }
        });

        add(new JLabel("用户名:"));
        add(usernameField);
        add(new JLabel("密码:"));
        add(passwordField);
        add(new JLabel(""));
        add(registerButton);
    }

    private boolean register(String username, String password) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/javaexperiment";
        String dbUsername = "root";
        String dbPassword = "123456";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword)) {
            String sql = "INSERT INTO login_info (username, password) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}