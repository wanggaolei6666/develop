package com.color.bean;

import java.io.File;

/**
 * Created by wanggaolei on 2018/8/20.
 */

public interface OnDownloadListener {
    void onDownloadFailed(String message);
    void onDownloading(int progress);
    void onDownloadSuccess(File file);

}
