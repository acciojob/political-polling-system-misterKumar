package com.driver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PoliticalPollingSystem {

    private static final String API_ENDPOINT = "https://example.com/api/vote";

    public void castVote(String party) {
    	//your code goes here
        try {
            // Create URL object
            URL url = new URL(API_ENDPOINT);

            // Create connection object
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method
            connection.setRequestMethod("POST");

            // Set content type
            connection.setRequestProperty("Content-Type", "application/json");

            // Enable input/output streams
            connection.setDoOutput(true);

            // Construct JSON payload
            String jsonInputString = "{\"party\": \"" + party + "\"}";

            // Write JSON payload to connection output stream
            connection.getOutputStream().write(jsonInputString.getBytes());

            // Get response code
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Vote successfully recorded for party: " + party);
            } else {
                System.err.println("Failed to record vote. Server returned HTTP error code: " + responseCode);
            }

            // Close connection
            connection.disconnect();
        } catch (IOException e) {
            System.err.println("Error while connecting to API: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Political Polling System!");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PoliticalPollingSystem pollingSystem = new PoliticalPollingSystem();

        try {
            System.out.println("Please enter your preferred political party:");
            String party = reader.readLine();

            pollingSystem.castVote(party);
        } catch (IOException e) {
            System.err.println("Error while reading user input: " + e.getMessage());
        }
    }
}

