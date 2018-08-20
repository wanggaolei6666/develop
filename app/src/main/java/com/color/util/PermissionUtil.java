package com.color.util;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.color.activity.MainActivity;

/**
 * Created by wanggaolei on 2018/8/20.
 */

public class PermissionUtil {
    public static String []permission =new String[]{Manifest.permission.INTERNET,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};
    public  static void applicationPermission(Context context, Activity activity){
        if (Build.VERSION.SDK_INT>=23)
        {
            for (int i = 0; i <permission.length ; i++) {
                int checkPermission = ContextCompat.checkSelfPermission(context, permission[i]);
                if (checkPermission!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(activity,permission,1);
                }else{

                    MainActivity.isPermission=true;
                }
            }

        }
    }

}
