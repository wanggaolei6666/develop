package com.color.activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DownloadMusicActivity extends ColorThemActivity implements RequestResult {

    private ProgressBar mProgressBar;
    private Button mDownMusic;
    private EditText mEditText;
    private Gson mGson;
    private Hash mJsonResult;
    private String fileHash;
    private String albAlbumID;
    private String musicFormat;
    private String musicName;
    private String musicNameInfo;
    private RelativeLayout mRelativeLayout;
    private List<Hash> musicInfo = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PermissionUtil.applicationPermission(getApplicationContext(), this);
        mProgressBar = (ProgressBar) findViewById(R.id.pro);
        mDownMusic = (Button) findViewById(R.id.download_music);
        mEditText = (EditText) findViewById(R.id.music_name);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.root_background);
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
        return false;
    }

    @Override
    protected String setTitle() {
        return "";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_download_music;
    }

    @Override
    protected Map<String, Integer> getId() {
        return null;
    }

    public void downLoadMusic(View view) {
        getMusicHashAndId();
    }

    private void getMusicHashAndId() {
        String url = String.format(BaseApi.searchMusic, mEditText.getText().toString().trim());
        Log.e("urlUtil", "" + url);
        OkHttpManager.getInstance().requestMehtod(url, this);
    }

    private void downLoadMusic(String hash, String albAlbumID, final String musicName) {
        String url = String.format(BaseApi.musicInfo, hash, albAlbumID);
        OkHttpManager.getInstance().requestMehtod(url, new RequestResult() {
            @Override
            public void requestSuccess(String json) {
                mGson = new Gson();
                final MusicResult musicResult = mGson.fromJson(json, MusicResult.class);
                String url = musicResult.getData().getPlay_url();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(DownloadMusicActivity.this)
                                .load(musicResult.getData().getImg())
                                .asBitmap()
                                .into(mSimpleTarget);
                    }
                });


                download(url, musicName);
            }

            private SimpleTarget<Bitmap> mSimpleTarget = new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> animation) {
                    Drawable drawable = new BitmapDrawable(resource);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        mRelativeLayout.setBackground(drawable);
                    }
                }
            };


            @Override
            public void requestFailed(final String errorMsg) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(DownloadMusicActivity.this, "" + errorMsg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void download(String url, String musicName) {
        DownLoadUtil.getInstance().download(url, "leige", musicName, new OnDownloadListener() {
            @Override
            public void onDownloadFailed(final String message) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(DownloadMusicActivity.this, "download failed\t" + message, Toast.LENGTH_SHORT).show();
                        mDownMusic.setEnabled(true);
                        mDownMusic.setText("DOWNLOAD");
                    }
                });
            }

            @Override
            public void onDownloading(final int progress) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        mProgressBar.setProgress(progress);
                        mDownMusic.setText(progress + "%");
                        mDownMusic.setEnabled(false);
                    }
                });
            }

            @Override
            public void onDownloadSuccess(File file) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mDownMusic.setEnabled(true);
                        mDownMusic.setText("DOWNLOAD");
                        Toast.makeText(DownloadMusicActivity.this, "download success", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public void requestSuccess(String json) {
            JSONArray jsonArray = JsonObjectManager.getJsonData(json);
            for (int i = 0; i <jsonArray.length() ; i++) {
                try {
                    fileHash = jsonArray.getJSONObject(i).getString("FileHash");
                    albAlbumID = jsonArray.getJSONObject(i).getString("AlbumID");
                    musicFormat = jsonArray.getJSONObject(i).getString("ExtName");
                    musicName = jsonArray.getJSONObject(i).getString("FileName");
                    musicInfo.add(new Hash(fileHash, albAlbumID, musicFormat, musicName));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
//            musicNameInfo = musicName.replaceAll("<em>|</em>", "");
            downLoadMusic(fileHash, albAlbumID, musicNameInfo + "." + musicFormat);
    }

    @Override
    public void requestFailed(final String errorMsg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(DownloadMusicActivity.this, "" + errorMsg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
