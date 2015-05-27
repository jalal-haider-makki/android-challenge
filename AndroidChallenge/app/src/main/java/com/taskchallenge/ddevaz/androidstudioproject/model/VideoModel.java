package com.taskchallenge.ddevaz.androidstudioproject.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VideoModel implements Serializable {
    @SerializedName("Title")
    String videoTitle;
    @SerializedName("link")
    String videoLink;
    @SerializedName("thumb")
    String videoThumb;

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getVideoThumb() {
        return videoThumb;
    }

    public void setVideoThumb(String videoThumb) {
        this.videoThumb = videoThumb;
    }
}
