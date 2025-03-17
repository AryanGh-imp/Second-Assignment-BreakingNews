package AP;

public class News {
    private final String title;
    private final String description;
    private final String source;
    private final String author;
    private final String url;
    private final String publishedAt;

    public News(String title, String description, String sourceName, String author, String url, String publishedAt) {
        this.title = title;
        this.description = description;
        this.source = sourceName;
        this.author = (author == null || author.isEmpty()) ? "UNKNOWN" : author;
        this.url = url;
        this.publishedAt = publishedAt;
    }

    public void displayNews() {
        System.out.println("📢 " + title);
        System.out.println("✍️ AUTHOR : " + author);
        System.out.println("📰  SOURCE : " + source);
        System.out.println("📅 PUBLISHED AT : " + publishedAt);
        System.out.println("🔎 DESCRIPTION : " + description);
        System.out.println("🔗 URL : " + url);
        System.out.println("==========================================");
    }

    public String getTitle() {
        return title;
    }
}
