package com.color.download.view;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.color.bean.Hash;
import com.color.bean.MusicResult;
import com.color.bean.OnDownloadListener;
import com.color.bean.RequestResult;
import com.color.download.presenter.DownLoadPresenter;
import com.color.download.presenter.RecyclerviewAdapter;
import com.color.them.R;
import com.color.util.BaseApi;
import com.color.util.ConstantUtil;
import com.color.util.DownLoadUtil;
import com.color.util.JsonObjectManager;
import com.color.util.OkHttpManager;
import com.color.util.PermissionUtil;
import com.example.colorthemmodule.ColorThemActivity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DownloadMusicActivity extends ColorThemActivity implements DownLoadCallBack {

    private ProgressBar mProgressBar;
    private Map<String, Integer> collectId = new HashMap<>();
    private RelativeLayout mRelativeLayout;
    private List<Hash> musicInfo = new ArrayList<>();
    private RecyclerView mRecy;
    private DownLoadPresenter downLoadPresenter;
    private Handler mHandler;


    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PermissionUtil.applicationPermission(getApplicationContext(), this);
        mRecy = (RecyclerView) findViewById(R.id.music_list);
        downLoadPresenter = new DownLoadPresenter(this);
        downLoadPresenter.getMusicInfo("情花");
        mRelativeLayout = (RelativeLayout) findViewById(R.id.root_background);
        mRelativeLayout.setBackgroundColor(Color.BLUE);
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case ConstantUtil.DOWNLOAD_MUSIC:
                        RecyclerviewAdapter recyclerviewAdapter= new RecyclerviewAdapter(DownloadMusicActivity.this,musicInfo);
                        mRecy.setAdapter(recyclerviewAdapter);
                        mRecy.setLayoutManager(new LinearLayoutManager(DownloadMusicActivity.this, LinearLayoutManager.VERTICAL, false));
                        break;

                }
            }
        };



    }

    @Override
    protected int setToobarColor() {
        return Color.TRANSPARENT;
    }

    @Override
    protected int setTitleColor() {
        return Color.TRANSPARENT;
    }

    @Override
    protected boolean isShowTitle() {
        return true;
    }

    @Override
    protected String setTitle() {
        return "音乐列表";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_download_music;
    }

    @Override
    protected Map<String, Integer> getId() {
        collectId.put("toobarid", R.id.toolbar);
        collectId.put("titleid", R.id.tv_title);
        return collectId;
    }


    @Override
    public void callSuccess(List<Hash> list) {

      musicInfo=list;
      mHandler.sendEmptyMessage(ConstantUtil.DOWNLOAD_MUSIC);
    }

    @Override
    public void callFailed(String msg) {
        Toast.makeText(this, "数据加载失败", Toast.LENGTH_SHORT).show();
    }
}
