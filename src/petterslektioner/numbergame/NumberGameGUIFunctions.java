package petterslektioner.numbergame;

import javax.swing.*;

public class NumberGameGUIFunctions {
    public boolean GuessButtonPressed(NumberGame currentGame, String guess, JLabel debugLabel) {
        int guessInt;

        if(guess == "DEBUG"){

        }
        //Error Handling
        try {
            guessInt = Integer.parseInt(guess);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Guess must be a number");
            return false;
        }
        if (currentGame == null) {
            JOptionPane.showMessageDialog(null, "No game is running");
            return false;
        }
        //End of Error Handling

        if (currentGame.checkIfWin(guessInt)){
            //Show wining window
            JOptionPane.showMessageDialog(null, "You won!");
        }

        return true;
    }
}
