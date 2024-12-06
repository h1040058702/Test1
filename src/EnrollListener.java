import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EnrollListener implements ActionListener {
    private JTextField tuser; // 用户名输入框
    private JPasswordField tpassword; // 密码输入框

    public void actionPerformed(ActionEvent e) {
        if (tuser != null && tpassword != null) {
            // 获取用户名和密码

            String username = tuser.getText();
            String password = new String(tpassword.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "用户名和密码不能为空");
                return;
            }

            if (registerUser(username, password)) {
                JOptionPane.showMessageDialog(null, "注册成功！");
            } else {
                JOptionPane.showMessageDialog(null, "注册失败，请稍后再试！");
            }
        }

    }

    public void setUser(JTextField text) {
        this.tuser = text;
    }
    public void setPassword(JTextField text) {
        this.tpassword = (JPasswordField) text;
    }



    private boolean registerUser(String username, String password) {
        String url = "jdbc:mysql://localhost:3306/demo2";  // 修改为你的数据库地址
        String dbUsername = "root";  // 数据库用户名
        String dbPassword = "123456";  // 数据库密码

        String query = "INSERT INTO users (Username, Password) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  // 如果插入成功，则返回true
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}

