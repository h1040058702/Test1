import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class RegisterFrame extends JDialog {
    private JTextField nameField;;
    private JTextField idField;
    private JTextField ageField;
    private JTextField heightField;
    private JTextField weightField;
    private JComboBox<String> genderComboBox;
    private JButton submitButton;
    private MainFrame mainFrame;

    // 数据库连接信息
    private static final String DB_URL = "jdbc:mysql://localhost:3306/demo2";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "123456";

    public RegisterFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        // 设置框架
        setTitle("修改信息");
        setSize(300, 500);
        setLayout(new GridLayout(7, 2));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(mainFrame);
        Font font = new Font("Microsoft YaHei", Font.PLAIN, 14);

        // 添加输入字段和标签
        add(new JLabel("姓名:"));
        nameField = new JTextField();
        nameField.setFont(font);
        add(nameField);

        add(new JLabel("序号:"));
        idField = new JTextField();
        idField.setFont(font);
        add(idField);

        add(new JLabel("年龄"));
        ageField = new JTextField();
        ageField.setFont(font);
        add(ageField);

        add(new JLabel("身高"));
        heightField = new JTextField();
        heightField.setFont(font);
        add(heightField);

        add(new JLabel("体重"));
        weightField = new JTextField();
        weightField.setFont(font);
        add(weightField);

        add(new JLabel("性别"));
        genderComboBox = new JComboBox<>(new String[]{"男" , "女"});
        genderComboBox.setFont(font);
        add(genderComboBox);

        submitButton = new JButton("修改");
        submitButton.setFont(font);
        add(submitButton);

        // 添加提交按钮的事件监听器
        submitButton.addActionListener((ActionEvent e) -> {
            String name = nameField.getText();
            String id  = idField.getText();
            String age = ageField.getText();
            String height = heightField.getText();
            String weight = weightField.getText();
            String gender = (String) genderComboBox.getSelectedItem();
            if (name.isEmpty() || id.isEmpty() || age.isEmpty() || height.isEmpty() || weight.isEmpty() || gender == null) {
                JOptionPane.showMessageDialog(this, "所有字段都必须填写！");
            } else {
                // 校验年龄、身高、体重是否为有效的数字
                if (!isNumeric(id) || !isNumeric(age) || !isNumeric(height) || !isNumeric(weight)) {
                    JOptionPane.showMessageDialog(this, "序号、年龄、身高和体重必须是有效的数字！");
                } else {
                    // 将数据插入数据库
                    if (updaterUser(name, id,age, height, weight, gender)) {
                        JOptionPane.showMessageDialog(this, "修改成功");
                        // 更新主界面显示用户信息
                        // mainFrame.updateUserList(); // 如果需要更新主界面的用户列表
                        dispose();  // 关闭注册窗口
                    } else {
                        JOptionPane.showMessageDialog(this, "添加失败，请重试！");
                    }
                }
            }
        });
    }

    // 注册用户信息到数据库
    private boolean updaterUser(String name,String id, String age, String height, String weight,String gender) {
        String query = "UPDATE data SET name = ?, age = ?, height = ?, weight = ?, gender = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, age);
            stmt.setString(3, height);
            stmt.setString(4, weight);
            stmt.setString(5, gender);
            stmt.setString(6, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  // 如果有记录更新，返回true
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);  // 尝试将字符串转换为数字
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
