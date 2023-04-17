import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {


    JButton foldersMenuButton;
    JLabel mainLabel;

    Methods methods = new Methods();

    Menu(){


        setTitle("Notes");
        setSize(600, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


        mainLabel = new JLabel();
        methods.setLabel(mainLabel, "Welcome in Notebook App", 180, 100, 300, 100);
        mainLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        add(mainLabel);
        

        foldersMenuButton = new JButton();
        methods.setButton(foldersMenuButton, "Go to folder menu",230,230, 150, 100);
        foldersMenuButton.addActionListener(this);
        add(foldersMenuButton);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ( e.getSource() == foldersMenuButton){
            Folders runFolderMenu = new Folders();
            dispose();
        }
    }
}
