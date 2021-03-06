package com.example.topfivealbums.album;

import com.example.topfivealbums.artist.Artist;
import com.example.topfivealbums.artist.ArtistRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.*;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumMapper albumMapper;

    @Autowired
    ArtistRepository artistRepository;

    @Override
    @Cacheable("albums")
    public List<Album> getTopFiveAlbums(Long userId) throws IOException, InterruptedException {
        Artist artist = artistRepository.getSavedFavoriteArtist().get(userId);
        if(artist == null){
            return new ArrayList<>();
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://itunes.apple.com/lookup?id=" + artist.getArtistId() + "&entity=album"))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject object = new JSONObject(response.body());
            JSONArray artistJsonArray = (JSONArray) object.get("results");
            List<Album> albums = new ArrayList<>();
            artistJsonArray.forEach(obj -> {
                if(((JSONObject) obj).toMap().get("wrapperType").equals("collection")){
                    AlbumDto albumDto = objectMapper.convertValue(((JSONObject) obj).toMap(), AlbumDto.class);
                    Album album = albumMapper.map(albumDto);
                    albums.add(album);
                }
            });
            if(albums.size() > 5){
                Random random = new Random();
                int numberOfElementsToDelete = albums.size() - 5;
                for(int i = 0; i < numberOfElementsToDelete; i++){
                    int randomIndex = random.nextInt(albums.size());
                    albums.remove(randomIndex);
                }
            }
            return albums;
        }
    }

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();
}
