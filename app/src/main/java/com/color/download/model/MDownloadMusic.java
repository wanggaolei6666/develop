package com.color.download.model;

import android.util.Log;

import com.color.bean.Hash;
import com.color.bean.MusicResult;
import com.color.bean.OnDownloadListener;
import com.color.bean.RequestResult;
import com.color.download.view.DownloadResultInterface;
import com.color.download.view.IMusicInfo;
import com.color.util.BaseApi;
import com.color.util.DownLoadUtil;
import com.color.util.JsonObjectManager;
import com.color.util.OkHttpManager;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanggaolei on 2018/9/5.
 */

public class MDownloadMusic {
    private String fileHash;
    private String albAlbumID;
    private String musicFormat;
    private String fileName;
    private String musicNameInfo;
    private Gson mGson;
    private String TAG = "MDownloadMusic";
    private List<Hash> musicInfo = new ArrayList<>();
    private String albumName;

    public void reqMusicList(String musicName, final IMusicInfo listener) {
        String url = String.format(BaseApi.searchMusic, musicName.toString().trim());
        OkHttpManager.getInstance().requestMehtod(url, new RequestResult() {

            @Override
            public void requestSuccess(String json) {
                JSONArray jsonArray = JsonObjectManager.getJsonData(json);
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        fileHash = jsonArray.getJSONObject(i).getString("FileHash");
                        albAlbumID = jsonArray.getJSONObject(i).getString("AlbumID");
                        musicFormat = jsonArray.getJSONObject(i).getString("ExtName");
                        fileName = jsonArray.getJSONObject(i).getString("FileName");
                        albumName = jsonArray.getJSONObject(i).getString("AlbumName");

                        musicNameInfo = fileName.replaceAll("<em>|</em>", "");
                        musicInfo.add(new Hash(fileHash,albAlbumID,musicFormat,musicNameInfo,albumName));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                listener.reqSuccess(musicInfo);
//                musicNameInfo = fileName.replaceAll("<em>|</em>", "");
//                downLoadMusic(fileHash, albAlbumID, musicNameInfo + "." + musicFormat, listener);
            }

            @Override
            public void requestFailed(String errorMsg) {
               listener.reqFailed(errorMsg);
            }
        });
    }

    public void downLoadMusic(String hash, String albAlbumID, final String musicName, final DownloadResultInterface listener) {
        String url = String.format(BaseApi.musicInfo, hash, albAlbumID);
        OkHttpManager.getInstance().requestMehtod(url, new RequestResult() {
            @Override
            public void requestSuccess(String json) {
                mGson = new Gson();
                final MusicResult musicResult = mGson.fromJson(json, MusicResult.class);
                String url = musicResult.getData().getPlay_url();

                download(url, musicName, listener);
            }


            @Override
            public void requestFailed(final String errorMsg) {
                Log.e(TAG, "" + errorMsg);
            }
        });
    }

    private void download(String url, String musicName, final DownloadResultInterface listener) {
        DownLoadUtil.getInstance().download(url, "leige", musicName, new OnDownloadListener() {
            @Override
            public void onDownloadFailed(final String message) {
                listener.downloadFailed(message);
            }

            @Override
            public void onDownloading(final int progress) {
                listener.download(progress);
            }

            @Override
            public void onDownloadSuccess(File file) {
                listener.downloadSuccess(file);
            }
        });
    }

}
