package com.bao30;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class dHubHttpHandler {

    public static final String DHUB_SERVER = "http://www.vrangum.com/dhub/api/transaction/test";

    public static void dHubTransactionPOST(String card_nr, Double pris, String varetekst) throws IOException {
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
        System.out.println("POST responded :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Print resultatet
            System.out.println(response.toString());
            } else {
            System.out.println("POST request failed!");
    }

    }
}
