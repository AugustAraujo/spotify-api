package com.api.spotifyapi.controller;

import com.api.spotifyapi.model.Auth;
import com.api.spotifyapi.model.SpotifyBuilder;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import org.apache.hc.core5.http.ParseException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;

@RestController
@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping(value="/connection", method = {RequestMethod.GET,RequestMethod.POST})
public class SpotifyController {

    SpotifyApi spotifyApi = new SpotifyBuilder().BuilderSet();

    @GetMapping("/setaSessao")
    public Auth geraSessao() {
        
         AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
                .scope("user-read-email,playlist-modify-public,playlist-modify-private")
                .show_dialog(true)
                .build();

         URI uri = authorizationCodeUriRequest.execute();

        Auth authURI = new Auth(uri.toString());
        
        return authURI;
    }

    @PostMapping("/setaSessao/{code}")
    private SpotifyApi seiLa(@PathVariable String code) {

         AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(code)
                .build();

        try{
            AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();

            spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
            spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());

            System.out.println("Expires in: " + authorizationCodeCredentials.getExpiresIn());

        } catch (IOException | SpotifyWebApiException | ParseException e){
            System.out.println("Error: " + e.getMessage());
        }

        return spotifyApi;

    }

}
