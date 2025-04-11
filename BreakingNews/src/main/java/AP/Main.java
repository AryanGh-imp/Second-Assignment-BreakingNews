package AP;

public class Main {
    public static void main(String[] args) {
        try {
            // Get API Key from Config
            String apiKey = Config.getApiKey();
            System.out.println("Using API Key: " + apiKey);

            Infrastructure inf = new Infrastructure(apiKey);
            inf.displayNewsList();

        }
        catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }
}