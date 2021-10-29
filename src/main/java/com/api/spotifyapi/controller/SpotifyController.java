package com.api.spotifyapi.controller;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRefreshRequest;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import org.apache.hc.core5.http.ParseException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@RestController
@RequestMapping(value="/connection")
public class SpotifyController{

    private static final String clientId = "423753d0e3334751a589992bb2daa084";
    private static final String clientSecret = "2f22a02667774619b26a2f49c0f61e8a";
    private static final URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:63342/spotify-api/frontend/deucerto.html/");

    @GetMapping("/conectaTudo")
    private static void conectaTudo() {
       SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .setRedirectUri(redirectUri)
                .build();

        AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri().build();
        final URI uri = authorizationCodeUriRequest.execute();

        System.out.println("URI: " + uri.toString());

    }

    @GetMapping("/codeRefresh/{code}")
    private static void AuthorizationCodeRefresh(@PathVariable String code, @RequestBody String codigo) {
            String clientId = "423753d0e3334751a589992bb2daa084";
            String clientSecret = "2f22a02667774619b26a2f49c0f61e8a";

            SpotifyApi spotifyApi = new SpotifyApi.Builder()
                    .setClientId(clientId)
                    .setClientSecret(clientSecret)
                    .setRefreshToken(code)
                    .build();

            System.out.println(codigo);

            AuthorizationCodeRefreshRequest authorizationCodeRefreshRequest = spotifyApi.authorizationCodeRefresh()
                    .build();

            try {
                final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRefreshRequest.execute();

            // Set access and refresh token for further "spotifyApi" object usage
            spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());

            System.out.println("Expires in: " + authorizationCodeCredentials.getExpiresIn());
            } catch (IOException | SpotifyWebApiException | ParseException e) {
                System.out.println("Error: " + e.getMessage());
            }
    }
}
