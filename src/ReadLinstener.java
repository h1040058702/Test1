import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReadLinstener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println(command + "的长度" + command.length());
    }
}
