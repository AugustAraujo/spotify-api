package com.api.spotifyapi.controller;

import com.api.spotifyapi.model.PlaylistModel;
import com.api.spotifyapi.model.SpotifyBuilder;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.special.SnapshotResult;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.requests.data.playlists.AddItemsToPlaylistRequest;
import com.wrapper.spotify.requests.data.playlists.CreatePlaylistRequest;
import org.apache.hc.core5.http.ParseException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping(value="/playlist", method = {RequestMethod.GET,RequestMethod.POST})
public class PlaylistController {

    @PostMapping("/criaPlaylist/{accessToken}")
    public Playlist criaPlaylist(@RequestBody PlaylistModel playlistData, @PathVariable String accessToken){

        String name = playlistData.getName();
        String[] uris = playlistData.getUris();
        String user_id = playlistData.getUser_id();

        SpotifyApi spotifyApi = new SpotifyBuilder().accessTokenBuilder(accessToken);

        CreatePlaylistRequest createPlaylistRequest = spotifyApi.createPlaylist(user_id, name).build();

        try {

            Playlist playlist = createPlaylistRequest.execute();

            populaPlaylist(playlist.getId(), uris, spotifyApi);

            return playlist;

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return null;
    }

    public void populaPlaylist(String id, String[] uris, SpotifyApi spotifyApi) {

        AddItemsToPlaylistRequest addItemsToPlaylistRequest = spotifyApi
                .addItemsToPlaylist(id, uris)
                .build();

        try {
            SnapshotResult snapshotResult = addItemsToPlaylistRequest.execute();

            System.out.println("Snapshot ID: " + snapshotResult.getSnapshotId());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

}
