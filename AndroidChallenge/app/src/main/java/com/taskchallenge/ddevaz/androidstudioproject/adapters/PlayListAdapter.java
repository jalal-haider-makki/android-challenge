package com.taskchallenge.ddevaz.androidstudioproject.adapters;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.taskchallenge.ddevaz.androidstudioproject.R;
import com.taskchallenge.ddevaz.androidstudioproject.model.VideoModel;

import java.util.List;

public class PlayListAdapter extends BaseAdapter {
    private final Activity mActivity;
    List<VideoModel> mVideos;
    private Picasso picasso;

    public PlayListAdapter(Activity act, List<VideoModel> videos) {
        mActivity = act;
        mVideos = videos;
        picasso = new Picasso.Builder(mActivity.getBaseContext()).build();
    }

    @Override
    public int getCount() {
        return mVideos.size();
    }

    @Override
    public Object getItem(int position) {
        return mVideos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            // Get a new instance of the row layout view
            convertView = LayoutInflater.from(mActivity).inflate(R.layout.row_playlist, null);
            holder = new ViewHolder();
            holder.thumb = (ImageView) convertView.findViewById(R.id.iv_video_thumb);
            holder.title = (TextView) convertView.findViewById(R.id.tv_video_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        VideoModel item = mVideos.get(position);

        picasso.load(item.getVideoThumb()).fit().into(holder.thumb);
        holder.title.setText(item.getVideoTitle());

        return convertView;
    }

    protected static class ViewHolder {
        private ImageView thumb;
        private TextView title;
    }
}
