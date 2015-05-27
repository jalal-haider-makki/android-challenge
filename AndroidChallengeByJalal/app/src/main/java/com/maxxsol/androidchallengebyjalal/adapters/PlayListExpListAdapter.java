package com.maxxsol.androidchallengebyjalal.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.maxxsol.androidchallengebyjalal.R;
import com.maxxsol.androidchallengebyjalal.entities.PlayList;
import com.maxxsol.androidchallengebyjalal.entities.PlayListItem;
import com.maxxsol.androidchallengebyjalal.framework.VolleySingleton;
import com.maxxsol.androidchallengebyjalal.utilities.Utility;

import java.util.List;

/**
 * Created by Jalal on 5/28/2015.
 */
public class PlayListExpListAdapter extends BaseExpandableListAdapter {
    private List<PlayList> mPlayLists;
    private LayoutInflater mInflater;
    private Context mContext;
    private static final String YOUTUBE_API_DEV_KEY = "AIzaSyBapG9j0SJ_z5-6vNz-Xx5rYyyExFH2W3A";

    public PlayListExpListAdapter(Context context, List<PlayList> playLists){
        this.mContext = context;
        this.mPlayLists = playLists;
        this.mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return mPlayLists.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mPlayLists.get(groupPosition).get_items().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mPlayLists.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mPlayLists.get(groupPosition).get_items().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        TextView tvTitle = null;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.listrow_playlist, null);
        }
        PlayList pl = (PlayList) getGroup(groupPosition);
        tvTitle = (TextView) convertView.findViewById(R.id.tv_playlist_title);

        tvTitle.setText(pl.get_title());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final PlayListItem playListItem = (PlayListItem) getChild(groupPosition, childPosition);

        NetworkImageView playlistThumbView = null;
        TextView tvPlayListItemTitle = null;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.listrow_playlist_item, null);
        }

        playlistThumbView = (NetworkImageView) convertView.findViewById(R.id.iv_playlist_item_thumb);
        playlistThumbView.setImageUrl(playListItem.get_thumb(), VolleySingleton.getInstance(mContext).getImageLoader());
        tvPlayListItemTitle = (TextView) convertView.findViewById(R.id.tv_playlist_item);
        tvPlayListItemTitle.setText(playListItem.get_title());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = YouTubeStandalonePlayer.createVideoIntent((Activity) mContext, YOUTUBE_API_DEV_KEY, Utility.getYoutubeVideoId(playListItem.get_link()));
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
