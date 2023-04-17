import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Message extends JFrame implements ActionListener {

    Methods methods = new Methods();

    JLabel message = new JLabel();

    JButton backButton;

    Message(String warningMessage){

        setTitle("MESSAGE WINDOW");
        setSize(450, 250);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        methods.setLabel(message, warningMessage, 30, 80, 400, 50);
        message.setFont(new Font("Serif", Font.PLAIN, 18));

        add(message);

        backButton = new JButton();
        methods.setButton(backButton,"Go Back", 30,130, 150,20);
        backButton.setFont(new Font("Serif", Font.PLAIN, 12));
        backButton.addActionListener(this);
        add(backButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ( e.getSource() == backButton){
            Menu runFolders = new Menu();
            dispose();
        }
    }
}
