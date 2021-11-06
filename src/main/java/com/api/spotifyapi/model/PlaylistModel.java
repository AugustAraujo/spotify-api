package com.api.spotifyapi.model;

public class PlaylistModel {

    private String id;
    private String name;
    private String[] uris;
    private String user_id;

    public PlaylistModel(String id, String name, String[] uris, String user_id) {
        this.id = id;
        this.name = name;
        this.uris = uris;
        this.user_id = user_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String url) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getUris() {
        return uris;
    }

    public void setUris(String[] uris) {
        this.uris = uris;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
