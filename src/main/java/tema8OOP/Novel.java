package tema8OOP;

public class Novel {
    private String name;
    private int numberOfPages;
    private String novelGenre;

    public Novel(String name, int numberOfPages, String novelGenre) {
        this.name = name;
        this.numberOfPages = numberOfPages;
        this.novelGenre = novelGenre;
    }

    public String getName() {
        return name;
    }
}
