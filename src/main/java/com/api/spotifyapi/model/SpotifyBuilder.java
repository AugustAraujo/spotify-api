package com.api.spotifyapi.model;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.net.URI;

@AllArgsConstructor
@Getter
@Setter

public class SpotifyBuilder {
    private SpotifyApi spotifyApi;
    private String clientId;
    private String clientSecret;
    private URI redirectUri;

    public SpotifyBuilder() {
        setClientId("423753d0e3334751a589992bb2daa084");
        setClientSecret("2f22a02667774619b26a2f49c0f61e8a");
        setRedirectUri(SpotifyHttpManager.makeUri("http://localhost:63342/spotify-api/frontend/deucerto.html/"));
    }

    public SpotifyApi BuilderSet() {
        this.spotifyApi = new SpotifyApi.Builder()
                .setClientId(this.clientId)
                .setClientSecret(this.clientSecret)
                .setRedirectUri(this.redirectUri)
                .build();

        return spotifyApi;
    }

    public SpotifyApi accessTokenBuilder(String accessToken) {
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setAccessToken(accessToken)
                .build();

        return spotifyApi;
    }

    public SpotifyApi getBuilderSet(){
        return spotifyApi;
    }

    public void setBuilderSet(SpotifyApi spotifyApi) {
        this.spotifyApi = spotifyApi;
    }

    public SpotifyApi getSpotifyApi() {
        return null;
    }

    public void setAuthURL(SpotifyApi spotifyApi) {
        this.spotifyApi = spotifyApi;
    }

}

