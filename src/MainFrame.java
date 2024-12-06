import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class MainFrame extends JFrame {
//    private JTextArea userTextArea;  // 用于显示注册的用户信息


    private static final String DB_URL = "jdbc:mysql://localhost:3306/demo2";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "123456";

    public MainFrame() {
        // 设置框架
        setTitle("相亲相爱");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 创建菜单栏
        JMenuBar menuBar = new JMenuBar();

        // 创建“个人信息”菜单
        JMenu dataMenu = new JMenu("个人信息");
        menuBar.add(dataMenu);

        // 创建“人物介绍”菜单
        JMenu objectMenu = new JMenu("对象信息");
        menuBar.add(objectMenu);


        // 创建“游戏设置”菜单
        JMenu settingsMenu = new JMenu("系统设置");
        menuBar.add(settingsMenu);


        JMenuItem viewUsersItem = new JMenuItem("查看自己信息");
        viewUsersItem.addActionListener((ActionEvent e) -> showUserInfoDialog());
        dataMenu.add(viewUsersItem);

//         为“修改”菜单项添加动作监听器
        JMenuItem registerItem = new JMenuItem("修改个人信息");
        registerItem.addActionListener((ActionEvent e) -> showRegisterFrame());
        dataMenu.add(registerItem);

        JMenuItem viewObjItem = new JMenuItem("查看对象信息");
        viewObjItem.addActionListener((ActionEvent e) -> showObjInfoDialog());
        objectMenu.add(viewObjItem);




        // 将菜单栏设置到框架
        setJMenuBar(menuBar);

    }
    private void showRegisterFrame() {
        RegisterFrame registerFrame = new RegisterFrame(this);
        registerFrame.setVisible(true);
    }


    private void showUserInfoDialog() {
        // 创建一个新的对话框来显示所有用户信息
        JDialog userInfoDialog = new JDialog(this, "用户信息", true);
        userInfoDialog.setSize(500, 300);

        // 创建一个文本区域来显示所有用户数据
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        userInfoDialog.add(scrollPane, BorderLayout.CENTER);

        // 获取用户数据并显示
        String userData = fetchUserData();
        textArea.setText(userData);

        // 显示对话框
        userInfoDialog.setVisible(true);
    }

    public void showObjInfoDialog() {
        JDialog objInfoDialog = new JDialog(this,"对象信息",true);
        objInfoDialog.setSize(500, 300);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        objInfoDialog.add(scrollPane, BorderLayout.CENTER);
        String objData = fetchObjData();
        textArea.setText(objData);
        objInfoDialog.setVisible(true);


    }
    private String fetchUserData() {
        StringBuilder userData = new StringBuilder();
        String query = "SELECT * FROM data";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // 获取并显示所有用户信息
            while (rs.next()) {
                String name = rs.getString("name");
                String age = rs.getString("age");
                String height = rs.getString("height");
                String weight = rs.getString("weight");
                String gender = rs.getString("gender");

                // 根据需求可以加更多字段
                userData.append("姓名: " + name + ", 年龄: " + age + ",身高: " + height + ",体重：" + weight + "性别：" + gender + "\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            userData.append("数据库连接失败！\n");
        }
        return userData.toString();
    }

    private String fetchObjData() {
        StringBuilder objData = new StringBuilder();
        String query = "SELECT * FROM obj";  // 假设你有一个objects表来存储对象信息
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // 获取并显示所有对象信息
            while (rs.next()) {
                String name = rs.getString("name");
                String age = rs.getString("age");
                String height = rs.getString("height");
                String weight = rs.getString("weight");
                String hobby = rs.getString("hobby");

                objData.append("姓名: " + name + ", 年龄: " + age + ", 身高: " + height + ", 体重: " + weight + "kg" + ", 爱好: " + hobby + "\n");
            }

        } catch (SQLException e) {
            objData.append("查询对象信息失败: " + e.getMessage() + "\n");
        }
        return objData.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }


    }




