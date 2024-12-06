import javax.swing.*;
import java.awt.*;
import java.sql.*;
public class LoginScreen extends JFrame {
    public LoginScreen() { //构造方法
        setTitle("登陆界面");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(500, 250);

        setLocationRelativeTo(null);

        setLayout(new FlowLayout());

        JLabel userLabel = new JLabel("账号:");
        JTextField userText = new JTextField(10);  // 设置文本框宽度为15
        JLabel passwordLabel = new JLabel("密码:");
        JPasswordField passwordField = new JPasswordField(10);// 设置密码框宽度为15
        JButton loginButton = new JButton("登录");
        JButton cancelButton = new JButton("取消");
        JButton enrollButton = new JButton("注册");


        // 添加组件到窗体
        add(userLabel);
        add(userText);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(cancelButton);
        add(enrollButton);

        loginButton.addActionListener(e -> {
            String username = userText.getText();
            String password = new String(passwordField.getPassword());

            // 简单的用户名和密码验证
            if (validateLogin(username, password)) {
                JOptionPane.showMessageDialog(this, "欢迎进入XX系统主界面！");
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "用户名或密码错误");
            }
        });

        // 取消按钮事件
        cancelButton.addActionListener(e -> {
            userText.setText("");  // 清空用户名输入框
            passwordField.setText("");  // 清空密码输入框
        });
        // 注册按钮事件
        EnrollListener enrollListener = new EnrollListener();
        enrollButton.addActionListener(enrollListener);
        enrollListener.setUser(userText);
        enrollListener.setPassword(passwordField);


    }



    private boolean validateLogin(String username, String password) {
        String url = "jdbc:mysql://localhost:3306/demo2"; // 修改为你的数据库地址
        String dbUsername = "root"; // 数据库用户名
        String dbPassword = "123456"; // 数据库密码

        String query = "SELECT * FROM users WHERE Username = ? AND Password = ?"; // 查询用户名和密码

        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            System.out.println("连接数据库成功");  // 调试信息
            stmt.setString(1, username);
            stmt.setString(2, password);
            //System.out.println("执行查询: " + query + "，参数: " + username + ", " + password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("用户名和密码匹配");  // 调试信息
                    return true;
                } else {
                    System.out.println("用户名或密码错误");  // 调试信息
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        // 启动界面
        SwingUtilities.invokeLater(() -> new LoginScreen().setVisible(true));
    }

}



