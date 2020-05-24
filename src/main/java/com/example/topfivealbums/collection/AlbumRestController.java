package com.example.topfivealbums.collection;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class AlbumRestController {

    @Autowired
    AlbumService albumService;

    @GetMapping("/api/v1/albums/")
    public List<Album> getTopFiveAlbums() throws IOException, InterruptedException {
        return albumService.getTopFiveAlbums();
    }
}
