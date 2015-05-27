package com.taskchallenge.ddevaz.androidstudioproject.web;

import com.taskchallenge.ddevaz.androidstudioproject.model.PlayListResponse;

import retrofit.http.GET;

/**
 * Interface for Api endpoints
 */
public interface Api {
    /**
     * List of playlists
     */
    String PLAYLISTS = "/test.json";

    @GET(PLAYLISTS)
    PlayListResponse getPlayList();
}
