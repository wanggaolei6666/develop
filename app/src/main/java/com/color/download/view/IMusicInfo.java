package com.color.download.view;

import com.color.bean.Hash;

import java.util.List;

/**
 * Created by wanggaolei on 2018/9/5.
 */

public interface IMusicInfo {
        void reqSuccess(List<Hash> list);
        void reqFailed(String msg);
}
