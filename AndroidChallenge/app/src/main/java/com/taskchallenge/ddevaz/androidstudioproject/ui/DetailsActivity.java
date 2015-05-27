package com.taskchallenge.ddevaz.androidstudioproject.ui;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.taskchallenge.ddevaz.androidstudioproject.R;
import com.taskchallenge.ddevaz.androidstudioproject.model.VideoModel;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DetailsActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    public static final String EXTRA_VIDEO = "video_extra";
    public static final String API_KEY = "AIzaSyDd4Voopcw3nYcJLyVtUi0jkBFJcmqM84g";

    //http://youtu.be/<VIDEO_ID>
    public static String mVideoId;

    @InjectView(R.id.youtube_player_view)
    YouTubePlayerView mYoutubePlayer;

    @InjectView(R.id.tv_video_title)
    TextView mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.inject(this);
        getExtras();
        mYoutubePlayer.initialize(API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        /** add listeners to YouTubePlayer instance **/
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        youTubePlayer.setPlaybackEventListener(playbackEventListener);

        /** Start buffering **/
        if (!wasRestored) {
            youTubePlayer.cueVideo(mVideoId);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Initialization failure!", Toast.LENGTH_LONG).show();
    }

    private void getExtras() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            VideoModel video = (VideoModel) bundle.getSerializable(EXTRA_VIDEO);
            String youtubeUrl = video.getVideoLink();
            mVideoId = youtubeUrl.substring(youtubeUrl.lastIndexOf("=") + 1);
            mTvTitle.setText(video.getVideoTitle());
        }
    }

    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {

        @Override
        public void onBuffering(boolean arg0) {
        }

        @Override
        public void onPaused() {
        }

        @Override
        public void onPlaying() {
        }

        @Override
        public void onSeekTo(int arg0) {
        }

        @Override
        public void onStopped() {
        }

    };

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new
            YouTubePlayer.PlayerStateChangeListener() {

        @Override
        public void onAdStarted() {
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason arg0) {
        }

        @Override
        public void onLoaded(String arg0) {
        }

        @Override
        public void onLoading() {
        }

        @Override
        public void onVideoEnded() {
        }

        @Override
        public void onVideoStarted() {
        }
    };
}
