package petterslektioner.numbergame;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;

/**
 * The HttpClientClass have all HTTP related functions in the game
 * This class can both send POST requests with the game score to a server and get validity of game server
 * This class also handles misc internet things like getting users IP adress
 */
public class HttpClientClass {
    /**
     * @param values a JSON object with everything the server needs to know
     * @param serverURL the server url where the game Server is located
     * @throws IOException throws execution if program cannot send a POST request for some reason
     */
    public static void sendPostRequestToServer(JSONObject values, String serverURL) throws IOException {
        HttpURLConnection con;
        System.out.println(values);
        try {
            URL url = new URL(serverURL + "/api/v1/post-new-score");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
            wr.writeBytes(values.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * @param serverURL The url to the game server
     * @return True if the server is valid and a legit server of the game. If it's false. The server is invalid
     */
    public static boolean checkIfValidServer(String serverURL) {
        HttpURLConnection con;
        try {
            URL url = new URL(serverURL + "/api/v1/check-if-valid-server");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
        } catch (IOException e) {
            return false;
        }
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response);
            return response.toString().equals("\"This server is 100% legit ;)\"");
        } catch (IOException e) {
            return false;
        }
    }
    /**
     * @return public IP adress of the user
     */
    public static String getPublicIP(){
        try {
            URL usersIP = new URL("https://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    usersIP.openStream()));
            return in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * '
     * @return local IP adress of the user
     */
    @SuppressWarnings("unused")
    public static String getLocalIP(){
        try (final DatagramSocket datagramSocket = new DatagramSocket()) {
            datagramSocket.connect(InetAddress.getByName("8.8.8.8"), 12345);
            return datagramSocket.getLocalAddress().getHostAddress();
        } catch (UnknownHostException | SocketException e) {
            throw new RuntimeException(e);
        }
    }
}
