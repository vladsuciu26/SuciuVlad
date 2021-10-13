package tema8OOP;

public class Album {
    private String name;
    private int numberOfPages;
    PaperQuality quality;

    public Album(String name, int numberOfPages, PaperQuality quality) {
        this.name = name;
        this.numberOfPages = numberOfPages;
        this.quality = quality;
    }

    public String getName() {
        return name;
    }
}
