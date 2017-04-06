package com.xiaofan.retrofitrxjavamvp.mvp.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
/**
 * @author: 范建海
 * @createTime: 2017/3/30 10:05
 * @className:  BaseActivity
 * @description:
 * @changed by:
 */
public class BaseActivity extends RxAppCompatActivity {
    // 加载框可自己定义
    protected ProgressDialog pd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(pd==null){
            pd = new ProgressDialog(this);
            pd.setCancelable(false);
        }
    }

    protected void showP(){
        if(pd!=null&&!pd.isShowing()){
            pd.show();
        }
    }

    protected void dismissP(){
        if(pd!=null&&pd.isShowing()){
            pd.dismiss();
        }
    }
}
