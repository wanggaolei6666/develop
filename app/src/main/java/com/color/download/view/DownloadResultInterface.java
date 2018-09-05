package com.color.download.view;

import java.io.File;

/**
 * Created by wanggaolei on 2018/9/5.
 */

public interface DownloadResultInterface {
    void downloadFailed(String msg);
    void download(int progress);
    void downloadSuccess(File file);
}
