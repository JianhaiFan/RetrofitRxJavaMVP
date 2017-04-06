package com.xiaofan.retrofitrxjavamvp.listener;


import com.xiaofan.retrofitrxjavamvp.exception.ApiException;

/**
 * @author: 范建海
 * @createTime: 2017/3/27 17:09
 * @className:  HttpOnNextListener
 * @description: 成功回调处理
 * @changed by:
 */
public interface HttpOnNextListener {

    /**
     * 成功后回调方法
     *
     * @param resulte
     * @param mothead
     */
    void onNext(String resulte, String mothead);

    /**
     * 失败
     * 失败或者错误方法
     * 自定义异常处理
     *
     * @param e
     */
    void onError(ApiException e);
}
