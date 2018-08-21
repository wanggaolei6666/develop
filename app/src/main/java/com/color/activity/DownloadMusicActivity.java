package com.color.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.color.bean.Hash;
import com.color.bean.OnDownloadListener;
import com.color.bean.RequestResult;
import com.color.them.R;
import com.color.util.BaseApi;
import com.color.util.ConstantUtil;
import com.color.util.DownLoadUtil;
import com.color.util.OkHttpManager;
import com.google.gson.Gson;

import java.io.File;

public class DownloadMusicActivity extends AppCompatActivity implements RequestResult {

    private ProgressBar mProgressBar;
    private Button mDownMusic;
    private EditText mEditText;
    private Gson mGson;
    private Hash mJsonResult;
    private String fileHash;
    private String albAlbumID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_music);
        mProgressBar = (ProgressBar) findViewById(R.id.pro);
        mDownMusic = (Button) findViewById(R.id.download_music);
        mEditText = (EditText) findViewById(R.id.music_name);
    }

    public void downLoadMusic(View view) {
        getMusicHashAndId();


    }

    private void getMusicHashAndId(){
        String url=String.format(BaseApi.searchMusic,mEditText.getText().toString().trim());
        Log.e("urlUtil", ""+url );
        OkHttpManager.getInstance().requestMehtod(url,this);
    }

    @Override
    public void requestSuccess(String json) {
        Log.e("jsonResult", ""+json );
        //        mGson = new Gson();
//        mJsonResult = mGson.fromJson(json, Hash.class);
//        fileHash=mJsonResult.getData().getLists().get(0).getFileHash();
//        albAlbumID=mJsonResult.getData().getLists().get(0).getAlbumID();
//        final String url=String.format(BaseApi.musicInfo,fileHash,albAlbumID);
//        Log.e("myUrl", ""+url );
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                mDownMusic.setText(url);
//            }
//        });
    }

    @Override
    public void requestFailed(final String errorMsg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(DownloadMusicActivity.this, ""+errorMsg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
