package com.maxxsol.androidchallengebyjalal.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Jalal on 5/28/2015.
 */
public class PlayListItem {
    private String _title;
    private String _link;
    private String _thumb;

    public PlayListItem (){
        this._title = "";
        this._link = "";
        this._thumb = "";
    }

    public PlayListItem (String title, String link, String thumb){
        this._title = title;
        this._link = link;
        this._thumb = thumb;
    }

    @JsonProperty(value = "Title")
    public String get_title() {
        return this._title;
    }

    @JsonProperty(value = "Title")
    public void set_title(String _title) {
        this._title = _title;
    }

    @JsonProperty(value = "link")
    public String get_link() {
        return this._link;
    }

    @JsonProperty(value = "link")
    public void set_link(String _link) {
        this._link = _link;
    }

    @JsonProperty(value = "thumb")
    public String get_thumb() {
        return this._thumb;
    }

    @JsonProperty(value = "thumb")
    public void set_thumb(String _thumb) {
        this._thumb = _thumb;
    }

    @Override
    public String toString() {
        return this._title;
    }
}
