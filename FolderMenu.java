import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Formatter;

public class FolderMenu extends JFrame implements ActionListener {

    String nameOfFolder;
    Methods methods = new Methods(); // creation of an object from Methods class, enables to use all of the methods from this class

    JButton backButton, addNoteButton, enterNoteButton, deleteFolderButton, createNoteButton, confirmationButton, refusalButton;
    JLabel  mainLabel, createNoteLabel, deletingLabel, addingNoteLabel;
    JComboBox listOfNotes = new JComboBox();
    JFrame addingNote = new JFrame();
    JTextField nameOfNote;
    JFrame deletingFolder = new JFrame();



    FolderMenu(String title){

        nameOfFolder = title;


        setTitle(title);
        setSize(600, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        backButton = new JButton();
        methods.setButton(backButton,"Go Back", 15,30, 150,20); // thanks to the object, I am able to use method
        // setButton in this class
        backButton.setFont(new Font("Serif", Font.PLAIN, 14));
        backButton.addActionListener(this);
        add(backButton);

        addNoteButton = new JButton();
        methods.setButton(addNoteButton,"Add Note", 15,150, 175,50);
        addNoteButton.setFont(new Font("Serif", Font.PLAIN, 14));
        addNoteButton.addActionListener(this);
        add(addNoteButton);

        enterNoteButton = new JButton();
        methods.setButton(enterNoteButton,"Enter selected note", 15,210, 175,50);
        enterNoteButton.setFont(new Font("Serif", Font.PLAIN, 14));
        enterNoteButton.addActionListener(this);
        add(enterNoteButton);

        deleteFolderButton = new JButton();
        methods.setButton(deleteFolderButton,"Delete folder", 15,300, 175,50);
        deleteFolderButton.setFont(new Font("Serif", Font.PLAIN, 14));
        deleteFolderButton.addActionListener(this);
        add(deleteFolderButton);

        createNoteButton = new JButton();
        methods.setButton(createNoteButton,"Create Note", 145, 120, 200, 50);
        createNoteButton.setFont(new Font("Serif", Font.PLAIN, 20));
        createNoteButton.addActionListener(this);
        addingNote.add(createNoteButton);

        confirmationButton = new JButton();
        methods.setButton(confirmationButton,"Yes", 33, 120, 200, 50);
        confirmationButton.setFont(new Font("Serif", Font.PLAIN, 20));
        confirmationButton.addActionListener(this);
        deletingFolder.add(confirmationButton);

        refusalButton = new JButton();
        methods.setButton(refusalButton,"No", 266, 120, 200, 50);
        refusalButton.setFont(new Font("Serif", Font.PLAIN, 20));
        refusalButton.addActionListener(this);
        deletingFolder.add(refusalButton);

        mainLabel = new JLabel();
        methods.setLabel(mainLabel,title + " Folder", 250,25,200, 30);
        mainLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        add(mainLabel);

        createNoteLabel = new JLabel();
        methods.setLabel(createNoteLabel, "Name of Note: ", 30, 50, 200, 50);
        createNoteLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        addingNote.add(createNoteLabel);

        deletingLabel = new JLabel();
        methods.setLabel(deletingLabel, null, 30, 50, 400, 50);
        deletingLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        deletingFolder.add(deletingLabel);

        addingNoteLabel = new JLabel();
        methods.setLabel(addingNoteLabel, "The name must not contain special symbols", 125, 80, 250, 50);
        addingNoteLabel.setFont(new Font("Serif", Font.PLAIN, 10));
        addingNote.add(addingNoteLabel);

        nameOfNote = new JTextField();
        methods.setTextField(nameOfNote,null,180, 50, 150, 50);
        addingNote.add(nameOfNote);

        listOfNotes.setBounds(350, 175, 200,50);
        listOfNotes.addActionListener(this);
        add(listOfNotes);

        File file = new File(title);
        methods.listFilesForFolder(file, listOfNotes);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String noteName = nameOfNote.getText();

        if ( e.getSource() == backButton){
            Folders openFolders = new Folders();
            dispose();
        }
        else if ( e.getSource() == addNoteButton){

            addingNote.setTitle("Add a note");
            addingNote.setSize(500, 250);
            addingNote.setLayout(null);
            addingNote.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            addingNote.setVisible(true);


        }else if ( e.getSource() == createNoteButton){


            File newNote = new File(nameOfFolder, noteName );
            addingNote.dispose();

            if (noteName.isEmpty()){

                this.dispose();
                dispose();
                Message newWarning = new Message("Provide a name for a note");

            } else{
                if(!newNote.exists()) {
                    try {
                        FileWriter writer = new FileWriter(newNote, true);
                        Formatter formatter = new Formatter(writer);
                        formatter.format("\n");

                        listOfNotes.addItem(nameOfNote.getText());
                        formatter.close();
                        writer.close();

                    } catch (IOException a) {
                        a.printStackTrace();
                    }
                }else{
                    this.dispose();
                    Message warning = new Message("Note already exists!");
                }
            }
        } else if (e.getSource() == deleteFolderButton ) {

            dispose();

            deletingFolder.setTitle("Delete a folder");
            deletingFolder.setSize(500, 250);
            deletingFolder.setLayout(null);
            deletingFolder.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            deletingFolder.setVisible(true);

            deletingLabel.setText("Do you want to delete folder: " + nameOfFolder + " ?");

        }else if( e.getSource() == confirmationButton){

            deletingFolder.dispose();

            File folderToBeDeleted = new File (nameOfFolder);
            methods.deleteDirectory(folderToBeDeleted);
            Message removeFolderMessage = new Message("Folder has been successfully deleted");

            String tempFile = "temp.txt";
            File nameOfFolders = new File("folders_names.txt");
            File newFile = new File(tempFile);

            String currentLine;


            try {


                FileWriter fw = new FileWriter(tempFile, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);

                FileReader fr = new FileReader("folders_names.txt");
                BufferedReader br = new BufferedReader(fr);

                while ( (currentLine = br.readLine()) !=null){

                    if (!(currentLine.equals(nameOfFolder))){
                        pw.println(currentLine);
                    }
                }

                pw.flush();
                pw.close();
                fr.close();
                br.close();
                bw.close();
                fw.close();

                nameOfFolders.delete();
                File name = new File("folders_names.txt");
                newFile.renameTo(name);


            } catch (IOException e1) {
                e1.printStackTrace();
            }

            dispose();
        }else if(e.getSource()==refusalButton){

            FolderMenu openFolderMenu = new FolderMenu(nameOfFolder); //Opens menu of the Folder, when refused to delete it

        }else if ( e.getSource() == enterNoteButton){
            NoteMenu openMenu = new NoteMenu((String) listOfNotes.getItemAt(listOfNotes.getSelectedIndex()), nameOfFolder);
            //Enters the note selected in the JComboBox
            dispose();

        }
    }
}


