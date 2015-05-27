package com.maxxsol.androidchallengebyjalal.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jalal on 5/28/2015.
 */
public class PlayList {
    private String _title;
    private List<PlayListItem> _items;

    public PlayList(){
        this._title = "";
        this._items = new ArrayList<PlayListItem>();
    }

    public PlayList(String title){
        this._title = title;
        this._items = new ArrayList<PlayListItem>();
    }

    @JsonProperty(value = "ListTitle")
    public String get_title() {
        return this._title;
    }

    @JsonProperty(value = "ListTitle")
    public void set_title(String _title) {
        this._title = _title;
    }

    @JsonProperty(value = "ListItems")
    public List<PlayListItem> get_items() {
        return this._items;
    }

    @JsonProperty(value = "ListItems")
    public void set_items(List<PlayListItem> _items) {
        this._items = _items;
    }

    @Override
    public String toString() {
        return this._title;
    }
}
