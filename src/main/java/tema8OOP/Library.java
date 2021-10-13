package tema8OOP;

import java.util.*;

public class Library {
     List<Novel> novels;
     List<Album> artAlbums;

    private String libraryName;

    public Library(String libraryName, Novel novel, Album artAlbum) {
        this.libraryName = libraryName;
        novels = new ArrayList<>();
        novels.addAll(Arrays.asList(novel));

        artAlbums = new ArrayList<>();
        artAlbums.addAll(Arrays.asList(artAlbum));
    }

    public Library(String libraryName) {
        this.libraryName = libraryName;
        novels = new ArrayList<>();
        novels.addAll(Arrays.asList());

        artAlbums = new ArrayList<>();
        artAlbums.addAll(Arrays.asList());
    }

    public void addNovel(Novel novelToAdd) {
        novels.add(novelToAdd);
    }

    public void addArtAlbum(Album artAlbumToAdd) {
        artAlbums.add(artAlbumToAdd);
    }

    public void deleteNovel(Novel novelToDelete) {
        novels.remove(novelToDelete);
    }

    public void deleteArtAlbum(Album artAlbumToDelete) {
        artAlbums.remove(artAlbumToDelete);
    }

    public List<Novel> getNovels() {
        return Collections.unmodifiableList(novels);
    }

    public List<Album> getArtAlbums() {
        return Collections.unmodifiableList(artAlbums);
    }

    public String getName() {
        return libraryName;
    }
}
