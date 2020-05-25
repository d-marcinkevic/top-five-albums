package com.example.topfivealbums.artist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ArtistRestControllerIntegrationTest {

    @TestConfiguration
    static class AccountServiceImplTestContextConfiguration {

        @Bean
        public ArtistService artistService() {
            return new ArtistServiceImpl();
        }
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    ArtistRepository artistRepository;

    @Test
    public void givenFoundArtists_whenSaveFavoriteArtist_thenReturnFavoriteArtist(){
        // Given
        Artist artist1 = new Artist();
        artist1.setWrapperType("artist");
        artist1.setArtistType("Artist");
        artist1.setArtistName("Name");
        artist1.setArtistLinkUrl("http://test.test");
        artist1.setArtistId(1111111111L);
        artist1.setPrimaryGenreName("test");
        artist1.setPrimaryGenreId(19L);
        Artist artist2 = new Artist();
        artist2.setWrapperType("artist");
        artist2.setArtistType("Artist");
        artist2.setArtistName("Name1");
        artist2.setArtistLinkUrl("http://test.test1");
        artist2.setArtistId(2222222222L);
        artist2.setPrimaryGenreName("test1");
        artist2.setPrimaryGenreId(10L);
        artistRepository.getFoundArtists().add(artist1);
        artistRepository.getFoundArtists().add(artist2);

        // When
        ResponseEntity<Artist> contactPostResponse = restTemplate.postForEntity("/api/v1/artists/2222222222/user/222", artist2, Artist.class);

        // Then
        assertThat(contactPostResponse.getBody().getArtistName()).isEqualTo("Name1");
    }

    @Test
    public void givenFavoriteArtist_whenGetFavoriteArtist_thenReturnFavoriteArtist(){
        // Given
        Artist artist1 = new Artist();
        artist1.setWrapperType("artist");
        artist1.setArtistType("Artist");
        artist1.setArtistName("Name");
        artist1.setArtistLinkUrl("http://test.test");
        artist1.setArtistId(1111111111L);
        artist1.setPrimaryGenreName("test");
        artist1.setPrimaryGenreId(19L);
        artistRepository.getSavedFavoriteArtist().put(111L, artist1);

        // When
        ResponseEntity<Artist> contactPostResponse = restTemplate.getForEntity("/api/v1/favorites/user/111", Artist.class);

        // Then
        assertThat(contactPostResponse.getBody().getArtistName()).isEqualTo("Name");
    }
}
