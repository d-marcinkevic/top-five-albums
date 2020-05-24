package com.example.topfivealbums.collection;

import java.io.IOException;
import java.util.List;

public interface AlbumService {

    List<Album> getTopFiveAlbums() throws IOException, InterruptedException;
}
