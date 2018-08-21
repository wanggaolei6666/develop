package com.color.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;

import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.color.bean.OnDownloadListener;
import com.color.them.R;
import com.color.util.ConstantUtil;
import com.color.util.DownLoadUtil;
import com.color.util.PermissionUtil;
import com.example.colorthemmodule.ColorThemActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ColorThemActivity implements View.OnClickListener {
    private Map<String, Integer> collectId = new HashMap<>();
    private Handler mHandler;
    private List<Integer> backGroudId = new ArrayList<>();
    private RelativeLayout mRootView;
    private TextView mTextView;
    private CanvasDraw mCanvas;
    private int i = -1;
    private int updateValue;
    private ProgressBar mProgress;
    private ProgressBar mProgress1;
    public static boolean isPermission=false;
    private String url="http://58.218.205.252:8666/data/wisegame/38a9a1e9696e730d/chuzhouyiyuan_3.apk?business_id=9029&task_id=6622724406056648705&from=a1101.apk";
    private TextView mProBarDesc;
    @SuppressLint({"HandlerLeak", "ObjectAnimatorBinding"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        backGroudId.add(R.mipmap.stylephoto);
        backGroudId.add(R.mipmap.two);
        backGroudId.add(R.mipmap.zhuang);
        backGroudId.add(R.mipmap.mu);

        mRootView = (RelativeLayout) findViewById(R.id.root_view);
        mTextView = (TextView) findViewById(R.id.test_desc);
        mCanvas = (CanvasDraw) findViewById(R.id.canvas_color);
        mProgress=(ProgressBar) findViewById(R.id.pro_bar);
        mProgress1=(ProgressBar) findViewById(R.id.pro_bar1);
        mProBarDesc = (TextView) findViewById(R.id.probar_desc);
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case ConstantUtil.HANDELR_TAG:
                        if (i == backGroudId.size() - 1) {
                            i = 0;
                        } else ++i;

                        updateBackGroundImage(mRootView);
                        sendEmptyMessageDelayed(ConstantUtil.HANDELR_TAG, 2000);
                        break;

                }
            }
        };
        mTextView.setOnClickListener(this);
        mHandler.sendEmptyMessage(ConstantUtil.HANDELR_TAG);
        PermissionUtil.applicationPermission(getApplicationContext(),this);
        if (isPermission){
            DownLoadUtil.getInstance().download(url, "download", new OnDownloadListener() {
                @Override
                public void onDownloadFailed(final String message) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.e("fileDownload", message );
                            Toast.makeText(MainActivity.this, "下载失败"+"\t"+message, Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onDownloading(final int progress) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mProgress.setProgress(progress);
                            mProBarDesc.setText(progress+"%");
                            Log.e("progress", "run: "+progress);
                        }
                    });
                }

                @Override
                public void onDownloadSuccess(File file) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }

//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTextView, "textColor", mTextView.getX(), updateValue);
//        ValueAnimator valueAnimator = ValueAnimator.ofFloat(mTextView.getX(), mTextView.getWidth());
//
//        valueAnimator.setInterpolator(new LinearInterpolator());
//        valueAnimator.setDuration(3000);
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                updateValue= (int) animation.getAnimatedValue();
//
//                if ()
//            }
//        });


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
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected Map<String, Integer> getId() {
        collectId.put("toobarid", R.id.toolbar);
        collectId.put("titleid", R.id.tv_title);

        return collectId;
    }

    @Override
    protected void onStop() {
        super.onStop();
        mHandler.removeMessages(ConstantUtil.HANDELR_TAG);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mHandler.sendEmptyMessage(ConstantUtil.HANDELR_TAG);
    }

    @Override
    protected String setTitle() {
        return "测试是否显示成功";
    }


    private int[] color = {Color.GREEN, Color.BLUE, Color.RED, Color.BLACK, Color.YELLOW};

    private void updateBackGroundImage(View view) {
        if (backGroudId.size() > 0) {
            view.setBackgroundResource(backGroudId.get(i));
//            view.setAnimation(intValueAnim());
        }
        mTextView.setTextColor(color[randomInt(color.length)]);


    }


    private AlphaAnimation intValueAnim() {
        final AlphaAnimation animator = new AlphaAnimation(1, 0);//淡出效果
        animator.setDuration(300);

        return animator;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeMessages(ConstantUtil.HANDELR_TAG);
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.test_desc:
                startActivity(new Intent(this,LoginActivity.class));
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int j = 0; j <grantResults.length; j++) {
            if (grantResults[j]== PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "已授权"+permissions[i], Toast.LENGTH_SHORT).show();
                isPermission=true;
            }else{
                Toast.makeText(this, "未授权"+permissions[i], Toast.LENGTH_SHORT).show();
                isPermission=false;
            }
        }
    }
}
