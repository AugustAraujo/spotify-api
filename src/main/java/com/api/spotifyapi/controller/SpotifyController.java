package com.api.spotifyapi.controller;

import com.api.spotifyapi.model.Auth;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.model_objects.specification.User;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import com.wrapper.spotify.requests.data.users_profile.GetCurrentUsersProfileRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.hc.core5.http.ParseException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;

@RestController
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping(value="/connection", method = {RequestMethod.GET,RequestMethod.POST})
public class SpotifyController {

    public String clientId = "423753d0e3334751a589992bb2daa084";
    public String clientSecret = "2f22a02667774619b26a2f49c0f61e8a";
    public URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:63342/spotify-api/frontend/deucerto.html/");

    SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(this.clientId)
            .setClientSecret(this.clientSecret)
            .setRedirectUri(this.redirectUri)
            .build();

    @GetMapping("/setaSessao")
    public Auth geraSessao() {

        final AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
                .scope("user-read-email")
                .build();

        final URI uri = authorizationCodeUriRequest.execute();

        System.out.println("GET SETA " + uri.toString());
        Auth auth = new Auth(uri.toString());

        return auth;
    }

    @PostMapping("/setaSessao/{code}")
    private void seiLa(@PathVariable String code) {
        final AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(code)
                .build();

        try{
            final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();

            spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
            spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());

            System.out.println("Expires in: " + authorizationCodeCredentials.getExpiresIn());

        } catch (IOException | SpotifyWebApiException | ParseException e){
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("macaco rodando");

    }

    @GetMapping("/user/profile")
    private void getCurrentUserProfile(){
        final GetCurrentUsersProfileRequest getCurrentUsersProfileRequest = spotifyApi.getCurrentUsersProfile().build();
        try{
            final User user = getCurrentUsersProfileRequest.execute();

            System.out.println("Display name: " + user.getDisplayName());
            System.out.println("Display name: " + user.getCountry());
            System.out.println("Display name: " + user.getEmail());

        } catch (IOException | SpotifyWebApiException | ParseException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

}

    /*
    @GetMapping("/conectaTudo")
    private static void conectaTudo(SpotifyApi spotifyApi) {

        SpotifyApi newBuild = spotifyApi;

        AuthorizationCodeUriRequest authorizationCodeUriRequest =  newBuild.authorizationCodeUri().build();
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

            System.out.println(code);


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

    @GetMapping("/currentUser/{code}")
    private static void showCurrentUser(@PathVariable String code) {

        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setAccessToken(code)
                .build();

        System.out.println(code);

        GetCurrentUsersProfileRequest getCurrentUserProfileRequest = spotifyApi.getCurrentUsersProfile()
                .build();

        try {
            final User user = getCurrentUserProfileRequest.execute();

            System.out.println("Display name: " + user.getDisplayName());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
*/