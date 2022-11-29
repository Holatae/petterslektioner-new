package petterslektioner.numbergame;

import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static petterslektioner.numbergame.HttpClientClass.getPublicIP;

public class NumberGameGUIFunctions {
    public boolean GuessButtonPressed(NumberGame currentGame, String guess) {
        int guessInt;

        //Error Handling
        try {
            guessInt = Integer.parseInt(guess);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Guess must be a number");
            return true;
        }
        if (currentGame == null) {
            JOptionPane.showMessageDialog(null, "No game is running");
            return false;
        }
        //End of Error Handling

        if (currentGame.checkIfWin(guessInt)){
            //Show wining window
            JOptionPane.showMessageDialog(null, "You won!");
            String name = JOptionPane.showInputDialog(null, "Skriv ditt namn");

            var values = new JSONObject();
            try {
                values.put("name", name);
                values.put("ip", getPublicIP());
                values.put("difficulty", currentGame.getCurrentDifficulty());
                values.put("date", getDate());
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            try {
                HttpClientClass.sendPostRequestToServer(values, currentGame.getCurrentGameURL());
                return false;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        else {
            if (currentGame.isToLow(guessInt)){
            JOptionPane.showMessageDialog(null, "You guessed to low");
            }
            else {
                JOptionPane.showMessageDialog(null, "You guessed to high");
            }
        }

        if(currentGame.getGuessesLeft() <= 0){
            JOptionPane.showMessageDialog(null, "You lost");
            return false;
        }

        return true;
    }

    private String getDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
