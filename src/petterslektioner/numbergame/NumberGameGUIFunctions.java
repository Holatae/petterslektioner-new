package petterslektioner.numbergame;

import netscape.javascript.JSObject;

import javax.swing.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;

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

        if (currentGame.checkIfWin(guessInt) || true){
            //Show wining window
            JOptionPane.showMessageDialog(null, "You won!");
            String name = JOptionPane.showInputDialog(null, "Skriv ditt namn");






            try {
                var values = new JSONObject();
                try {
                    values.put("name",name);
                    values.put("ip", "1231345");
                    values.put("score", 23);
                    values.put("date", "no Thanks");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                System.out.println(values);

                URL url = new URL("http://127.0.0.1:8000/api/v1/post-new-score");
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");

                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);

                DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                dos.writeBytes(values.toString());
                dos.flush();
                dos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

/*            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://127.0.0.1:8000/api/v1/post-new-score"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
            System.out.println(request.method());
            try {
                client.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }*/
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
}
