import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Formatter;

public class Folders extends JFrame implements ActionListener {

    File foldersNames = new File("folders_names.txt");


    JFrame addingFolder = new JFrame();
    JLabel foldersLabel, createFolderLabel, addingFolderLabel;
    JButton backToMenuButton, addFolderButton, backupFolderButton, goToFolderButton, createFolderButton;
    JComboBox folderList = new JComboBox();
    JTextField nameOfFolder;

    Methods methods = new Methods();

    Folders(){

        setTitle("Folder Menu");
        setSize(600, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        //Labels

        foldersLabel = new JLabel();
        methods.setLabel(foldersLabel,"Folders", 265,30,100, 20);
        foldersLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        add(foldersLabel);

        createFolderLabel = new JLabel();
        methods.setLabel(createFolderLabel, "Name of folder: ", 30, 50, 200, 50);
        createFolderLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        addingFolder.add(createFolderLabel);

        addingFolderLabel = new JLabel();
        methods.setLabel(addingFolderLabel, "The name must not contain special symbols", 125, 80, 250, 50);
        addingFolderLabel.setFont(new Font("Serif", Font.PLAIN, 10));
        addingFolder.add(addingFolderLabel);

        //Buttons

        backToMenuButton = new JButton();
        methods.setButton(backToMenuButton,"Go to main Menu", 15,30, 150,20);
        backToMenuButton.setFont(new Font("Serif", Font.PLAIN, 14));
        backToMenuButton.addActionListener(this);
        add(backToMenuButton);

        addFolderButton = new JButton();
        methods.setButton(addFolderButton,"Add Folder Here", 15, 175,200,50);
        addFolderButton.setFont(new Font("Serif", Font.PLAIN, 20));
        addFolderButton.addActionListener(this);
        add(addFolderButton);

        backupFolderButton = new JButton();
        methods.setButton(backupFolderButton,"Backup Folder Here", 15, 300, 200, 50);
        backupFolderButton.setFont(new Font("Serif", Font.PLAIN, 20));
        backupFolderButton.addActionListener(this);
        add(backupFolderButton);

        goToFolderButton = new JButton();
        methods.setButton(goToFolderButton, "Go to selected Folder", 350, 300, 200, 50);
        goToFolderButton.setFont(new Font("Serif", Font.PLAIN, 14));
        goToFolderButton.addActionListener(this);
        add(goToFolderButton);

        createFolderButton = new JButton();
        methods.setButton(createFolderButton,"Create Folder", 145, 120, 200, 50);
        createFolderButton.setFont(new Font("Serif", Font.PLAIN, 20));
        createFolderButton.addActionListener(this);
        addingFolder.add(createFolderButton);

        //Text Fields

        nameOfFolder = new JTextField();
        methods.setTextField(nameOfFolder,null,180, 50, 150, 50);
        addingFolder.add(nameOfFolder);


        folderList.setBounds(350, 175, 200,50);
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
        else if ( e.getSource() == addFolderButton){

            addingFolder.setTitle("Add a folder");
            addingFolder.setSize(500, 250);
            addingFolder.setLayout(null);
            addingFolder.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            addingFolder.setVisible(true);

        }
        else if ( e.getSource() == goToFolderButton){

            FolderMenu runNoteMenu = new FolderMenu((String) folderList.getItemAt(folderList.getSelectedIndex()));
            dispose();
        }
        else if ( e.getSource() == createFolderButton){
            addingFolder.dispose();
            String folderName = nameOfFolder.getText();


            if (folderName.isEmpty()){
                boolean flag = true;

                while(flag){

                    dispose();
                    Message newWarning = new Message("Provide a name for a folder");
                    flag = false;

                }


            }
            else{
                if (foldersNames.canWrite()){
                    try{
                        FileWriter writer = new FileWriter(foldersNames, true);
                        Formatter formatter = new Formatter(writer);
                        formatter.format( folderName + "\n" );

                        File file = new File(folderName);
                        if (!file.exists()){
                            folderList.addItem(folderName);
                            file.mkdir();
                            formatter.close();
                            writer.close();
                        }else{

                            dispose();
                            Message warning = new Message("Folder already exists!");}

                    }catch (IOException a){
                        a.printStackTrace();
                    }


                }
            }

        }
        else if ( e.getSource() == backupFolderButton){
            BackupMenu backupFolder = new BackupMenu();
            dispose();
        }
    }
}

