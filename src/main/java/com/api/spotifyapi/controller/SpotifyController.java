package com.api.spotifyapi.controller;

import com.api.spotifyapi.model.Auth;
import com.api.spotifyapi.model.SpotifyBuilder;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.model_objects.specification.User;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import com.wrapper.spotify.requests.data.users_profile.GetCurrentUsersProfileRequest;
import com.wrapper.spotify.requests.data.users_profile.GetUsersProfileRequest;
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

    SpotifyApi spotifyApi = new SpotifyBuilder().BuilderSet();

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
    private SpotifyApi seiLa(@PathVariable String code) {

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

        System.out.println(spotifyApi);
        return spotifyApi;

    }


//    @GetMapping("/user/profile")
//    private User getCurrentUserProfile(){
//        final GetCurrentUsersProfileRequest getCurrentUsersProfileRequest = spotifyApi.getCurrentUsersProfile().build();
//        try{
//            final User user = getCurrentUsersProfileRequest.execute();
//
//            System.out.println("Display name: " + user.getDisplayName());
//            System.out.println("Display name: " + user.getCountry());
//            System.out.println("Display name: " + user.getEmail());
//
//            return user;
//
//        } catch (IOException | SpotifyWebApiException | ParseException e){
//            System.out.println("Error: " + e.getMessage());
//            return null;
//        }
//
//    }

}
