package com.taskchallenge.ddevaz.androidstudioproject.ui;

import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.taskchallenge.ddevaz.androidstudioproject.R;
import com.taskchallenge.ddevaz.androidstudioproject.adapters.PlayListAdapter;
import com.taskchallenge.ddevaz.androidstudioproject.loaders.PlayListLoader;
import com.taskchallenge.ddevaz.androidstudioproject.model.PlayListResponse;
import com.taskchallenge.ddevaz.androidstudioproject.model.VideoModel;
import com.taskchallenge.ddevaz.androidstudioproject.model.PlayList;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;

public class MainActivity extends ActionBarActivity
        implements LoaderManager.LoaderCallbacks<PlayListResponse> {

    private static final int LOADER_ID = 1;

    @InjectView(R.id.lv_playlist)
    ListView mLvPlaylist;

    private Loader mLoader;

    private List<VideoModel> mVideos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        setLoader();
    }

    @Override
    public Loader<PlayListResponse> onCreateLoader(int id, Bundle args) {
        Loader<PlayListResponse> loader = null;
        if (id == LOADER_ID) {
            loader = new PlayListLoader(this);
        }
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<PlayListResponse> loader, PlayListResponse data) {
        if (data != null) {
            setPlayList(data);
            loadAdapters();
        }
    }

    private void setPlayList(PlayListResponse data) {
        mVideos = new ArrayList<>();
        for (PlayList playList : data.getPlayList()) {
            for (VideoModel video : playList.getVideos()) {
                mVideos.add(video);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<PlayListResponse> loader) {
        if (mLoader != null) {
            mLoader.reset();
        }
    }

    @OnItemClick(R.id.lv_playlist)
    void onItemClick(int position) {
        if (mVideos != null) {
            VideoModel item = mVideos.get(position);
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(DetailsActivity.EXTRA_VIDEO, item);
            startActivity(intent);
        }
    }

    private void loadAdapters() {
        BaseAdapter adapter = new PlayListAdapter(this, mVideos);
        mLvPlaylist.setAdapter(adapter);
    }

    private void setLoader() {
        LoaderManager manager = getSupportLoaderManager();
        Loader<PlayListResponse> info = manager.getLoader(LOADER_ID);
        if (info == null) {
            mLoader = manager.initLoader(LOADER_ID, null, this);
        } else {
            mLoader = manager.restartLoader(LOADER_ID, null, this);
        }
        mLoader.forceLoad();
    }
}
