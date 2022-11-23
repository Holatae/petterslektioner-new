package petterslektioner.numbergame

import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.swing.JOptionPane

class NumberGameGUIFunctionsKT {
    public fun GuessButtonPressed(currentGame: NumberGame, guess: String): Boolean {
        val guessInt: Int;

        try {
            guessInt = guess.toInt();
        } catch (e: NumberFormatException) {
            JOptionPane.showMessageDialog(null, "Guess must be a number")
            return true;
        }

        // Checks of you won
        if (currentGame.checkIfWin(guessInt)) {
            //Show wining window
            JOptionPane.showMessageDialog(null, "You won!")
            val name = JOptionPane.showInputDialog(null, "What's your name?")

            val values = JSONObject();

            try {
                values.put("name", name)
                values.put("ip", HttpClientClass.getPublicIP())
                values.put("difficulty", currentGame.currentDifficulty)
                values.put("date", getDate())
            } catch (e: JSONException) {
                throw RuntimeException(e);
            }

            try {
                HttpClientClass.sendPostRequestToServer(values, currentGame.currentGameURL)
                return false;
            } catch (e: IOException) {
                throw RuntimeException(e);
            }

        } else {
            if (currentGame.isToLow(guessInt)) {
                JOptionPane.showMessageDialog(null, "You guessed to low")
            } else {
                JOptionPane.showMessageDialog(null, "You guessed to high")
            }
        }

        // If Guesses left are 0 or less. Then you have lost
        if (currentGame.guessesLeft <= 0) {
            JOptionPane.showMessageDialog(null, "You are out of guesses");
            return false;
        }
        return true;
    }

    private fun getDate(): String {
        val dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")
        val now = LocalDateTime.now()
        return dtf.format(now)
    }
}
