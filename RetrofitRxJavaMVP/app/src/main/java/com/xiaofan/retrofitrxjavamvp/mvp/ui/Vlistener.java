package com.xiaofan.retrofitrxjavamvp.mvp.ui;


import com.xiaofan.retrofitrxjavamvp.exception.ApiException;

/**
 * @author: 范建海
 * @createTime: 2017/3/30 10:07
 * @className:  Vlistener
 * @description: V层接口
 * @changed by:
 */
public interface Vlistener {

    void onTestNext(String msg);

    void showProg();

    void dismissProg();

    void onNext(String s, String m);

    void  onError(ApiException e);

}
