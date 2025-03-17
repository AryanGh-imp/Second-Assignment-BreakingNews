package AP;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Scanner;

public class Infrastructure {

    private final String URL;
    private final String APIKEY;
    private final String JSONRESULT;
    private ArrayList<News> newsList;


    public Infrastructure(String APIKEY) {
        this.APIKEY = APIKEY;
        this.URL = "https://newsapi.org/v2/everything?q=tesla&from="
                + LocalDate.now().minusDays(1) + "&sortBy=publishedAt&apiKey=";
        this.JSONRESULT = getInformation();
        parseInformation();
    }

    public ArrayList<News> getNewsList() {
        return newsList;
    }

    private String getInformation() {
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL + APIKEY))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return response.body();
            } else {
                throw new IOException("HTTP error code: " + response.statusCode());
            }
        } catch (Exception e) {
            System.out.println("!!Exception : " + e.getMessage());
        }
        return null;
    }

    private void parseInformation() {
        // TODO: Get the first 20 news from the articles array of the json result
        //  and parse the information of each on of them to be mapped to News class
        //  finally add them to newsList in this class to display them in the output
    }

    public void displayNewsList() {
        if (newsList == null || newsList.isEmpty()) {
            System.out.println("❌ THERE IS NO NEWS TO DISPLAY ❌");
            return;
        }

        System.out.println("\n📢 NEWS LIST : ");
        for (int i = 0; i < newsList.size(); i++) {
            System.out.println((i + 1) + ". " + newsList.get(i).getTitle());
        }

        System.out.println("\n🔢 SELECT THE NEWS NUMBER (0 TO EXIT) : ");
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.print("YOUR CHOICE : ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice == 0) {
                    System.out.println("🔚 EXIT THE PROGRAM");
                    break;
                } else if (choice > 0 && choice <= newsList.size()) {
                    newsList.get(choice - 1).displayNews();
                } else {
                    System.out.println("❌ INVALID NUMBER! PLEASE ENTER AGAIN ❌");
                }
            } else {
                System.out.println("❌ INVALID INPUT! ENTER ONLY NUMBERS ❌");
                scanner.next();
            }
        }
    }

}
