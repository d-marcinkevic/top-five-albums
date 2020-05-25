package com.example.topfivealbums.album;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.List;

@Api(tags = "Album")
@RestController
public class AlbumRestController {

    @Autowired
    AlbumService albumService;

    @ApiOperation(value = "Returns users favorite artist top 5 albums")
    @GetMapping("/api/v1/albums/user/{user}")
    public List<Album> getTopFiveAlbums(@PathVariable Long user) throws IOException, InterruptedException {
        return albumService.getTopFiveAlbums(user);
    }
}
