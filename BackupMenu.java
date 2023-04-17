import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class BackupMenu extends JFrame implements ActionListener {

    JComboBox folderList = new JComboBox();
    Methods methods = new Methods();
    JLabel label = new JLabel();
    JButton backupButton, backToMenuButton;

    BackupMenu(){

        File foldersNames = new File("folders_names.txt");

        setTitle("Backup Menu");
        setSize(600, 300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        label = new JLabel();
        methods.setLabel(label,"Select folder to Back - Up", 150,75,350, 50);
        label.setFont(new Font("Serif", Font.PLAIN, 20));
        add(label);

        backToMenuButton = new JButton();
        methods.setButton(backToMenuButton,"Go to main Menu", 15,30, 150,20);
        backToMenuButton.setFont(new Font("Serif", Font.PLAIN, 14));
        backToMenuButton.addActionListener(this);
        add(backToMenuButton);

        backupButton = new JButton();
        methods.setButton(backupButton, "Back - Up folder", 350, 163, 150, 20);
        backupButton.setFont(new Font("Serif", Font.PLAIN, 14));
        backupButton.addActionListener(this);
        add(backupButton);

        folderList.setBounds(30, 150, 200,50);
        folderList.addActionListener(this);
        add(folderList);

        methods.fillComboBox(folderList, foldersNames);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if ( e.getSource() == backToMenuButton){
            Menu runMenu = new Menu();
            dispose();
        }
        else if ( e.getSource() == backupButton){

            File destination = new File ((String) folderList.getItemAt(folderList.getSelectedIndex()) + "C");
            File source = new File ((String) folderList.getItemAt(folderList.getSelectedIndex()));

            methods.copyFolder(source, destination);

            JFrame confirmationWindow = new JFrame();
            Message newMessage = new Message("BACK - UP HAS BEEN SUCCESSFULLY CREATED");
            dispose();
        }
    }
}

