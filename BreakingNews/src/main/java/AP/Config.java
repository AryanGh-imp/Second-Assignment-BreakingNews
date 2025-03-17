package AP;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Config {

    private static final String CONFIG_FILE = "BreakingNews/src/main/resources/config.properties";

    // Reading API KEY from config.properties (if there is)
    public static String getApiKey() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(CONFIG_FILE)) {
            properties.load(input);
            String apiKey = properties.getProperty("apiKey");

            if (apiKey == null || apiKey.isEmpty()) {
                // if there is no API KEY, we will ask the user to enter it
                apiKey = requestAndSaveApiKey();
            }
            return apiKey;
        } catch (IOException e) {
            // If the file is not found, we ask the user for the API Key.
            return requestAndSaveApiKey();
        }
    }

    // Request API Key from user and save it to file
    private static String requestAndSaveApiKey() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your API Key: ");
        String apiKey = scanner.nextLine();

        // Save API Key in config.properties file
        Properties properties = new Properties();
        properties.setProperty("apiKey", apiKey);
        try (FileOutputStream output = new FileOutputStream(CONFIG_FILE)) {
            properties.store(output, "News API Key");
            System.out.println("API Key saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving API Key.");
        }
        return apiKey;
    }
}

