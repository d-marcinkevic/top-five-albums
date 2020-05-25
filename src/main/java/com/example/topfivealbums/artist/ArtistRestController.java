package com.example.topfivealbums.artist;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.Set;

@Api(tags = "Artist")
@RestController
public class ArtistRestController {

    @Autowired
    ArtistService artistService;

    @ApiOperation(value = "Search artist by name")
    @GetMapping("/api/v1/artists/{name}")
    public Set<Artist> getArtistByName(@PathVariable String name) throws IOException, InterruptedException {
        return artistService.getArtistsByName(name);
    }

    @ApiOperation(value = "Saves users favorite artist")
    @PostMapping("/api/v1/artists/{artistId}/user/{userId}")
    public Artist saveFavoriteArtist(@PathVariable Long artistId, @PathVariable Long userId){
        return artistService.saveFavoriteArtist(artistId, userId);
    }

    @ApiOperation(value = "Returns users favorite artist")
    @GetMapping("/api/v1/favorites/user/{userId}")
    public Artist getFavoriteArtist(@PathVariable Long userId){
        return artistService.getFavoriteArtist(userId);
    }
}
