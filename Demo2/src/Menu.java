import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {
    //框架大小
    Dimension screenSize = new Dimension(800, 600);
    Image icon;
    JMenuBar mainMenu = new JMenuBar();

    //建立"个人信息"菜单组
    JMenu personalData = new JMenu("个人信息");
    //建立"对象信息"菜单组
    JMenu objectInfo = new JMenu("对象信息");

    public Menu() {
        enableEvents(AWTEvent.KEY_EVENT_MASK);
        //添加框架的关闭事件
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.pack();
        //设置框架的大小
        this.setSize(screenSize);

        this.setTitle("相亲相爱");

        icon = Toolkit.getDefaultToolkit().getImage("icon.gif");
        this.setIconImage(icon);

        try{
            Init();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private void Init() throws Exception{
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());

        mainMenu.add(personalData);
        mainMenu.add(objectInfo);

        this.setJMenuBar(mainMenu);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // 创建并显示菜单窗口
                Menu menu = new Menu();
                menu.setVisible(true);
            }
        });
    }
}

