package com.api.spotifyapi.controller;

import com.api.spotifyapi.model.SpotifyBuilder;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.model_objects.specification.User;
import com.wrapper.spotify.requests.data.playlists.GetListOfCurrentUsersPlaylistsRequest;
import com.wrapper.spotify.requests.data.users_profile.GetCurrentUsersProfileRequest;
import org.apache.hc.core5.http.ParseException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping(value="/user", method = {RequestMethod.GET,RequestMethod.POST})

public class UserController {

    @GetMapping("/profile/{accessToken}")
    private User getCurrentUserProfile(@PathVariable String accessToken){

        SpotifyApi spotifyApi = new SpotifyBuilder().accessTokenBuilder(accessToken);

        GetCurrentUsersProfileRequest getCurrentUsersProfileRequest = spotifyApi.getCurrentUsersProfile().build();
        try{
            User user = getCurrentUsersProfileRequest.execute();

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

        GetListOfCurrentUsersPlaylistsRequest getListOfCurrentUsersPlaylistsRequest = spotifyApi
                .getListOfCurrentUsersPlaylists()
                .build();

        try {
            Paging<PlaylistSimplified> playlistSimplifiedPaging = getListOfCurrentUsersPlaylistsRequest.execute();

            System.out.println("Total: " + playlistSimplifiedPaging.getTotal());

            for(var num=0; num < playlistSimplifiedPaging.getTotal(); num++){
                System.out.println(playlistSimplifiedPaging.getItems()[num].getName());
                if (playlistSimplifiedPaging.getItems()[num].getName().contains("Daily Mix")) {
                    System.out.println("Encontrado: " + playlistSimplifiedPaging.getItems()[num].getName());
                }

            }

            return playlistSimplifiedPaging;
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }

    }

}
