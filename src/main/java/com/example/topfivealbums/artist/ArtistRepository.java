package com.example.topfivealbums.artist;

import lombok.Data;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
@Component
public class ArtistRepository {

    public Set<Artist> foundArtists = new HashSet<>();
    public Map<Long, Artist> savedFavoriteArtist = new HashMap();
}
