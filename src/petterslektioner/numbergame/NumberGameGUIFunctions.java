package petterslektioner.numbergame;

import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class NumberGameGUIFunctions {
    public boolean GuessButtonPressed(NumberGame currentGame, String guess, JLabel debugLabel) {
        int guessInt;

        if(guess == "DEBUG"){
            //TODO DEBUG stuff
        }
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
                values.put("score", 23);
                values.put("date", getDate());
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            try {
                HttpClientClass.sendPostRequestToServer(values, currentGame.getCurrentGameURL());
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
            JOptionPane.showMessageDialog(null, "Sug kuk");
            return false;
        }

        return true;
    }



    private String getLocalIP(){
        try (final DatagramSocket datagramSocket = new DatagramSocket()) {
            datagramSocket.connect(InetAddress.getByName("8.8.8.8"), 12345);
            return datagramSocket.getLocalAddress().getHostAddress();
        } catch (UnknownHostException | SocketException e) {
            throw new RuntimeException(e);
        }
    }

    private String getPublicIP(){
        try {
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));
            return in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
