package com.example.topfivealbums.artist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArtistDto {

    @JsonProperty("wrapperType")
    private String wrapperType;

    @JsonProperty("artistType")
    private String artistType;

    @JsonProperty("artistName")
    private String artistName;

    @JsonProperty("artistLinkUrl")
    private String artistLinkUrl;

    @JsonProperty("artistId")
    private Long artistId;

    @JsonProperty("amgArtistId")
    private Long amgArtistId;

    @JsonProperty("primaryGenreName")
    private String primaryGenreName;

    @JsonProperty("primaryGenreId")
    private Long primaryGenreId;
}
