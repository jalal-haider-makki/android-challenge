package com.taskchallenge.ddevaz.androidstudioproject.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlayList {
    @SerializedName("ListTitle")
    String mPlaylistTitle;
    @SerializedName("ListItems")
    List<VideoModel> mVideos;

    public List<VideoModel> getVideos() {
        return mVideos;
    }

    public void setVideos(List<VideoModel> videos) {
        mVideos = videos;
    }

    public String getPlaylistTitle() {
        return mPlaylistTitle;
    }

    public void setPlaylistTitle(String playlistTitle) {
        mPlaylistTitle = playlistTitle;
    }
}
