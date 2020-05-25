package com.example.topfivealbums.album;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlbumDto {

    @JsonProperty("wrapperType")
    private String wrapperType;

    @JsonProperty("collectionType")
    private String collectionType;

    @JsonProperty("artistId")
    private Long artistId;

    @JsonProperty("collectionId")
    private Long collectionId;

    @JsonProperty("amgArtistId")
    private Long amgArtistId;

    @JsonProperty("artistName")
    private String artistName;

    @JsonProperty("collectionName")
    private String collectionName;

    @JsonProperty("collectionCensoredName")
    private String collectionCensoredName;

    @JsonProperty("artistViewUrl")
    private String artistViewUrl;

    @JsonProperty("collectionViewUrl")
    private String collectionViewUrl;

    @JsonProperty("artworkUrl60")
    private String artworkUrl60;

    @JsonProperty("artworkUrl100")
    private String artworkUrl100;

    @JsonProperty("collectionPrice")
    private BigDecimal collectionPrice;

    @JsonProperty("collectionExplicitness")
    private String collectionExplicitness;

    @JsonProperty("trackCount")
    private Integer trackCount;

    @JsonProperty("copyright")
    private String copyright;

    @JsonProperty("country")
    private String country;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("releaseDate")
    private String releaseDate;

    @JsonProperty("primaryGenreName")
    private String primaryGenreName;
}
