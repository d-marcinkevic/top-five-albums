package com.example.topfivealbums.artist;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Set;

@Service
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private ArtistMapper artistMapper;

    @Autowired
    ArtistRepository artistRepository;

    @Override
    public Set<Artist> getArtistsByName(String artistName) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://itunes.apple.com/search?entity=allArtist&term=" + artistName.trim().replace(" ", "+")))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject object = new JSONObject(response.body());
        JSONArray artistJsonArray = (JSONArray) object.get("results");
        if(!artistRepository.getFoundArtists().isEmpty()){
            artistRepository.getFoundArtists().clear();
        }
        artistJsonArray.forEach(obj -> {
            ArtistDto artistDto = objectMapper.convertValue(((JSONObject) obj).toMap(), ArtistDto.class);
            Artist artist = artistMapper.map(artistDto);
            artistRepository.getFoundArtists().add(artist);
        });
        return artistRepository.getFoundArtists();
    }

    @Override
    @CacheEvict(value = "albums", key = "#userId")
    public Artist saveFavoriteArtist(Long artistId, Long userId) {
        Artist artist = artistRepository.getFoundArtists().stream()
                .filter(e -> artistId.equals(e.getArtistId()))
                .findAny()
                .orElse(null);
        artistRepository.getSavedFavoriteArtist().put(userId, artist);
        return artist;
    }

    @Override
    public Artist getFavoriteArtist(Long userId) {
        Artist artist = artistRepository.getSavedFavoriteArtist().get(userId);
        return artist;
    }

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();
}
