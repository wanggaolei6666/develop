package com.color.download.presenter;

import com.color.bean.Hash;
import com.color.download.model.MDownloadMusic;
import com.color.download.view.DownLoadCallBack;
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
    }
    public void getMusicInfo(String musicName){
        mDownloadMusic.reqMusicList(musicName,this);
    }

    @Override
    public void reqSuccess(List<Hash> list) {
        mDownloadCallBack.callSuccess(list);
    }

    @Override
    public void reqFailed(String msg) {
        mDownloadCallBack.callFailed(msg);
    }
}
