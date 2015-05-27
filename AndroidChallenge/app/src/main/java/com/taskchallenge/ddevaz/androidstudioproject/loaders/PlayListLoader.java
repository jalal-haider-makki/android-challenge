package com.taskchallenge.ddevaz.androidstudioproject.loaders;


import android.content.Context;
import android.util.Log;

import com.taskchallenge.ddevaz.androidstudioproject.model.PlayListResponse;
import com.taskchallenge.ddevaz.androidstudioproject.web.RestClient;

import retrofit.RetrofitError;

public class PlayListLoader extends android.support.v4.content.AsyncTaskLoader<PlayListResponse>{

    private Context mCtx;

    public PlayListLoader(Context context) {
        super(context);
        mCtx = context;
    }

    @Override
    public PlayListResponse loadInBackground() {
        PlayListResponse response = null;
        try{
            response = RestClient.get(mCtx).getPlayList();
        } catch (RetrofitError e) {
            Log.e("PlayListLoader", "Cannot load play list", e);
        }
        return response;
    }
}
