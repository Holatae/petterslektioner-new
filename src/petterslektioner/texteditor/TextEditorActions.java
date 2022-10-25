package petterslektioner.texteditor;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.ArrayList;

public class TextEditorActions {
    public void lockFileButtonAction(){

    }

    public String saveFileButtonAction(String filePath, String text){
        filePath = showSaveDialog(filePath);
        return saveFile(filePath, text);

    }

    private static String saveFile(String filePath, String text) {
        filePath = showSaveDialog(filePath);
        if (filePath == null) return null;

        File file = new File(filePath);
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(text);

            bufferedWriter.close();
            fileWriter.close();
            return filePath;
        }catch (IOException e) {
            System.err.println("IOException has been lifted.");
        }
        return null;
    }

    private static String showSaveDialog(String filePath) {
        if ( filePath == null || filePath.isEmpty()){
            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            int r = j.showSaveDialog(null);
            if (r != JFileChooser.APPROVE_OPTION) {
                return null;
            }
            filePath = j.getSelectedFile().getAbsolutePath();
        }
        return filePath;
    }

    /**
     * @return an array with index[0] File path and index[1] text in file, returns null if not approved
     */
    public String[] openFileButtonAction(){
        JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int r = j.showOpenDialog(null);
        if (r != JFileChooser.APPROVE_OPTION) {return null;}
        ArrayList<String> currentFileContent = new ArrayList<>();

        try{
            FileReader fileReader = new FileReader(j.getSelectedFile().getAbsoluteFile());
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null){
                currentFileContent.add(currentLine);
                currentFileContent.add("\n");
            }


            bufferedReader.readLine();
        }catch (Exception e){
            return null;
        }
        return new String[] {j.getSelectedFile().getAbsolutePath(), currentFileContent.toString()};
    }

    public String saveAsFileButtonAction(String text){
        String filePath = showSaveDialog(null);
        saveFile(filePath, text);
        return filePath;
    }
}
