import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class NoteMenu extends JFrame implements ActionListener {

    String noteReader, folderReader;

    JFrame deletingNote = new JFrame();
    JButton backButton, saveChangesButton, deleteNoteButton, confirmationButton, refusalButton;
    JTextArea noteArea;
    Methods methods = new Methods();
    JLabel mainNote, writeDownLabel, deletingLabel;
    JScrollPane scrollPane = new JScrollPane();

    NoteMenu(String title, String folderName){

        folderReader = folderName;
        noteReader = title;

        setTitle(title);
        setSize(600, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        backButton = new JButton();
        methods.setButton(backButton,"Go Back", 415,400, 150,20);
        backButton.setFont(new Font("Serif", Font.PLAIN, 14));
        backButton.addActionListener(this);
        add(backButton);

        saveChangesButton = new JButton();
        methods.setButton(saveChangesButton,"Save changes", 15,400, 150,20);
        saveChangesButton.setFont(new Font("Serif", Font.PLAIN, 14));
        saveChangesButton.addActionListener(this);
        add(saveChangesButton);

        deleteNoteButton = new JButton();
        methods.setButton(deleteNoteButton,"Delete current Note", 218,400, 150,20);
        deleteNoteButton.setFont(new Font("Serif", Font.PLAIN, 14));
        deleteNoteButton.addActionListener(this);
        add(deleteNoteButton);

        confirmationButton = new JButton();
        methods.setButton(confirmationButton,"Yes", 33, 120, 200, 50);
        confirmationButton.setFont(new Font("Serif", Font.PLAIN, 20));
        confirmationButton.addActionListener(this);
        deletingNote.add(confirmationButton);

        refusalButton = new JButton();
        methods.setButton(refusalButton,"No", 266, 120, 200, 50);
        refusalButton.setFont(new Font("Serif", Font.PLAIN, 20));
        refusalButton.addActionListener(this);
        deletingNote.add(refusalButton);

        mainNote = new JLabel();
        methods.setLabel(mainNote, noteReader+ " Note", 250, 25, 150, 30 );
        mainNote.setFont(new Font("Serif", Font.PLAIN, 20));
        add(mainNote);

        writeDownLabel = new JLabel();
        methods.setLabel(writeDownLabel, "Write below: ", 15, 85, 150, 30 );
        writeDownLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        add(writeDownLabel);

        deletingLabel = new JLabel();
        methods.setLabel(deletingLabel, null, 30, 50, 400, 50);
        deletingLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        deletingNote.add(deletingLabel);

        noteArea = new JTextArea(100, 100);
        noteArea.setBounds(15, 120, 550, 270);
        add(noteArea);

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getViewport().setBackground(Color.BLUE);
        scrollPane.getViewport().add(noteArea);
        scrollPane.setBounds(15, 120, 550, 270);
        add(scrollPane);
        

        try{
            File file = new File(folderReader, noteReader);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();

            while(line != null){

                noteArea.append(line+"\n");
                line=br.readLine();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton){
            FolderMenu openFolderMenu = new FolderMenu(folderReader);
            dispose();
        }else if( e.getSource() == saveChangesButton){

            try {

                File file = new File(folderReader, noteReader);
                PrintWriter printWriter = new PrintWriter(file);

                String[] lines = noteArea.getText().split("\\n");
                for(int i = 0 ; i < lines.length; i++) {
                    printWriter.println(lines[i]);
                }
                printWriter.close();
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }

        }else if ( e.getSource() == deleteNoteButton){

            dispose();

            deletingNote.setTitle("Delete a note");
            deletingNote.setSize(500, 250);
            deletingNote.setLayout(null);
            deletingNote.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            deletingNote.setVisible(true);

            deletingLabel.setText("Do you want to delete note: " + noteReader + " ?");

        }else if ( e.getSource() == confirmationButton){

            deletingNote.dispose();
            File file = new File(folderReader, noteReader);
            file.delete();

            Message removeNoteMessage = new Message("Note has been successfully deleted");

        }else if ( e.getSource() == refusalButton){

            NoteMenu openNoteMenu = new NoteMenu(noteReader, folderReader);

        }
    }
}
