package com.color.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.color.them.R;
import com.color.them.com.color.util.ConstantUtil;
import com.example.colorthemmodule.ColorThemActivity;

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
}
