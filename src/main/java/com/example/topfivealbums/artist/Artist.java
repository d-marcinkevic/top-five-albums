package com.example.topfivealbums.artist;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Artist {

    private String wrapperType;
    private String artistType;
    private String artistName;
    private String artistLinkUrl;
    private Long artistId;
    private Long amgArtistId;
    private String primaryGenreName;
    private Long primaryGenreId;
}
