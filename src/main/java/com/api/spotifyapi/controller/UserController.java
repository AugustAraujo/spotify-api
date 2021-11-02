package com.api.spotifyapi.controller;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.User;
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
@RequestMapping(value="/user", method = {RequestMethod.GET,RequestMethod.POST})

public class UserController {

    @GetMapping("/profile")
    private User getCurrentUserProfile(){
        final GetCurrentUsersProfileRequest getCurrentUsersProfileRequest = spotifyApi.getCurrentUsersProfile().build();
        try{
            final User user = getCurrentUsersProfileRequest.execute();

            System.out.println("Display name: " + user.getDisplayName());
            System.out.println("Display name: " + user.getCountry());
            System.out.println("Display name: " + user.getEmail());

            return user;

        } catch (IOException | SpotifyWebApiException | ParseException e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }

    }
}
