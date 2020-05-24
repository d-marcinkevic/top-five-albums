package com.example.topfivealbums.artist;

import java.io.IOException;
import java.util.Set;

public interface ArtistService {

    Set<Artist> getArtistsByName(String artistName) throws IOException, InterruptedException;
    Artist saveFavoriteArtist(Long artistId);
    Artist getFavoriteArtist();
}
