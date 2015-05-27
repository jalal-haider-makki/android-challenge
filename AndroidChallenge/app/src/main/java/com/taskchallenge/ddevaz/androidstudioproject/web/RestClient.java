package com.taskchallenge.ddevaz.androidstudioproject.web;

import android.content.Context;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
* Handles REST API calls
*/
public final class RestClient {
    /**
     * Api base url
     */
    public static final String ROOT = "http://cscompanion.com/api";

    /**
     * Prevent additional instances creation
     */
    private RestClient() {}

    private static Api sClient;

    public static Api get(Context ctx) {
        return setupRestClient(ctx);
    }

    /**
     * Build OkHttpClient
     */
    private static Api setupRestClient(final Context ctx) {
        if (sClient == null) {
            RestAdapter.Builder builder = new RestAdapter.Builder()
                    .setEndpoint(ROOT)
                    .setClient(new OkClient(new OkHttpClient()))
                    .setLogLevel(RestAdapter.LogLevel.HEADERS_AND_ARGS);

            RestAdapter restAdapter = builder.build();
            sClient = restAdapter.create(Api.class);
        }
        return sClient;
    }
}
