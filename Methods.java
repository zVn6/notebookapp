import javax.swing.*;
import java.io.*;

public class Methods extends JFrame {


    public JButton setButton(JButton button, String text, int x, int y, int width, int height) {
        button.setText(text);
        button.setBounds(x, y, width, height);
        return button;
    }

    public JLabel setLabel(JLabel label, String text, int x, int y, int width, int height) {
        label.setText(text);
        label.setBounds(x, y, width, height);
        return label;
    }


    public JTextField setTextField(JTextField textField, String text, int x, int y, int width, int height) {
        textField.setText(text);
        textField.setBounds(x, y, width, height);
        return textField;
    }

    public void fillComboBox(JComboBox folderList, File foldersNames) {
        if (foldersNames.canWrite()) {

            try {

                FileReader reader = new FileReader(foldersNames);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    folderList.addItem(line);
                }
                reader.close();
            } catch (IOException a) {
                a.printStackTrace();
            }
        }
    }
    
    public void listFilesForFolder(File folder, JComboBox comboBox) {
        for (final File fileEntry : folder.listFiles()) { //using foreach to search every element in File folder
            if (fileEntry.isDirectory()) { // checking if the file is another folder
                listFilesForFolder(fileEntry, comboBox); // if yes it uses recursion to "dig up" to the elements in this directory
            } else {
                comboBox.addItem(fileEntry.getName()); // if no it adds the name of the file to JComboBox
            }
        }
    }

    boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }

    public void copyFolder(File source, File destination){

        if (source.isDirectory()){

            if (!destination.exists()) {
                destination.mkdirs();
            }

            String files[] = source.list();

            for (String file : files){
                File srcFile = new File(source, file);
                File destFile = new File(destination, file);

                copyFolder(srcFile, destFile);
            }
        } else {
            InputStream input = null;
            OutputStream output = null;
            try {

                input = new FileInputStream(source);
                output = new FileOutputStream(destination);

                byte[] buffer = new byte[1024];

                int length;
                while ((length = input.read(buffer)) > 0)
                {
                    output.write(buffer, 0, length);
                }
            } catch (Exception e){
                try {
                    input.close();
                } catch (IOException e1){
                    e1.printStackTrace();
                }
                try{
                    output.close();
                } catch (IOException e1){
                    e1.printStackTrace();
                }
            }
        }
    }



}




