package com.example.topfivealbums.album;

import java.io.IOException;
import java.util.List;

public interface AlbumService {

    List<Album> getTopFiveAlbums(Long userId) throws IOException, InterruptedException;
}
