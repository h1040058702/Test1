import javax.swing.*;
import java.awt.*;
import java.net.http.WebSocket;

public class Window extends JFrame {

    ReadLinstener listener;

    public Window(String s,int x,int y,int w,int h) {
        init(s);

        setLocation(x, y);
        setSize(w, h);

        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

        void init(String s){
            setTitle(s);

            setLayout(new FlowLayout());

            JLabel userLabel = new JLabel("用户名");

            JTextField userText = new JTextField(10);

            JButton loginButton = new JButton("登录");

            listener = new ReadLinstener();

            loginButton.addActionListener(listener);
            //userText.addActionListener(listener);



            add(userLabel);
            add(userText);
            add(loginButton);

        }
}
