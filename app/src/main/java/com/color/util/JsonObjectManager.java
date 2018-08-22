package com.color.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2018/8/22.
 */

public class JsonObjectManager {
    public static JSONArray getJsonData(String jsonStr) {
        if (null == jsonStr || "".equals(jsonStr)) {
            return null;
        }
        try {
            JSONObject json= new JSONObject(jsonStr);
            JSONObject jsonData= json.getJSONObject("data");
            return jsonData.getJSONArray("lists");
        } catch (JSONException e) {
            Log.e("jsonDataError", ""+e.getMessage());
        }
        return null;
    }
}
