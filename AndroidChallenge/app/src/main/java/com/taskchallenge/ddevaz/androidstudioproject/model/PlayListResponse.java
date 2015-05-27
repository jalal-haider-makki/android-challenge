package com.taskchallenge.ddevaz.androidstudioproject.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlayListResponse {
    @SerializedName("Playlists")
    List<PlayList> mPlayList;

    public List<PlayList> getPlayList() {
        return mPlayList;
    }

    public void setPlaylists(List<PlayList> playList) {
        mPlayList = playList;
    }

}
