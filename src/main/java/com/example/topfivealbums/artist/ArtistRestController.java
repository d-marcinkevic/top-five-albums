package com.example.topfivealbums.artist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.Set;


@RestController
public class ArtistRestController {

    @Autowired
    ArtistService artistService;

    @GetMapping("/api/v1/artists/{name}")
    public Set<Artist> getArtistByName(@PathVariable String name) throws IOException, InterruptedException {
        return artistService.getArtistsByName(name);
    }

    @PostMapping("/api/v1/artists/{artistId}")
    public Artist saveFavoriteArtist(@PathVariable Long artistId){
        return artistService.saveFavoriteArtist(artistId);
    }

    @GetMapping("/api/v1/favorites/")
    public Artist getFavoriteArtist(){
        return artistService.getFavoriteArtist();
    }
}
