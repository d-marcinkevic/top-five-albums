package com.example.topfivealbums.artist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ArtistServiceImplTest {

    @TestConfiguration
    static class AccountServiceImplTestContextConfiguration {

        @Bean
        public ArtistService artistService() {
            return new ArtistServiceImpl();
        }
    }

    @Autowired
    private ArtistService artistService;

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
        Artist savedFavoriteArtist = artistService.saveFavoriteArtist(2222222222L, 111L);

        // Then
        assertThat(savedFavoriteArtist.getArtistName()).isEqualTo("Name1");
        assertThat(artistRepository.getSavedFavoriteArtist().get(111L)).isEqualTo(artist2);
    }

    @Test
    public void givenFavoriteArtist_whenGetArtistByUserIs_thenReturnFavoriteArtist() {
        // Given
        Artist artist = new Artist();
        artist.setWrapperType("artist");
        artist.setArtistType("Artist");
        artist.setArtistName("Name");
        artist.setArtistLinkUrl("http://test.test");
        artist.setArtistId(1111111111L);
        artist.setPrimaryGenreName("test");
        artist.setPrimaryGenreId(19L);
        artistRepository.getSavedFavoriteArtist().put(111L, artist);

        // When
        Artist art = artistService.getFavoriteArtist(111L);

        // Then
        assertThat(art.getArtistName()).isEqualTo("Name");
    }
}
