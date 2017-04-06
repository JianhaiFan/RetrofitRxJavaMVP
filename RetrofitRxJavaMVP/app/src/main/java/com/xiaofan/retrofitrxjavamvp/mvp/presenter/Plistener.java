package com.xiaofan.retrofitrxjavamvp.mvp.presenter;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.xiaofan.retrofitrxjavamvp.entity.api.BaseApi;

/**
 * @author: 范建海
 * @createTime: 2017/3/30 10:16
 * @className:  Plistener
 * @description: p层提供给v层的接口
 * @changed by:
 */
public interface Plistener {
    /**
     * http请求
     * @param rxAppCompatActivity
     * @param baseApi
     */
    void startPost(RxAppCompatActivity rxAppCompatActivity, BaseApi baseApi);

    /**
     * 其他方法-演示
     * @param msg
     */
    void doTest(String msg);

}