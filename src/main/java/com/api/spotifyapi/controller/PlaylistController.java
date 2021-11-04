package com.api.spotifyapi.controller;

import com.api.spotifyapi.model.SpotifyBuilder;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.User;
import com.wrapper.spotify.requests.data.playlists.CreatePlaylistRequest;
import com.wrapper.spotify.requests.data.users_profile.GetCurrentUsersProfileRequest;
import org.apache.hc.core5.http.ParseException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.Parameter;

@RestController
@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping(value="/playlist", method = {RequestMethod.GET,RequestMethod.POST})

public class PlaylistController {

    @PostMapping("/criaPlaylist/{accessToken}")
    public void criaPlaylist(@RequestBody String armazena, @PathVariable String accessToken){

        String[] teste = armazena.split(":");
        String[] teste2 = teste[1].split(",");

        SpotifyApi spotifyApi = new SpotifyBuilder().accessTokenBuilder(accessToken);

        final GetCurrentUsersProfileRequest getCurrentUsersProfileRequest = spotifyApi.getCurrentUsersProfile().build();

        System.out.println(teste2[0] + teste[2]);

        try{
            final User user = getCurrentUsersProfileRequest.execute();

            System.out.println("ID" + user.getId());
        } catch (IOException | SpotifyWebApiException | ParseException e){
            System.out.println("erro: " + e.getMessage());
        }



        //final CreatePlaylistRequest createPlaylistRequest = spotifyApi.createPlaylist(ID, name);
    }

}
