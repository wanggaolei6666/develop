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
import com.color.download.presenter.RecyclerviewAdapter.OnItemClickListener;
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

public class DownloadMusicActivity extends ColorThemActivity implements DownLoadCallBack,OnItemClickListener, DownloadResultInterface {

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
        downLoadPresenter.getMusicInfo("霍元甲");
        mRelativeLayout = (RelativeLayout) findViewById(R.id.root_background);
        mRelativeLayout.setBackgroundColor(Color.BLUE);
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case ConstantUtil.DOWNLOAD_MUSIC:
                        RecyclerviewAdapter recyclerviewAdapter= new RecyclerviewAdapter(DownloadMusicActivity.this,musicInfo,DownloadMusicActivity.this);
                        mRecy.setAdapter(recyclerviewAdapter);
                        mRecy.setLayoutManager(new LinearLayoutManager(DownloadMusicActivity.this, LinearLayoutManager.VERTICAL, false));
                        break;
                    case 0:
                        mProgressBar.setVisibility(View.VISIBLE);
                        Bundle data = msg.getData();
                        int progress=data.getInt("Progress");
                        mProgressBar.setProgress(progress);
                        break;

                    case 1:
                        mProgressBar.setVisibility(View.GONE);

                        Bundle downSuccess = msg.getData();
                        String downloadSuccess=downSuccess.getString("DownloadSuccess");
                        Toast.makeText(DownloadMusicActivity.this, ""+downloadSuccess, Toast.LENGTH_SHORT).show();
                        break;

                    case 2:
                        mProgressBar.setVisibility(View.GONE);
                        Bundle downFailed = msg.getData();
                        String downloadFailed=downFailed.getString("ErrorMsg");
                        Toast.makeText(DownloadMusicActivity.this, ""+downloadFailed, Toast.LENGTH_SHORT).show();
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
        mHandler.sendEmptyMessage(ConstantUtil.DOWNLOAD_MUSIC_FAILED);

    }

    @Override
    public void onItemClick(View view) {
        int position=mRecy.getChildAdapterPosition(view);
        mProgressBar=view.findViewById(R.id.progress_bar);
        downLoadPresenter.downloadMusic(musicInfo.get(position).getFileHash(),musicInfo.get(position).getAlbumID(),musicInfo.get(position).getFileName()+"."+musicInfo.get(position).getExtName(),DownloadMusicActivity.this);
    }

    @Override
    public void downloadFailed(String msg) {
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("ErrorMsg",msg);
        message.setData(bundle);
        message.arg1=2;
        mHandler.sendMessage(message);
    }

    @Override
    public void download(int progress) {
        Log.e("download", ""+progress );
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putInt("Progress",progress);
        message.setData(bundle);
        message.arg1=0;
        mHandler.sendMessage(message);


    }

    @Override
    public void downloadSuccess(File file) {
        Log.e("download", "下载完成");
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("DownloadSuccess","下载完成");
        message.setData(bundle);
        message.arg1=1;
        mHandler.sendMessage(message);

    }
}
