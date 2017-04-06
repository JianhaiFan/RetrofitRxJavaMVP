package com.xiaofan.retrofitrxjavamvp.mvp.presenter;

import com.xiaofan.retrofitrxjavamvp.exception.ApiException;

/**
 * @author: 范建海
 * @createTime: 2017/3/30 10:42
 * @className:  PVlistener
 * @description: mvp 中p层提供给m层使用的接口
 * @changed by:
 */
public interface PVlistener {

    /**
     * 测试回调
     * @param msg
     */
    void testPSuc(String msg);

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
