package com.example.topfivealbums.album;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Album {

    private String wrapperType;
    private String collectionType;
    private Long artistId;
    private Long collectionId;
    private Long amgArtistId;
    private String artistName;
    private String collectionName;
    private String collectionCensoredName;
    private String artistViewUrl;
    private String collectionViewUrl;
    private String artworkUrl60;
    private String artworkUrl100;
    private BigDecimal collectionPrice;
    private String collectionExplicitness;
    private Integer trackCount;
    private String copyright;
    private String country;
    private String currency;
    private String releaseDate;
    private String primaryGenreName;
}
