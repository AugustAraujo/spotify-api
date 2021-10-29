package com.api.spotifyapi.service;

import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.stereotype.Service;

@Service
public class SpotifyService {
    public SpotifyService spotifyService(){
        return new SpotifyService();
    }
}
