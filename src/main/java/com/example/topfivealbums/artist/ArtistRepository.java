package com.example.topfivealbums.artist;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Data
@Component
public class ArtistRepository {

    public Set<Artist> foundArtists = new HashSet<>();
    public Artist savedFavoriteArtist = new Artist();
}
