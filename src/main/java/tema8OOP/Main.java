package tema8OOP;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Novel n1 = new Novel("Anna Karenina", 230, "Science fiction");
        Novel n2 = new Novel("To Kill a Mockingbird", 200, "Mystery");
        Novel n3 = new Novel("One Hundred Years of Solitude", 180, "Romance");
        Novel n4 = new Novel("A Passage to India", 250, "Historical Fiction");

        Album a1 = new Album("Animals", 100, PaperQuality.LOW);
        Album a2 = new Album("Heroes", 70, PaperQuality.NORMAL);
        Album a3 = new Album("What's Going On", 80, PaperQuality.PREMIUM);

        Library library1 = new Library("Library1", n1, a1);
        library1.addNovel(n2);
        library1.addNovel(n3);

        Library library2 = new Library("Library2", n2, a3);
        library2.addNovel(n1);
        library2.addArtAlbum(a3);
        library2.addArtAlbum(a1);

        Library library3 = new Library("Library3");
        library3.addNovel(n4);
        library3.addArtAlbum(a2);

        List<Library> libraries = Arrays.asList(library1, library2, library3);

        Map<String, List<Novel>> libraryListWithNovels = new HashMap<>();
//        Show the novels
        for(Library bookToSearch : libraries) {
            libraryListWithNovels.put(bookToSearch.getName(), bookToSearch.getNovels());
        }

        Map<String, List<Album>> libraryListWithArtAlbums = new HashMap<>();
//        Show the art albums
        for(Library bookToSearch : libraries) {
            libraryListWithArtAlbums.put(bookToSearch.getName(), bookToSearch.getArtAlbums());
        }


        library3.deleteNovel(n4);
        library2.deleteArtAlbum(a3);
        List<Library> libraries1 = Arrays.asList(library1, library2, library3);
        Map<String, List<Novel>> libraryList1 = new HashMap<>();
        for(Library bookToSearch : libraries) {
            libraryList1.put(bookToSearch.getName(), bookToSearch.getNovels());
        }
    }
}
