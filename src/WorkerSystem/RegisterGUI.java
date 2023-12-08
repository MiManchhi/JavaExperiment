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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLayout(new GridLayout(3, 2));
        //置中显示
        setLocationRelativeTo(null);

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        registerButton = new JButton("注册");

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (register()) {
                    JOptionPane.showMessageDialog(RegisterGUI.this, "注册成功");
                } else {
                    JOptionPane.showMessageDialog(RegisterGUI.this, "注册失败，请重试");
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

    public boolean register() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/javaexperiment";
        String dbUsername = "root";
        String dbPassword = "123456";

        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword)) {
            String sql = "INSERT INTO login_info (username, password) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RegisterGUI registerGUI = new RegisterGUI();
            registerGUI.setVisible(true);
        });
    }
}
