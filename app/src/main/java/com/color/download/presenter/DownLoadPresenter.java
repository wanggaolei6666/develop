package com.color.download.presenter;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.color.bean.Hash;
import com.color.download.model.MDownloadMusic;
import com.color.download.view.DownLoadCallBack;
import com.color.download.view.DownloadResultInterface;
import com.color.download.view.IMusicInfo;

import java.util.List;


/**
 * Created by wanggaolei on 2018/9/5.
 */

public class DownLoadPresenter implements IMusicInfo {
    private DownLoadCallBack mDownloadCallBack;
    private MDownloadMusic mDownloadMusic;
    public DownLoadPresenter(DownLoadCallBack mDownloadCallBack) {
        this.mDownloadCallBack=mDownloadCallBack;
        mDownloadMusic = new MDownloadMusic();
        @SuppressLint("HandlerLeak") Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };
    }
    public void downloadMusic(String hash, String albAlbumID, final String musicName, final DownloadResultInterface listener){
        mDownloadMusic.downLoadMusic(hash,albAlbumID,musicName,listener);
    }


    public void getMusicInfo(String musicName){
        Log.e("ThreadIsMainThread", "getMusicInfo:"+Thread.currentThread().getId() );
        mDownloadMusic.reqMusicList(musicName,this);
    }

    @Override
    public void reqSuccess(List<Hash> list) {
        Log.e("ThreadIsMainThread", ""+Thread.currentThread().getId() );
        mDownloadCallBack.callSuccess(list);
    }

    @Override
    public void reqFailed(String msg) {
        Log.e("ThreadIsMainThread", "reqFailed"+Thread.currentThread().getId() );
        mDownloadCallBack.callFailed(msg);
    }
}
