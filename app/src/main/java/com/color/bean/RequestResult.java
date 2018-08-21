package com.color.bean;

import okhttp3.Response;

/**
 * Created by wanggaolei on 2018/8/21.
 */

public interface RequestResult {
    void requestSuccess(String json);
    void requestFailed(String errorMsg);
}
