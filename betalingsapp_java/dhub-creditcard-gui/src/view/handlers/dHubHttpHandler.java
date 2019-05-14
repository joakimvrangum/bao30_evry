package view.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class dHubHttpHandler {

    public static final String DHUB_SERVER = "http://127.0.0.1:8000/transaction/card";

    public static String dHubTransactionPOST(String card_nr, Double pris, String varetekst) throws IOException {
        URL obj = new URL(DHUB_SERVER);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        connection.setDoOutput(true);
        OutputStream os = connection.getOutputStream();

        String postdata = "cardNr=" + card_nr + "&transactionAmount=" + pris + "&transactionText=" + varetekst;
        os.write(postdata.getBytes());
        os.flush();
        os.close();

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        } else {
            return "Error! POST request feilet.";
        }

    }
}
