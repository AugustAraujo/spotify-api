package com.api.spotifyapi.controller;

import com.api.spotifyapi.model.SpotifyBuilder;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.model_objects.specification.User;
import com.wrapper.spotify.requests.data.playlists.GetListOfCurrentUsersPlaylistsRequest;
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
@Getter
@Setter
@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping(value="/user", method = {RequestMethod.GET,RequestMethod.POST})

public class UserController {

    @GetMapping("/profile/{accessToken}")
    private User getCurrentUserProfile(@PathVariable String accessToken){

        SpotifyApi spotifyApi = new SpotifyBuilder().accessTokenBuilder(accessToken);

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

    @GetMapping("/myplaylists/{accessToken}")
    private Paging<PlaylistSimplified> getCurrentUserPlaylists(@PathVariable String accessToken) {
        SpotifyApi spotifyApi = new SpotifyBuilder().accessTokenBuilder(accessToken);

        final GetListOfCurrentUsersPlaylistsRequest getListOfCurrentUsersPlaylistsRequest = spotifyApi
                .getListOfCurrentUsersPlaylists()
//          .limit(10)
//          .offset(0)
                .build();

        try {
            final Paging<PlaylistSimplified> playlistSimplifiedPaging = getListOfCurrentUsersPlaylistsRequest.execute();

            System.out.println("Total: " + playlistSimplifiedPaging.getTotal());
            return playlistSimplifiedPaging;
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }

    }
}
