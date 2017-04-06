package com.xiaofan.retrofitrxjavamvp.mvp.model;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.xiaofan.retrofitrxjavamvp.entity.api.BaseApi;

/**
 * @author: 范建海
 * @createTime: 2017/3/30 10:54
 * @className:  Mlistener
 * @description: m层提供给p层的接口
 * @changed by:
 */
public interface Mlistener {
    /**
     * 开始任务
     * @param rxAppCompatActivity
     * @param baseApi
     */
    void startPost(RxAppCompatActivity rxAppCompatActivity, BaseApi baseApi);

    /**
     * 测试接口
     * @param s
     */
    void testDo(String s);
}
