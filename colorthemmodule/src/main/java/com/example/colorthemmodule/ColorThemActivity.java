package com.example.colorthemmodule;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Map;
import java.util.Random;
import java.util.zip.Inflater;

public abstract class ColorThemActivity extends AppCompatActivity {
    private Toolbar mToobar;
    private TextView mTitle;
    private Map<String, Integer> id;
    private Random mRandom;
    private LayoutInflater inflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        inflater= LayoutInflater.from(this);
        Log.e("parent", "onCreate: ");
        if (isShowTitle()) {
            id = getId();
            if (!id.isEmpty()){
                for (Map.Entry<String, Integer> entery:id.entrySet()
                     ) {
                   if(entery.getKey().equals("toobarid")){
                       mToobar= (Toolbar)findViewById(entery.getValue());
                    }else{
                       mTitle=(TextView)findViewById(entery.getValue());
                    }
                }
                mTitle.setText(setTitle());
                mToobar.setBackgroundColor(setToobarColor());
                setSupportActionBar(mToobar);
            }

        }
        initData();
    }
    protected abstract int setToobarColor();
    protected abstract int setTitleColor();
    protected abstract boolean isShowTitle();
    protected  abstract String setTitle();

    protected abstract int getLayoutId();

    protected View getCustomView(){
        return inflater.inflate(getLayoutId(),null);
    }
    protected abstract Map<String,Integer> getId();
    private void initData(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(setTitleColor());
        }
        if (null==mRandom){
            mRandom=new Random();
        }
    }
    protected int randomInt(int max){
        return mRandom.nextInt(max);
    }


}
