package petterslektioner.texteditor;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class TextEditorActions {
    public void lockFileButtonAction(){

    }

    public void saveFileButtonAction(){

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
}
