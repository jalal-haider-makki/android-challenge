package com.maxxsol.androidchallengebyjalal.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxxsol.androidchallengebyjalal.R;
import com.maxxsol.androidchallengebyjalal.adapters.PlayListExpListAdapter;
import com.maxxsol.androidchallengebyjalal.entities.PlayList;
import com.maxxsol.androidchallengebyjalal.framework.VolleySingleton;
import com.maxxsol.androidchallengebyjalal.utilities.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    private ExpandableListView mExpListView;
    private ProgressDialog mProgressDialog;
    private TextView mTvNoticePlayList;

    private List<PlayList> mPlayLists;
    private ObjectMapper mObjectMapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressDialog = new ProgressDialog(this);
        mObjectMapper = new ObjectMapper();
        mExpListView = (ExpandableListView) this.findViewById(R.id.eLv_playlists);
        mTvNoticePlayList = (TextView) this.findViewById(R.id.tv_notice_emptyPlayList);

        fetchPlayLists();
    }

    /**
     * Loads Playlists from API
     */
    private void fetchPlayLists(){
        mProgressDialog.setMessage("Loading Playlists...");
        if(!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }

        JsonObjectRequest jsonRequest_palylist = new JsonObjectRequest(Request.Method.GET, Utility.API_BASE_URL+"test.json", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if(mProgressDialog.isShowing()){
                    mProgressDialog.dismiss();
                }

                try {
                    JSONArray playlists = response.getJSONArray("Playlists");
                    mPlayLists = Arrays.asList(mObjectMapper.readValue(playlists.toString(), PlayList[].class));
                    PlayListExpListAdapter adapter = new PlayListExpListAdapter(MainActivity.this, mPlayLists);

                    if(mPlayLists.size() != 0){
                        mTvNoticePlayList.setVisibility(View.GONE);
                        mExpListView.setVisibility(View.VISIBLE);
                    } else {
                        mExpListView.setVisibility(View.GONE);
                        mTvNoticePlayList.setVisibility(View.VISIBLE);
                    }

                    mExpListView.setAdapter(adapter);
                } catch (IOException e) {
                    Toast.makeText(MainActivity.this, "Problem loading playlists from server.", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this, "Problem loading playlists from server.", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(mProgressDialog.isShowing()){
                    mProgressDialog.dismiss();
                }

                Toast.makeText(MainActivity.this, "Problem loading playlists from server.", Toast.LENGTH_SHORT).show();
                Log.e(TAG, error.getLocalizedMessage());
            }
        });

        VolleySingleton.getInstance(this).addToRequestQueue(jsonRequest_palylist);
    }

}
