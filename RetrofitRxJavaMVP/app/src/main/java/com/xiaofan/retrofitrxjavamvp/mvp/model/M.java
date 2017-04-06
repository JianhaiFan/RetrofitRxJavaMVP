package com.xiaofan.retrofitrxjavamvp.mvp.model;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.xiaofan.retrofitrxjavamvp.entity.api.BaseApi;
import com.xiaofan.retrofitrxjavamvp.exception.ApiException;
import com.xiaofan.retrofitrxjavamvp.http.HttpManager;
import com.xiaofan.retrofitrxjavamvp.listener.HttpOnNextListener;
import com.xiaofan.retrofitrxjavamvp.mvp.presenter.PVlistener;

/**
 * @author: 范建海
 * @createTime: 2017/3/30 10:55
 * @className:  M
 * @description:  m层数据处理
 * @changed by:
 */
public class M implements Mlistener,HttpOnNextListener {

    private PVlistener pVlistener;

    public M(PVlistener pVlistener) {
        this.pVlistener = pVlistener;
    }

    @Override
    public void startPost(RxAppCompatActivity rxAppCompatActivity, BaseApi baseApi) {
        HttpManager manager = new HttpManager(this, rxAppCompatActivity);
        manager.doHttpDeal(baseApi);
    }

    @Override
    public void testDo(String s) {
        String msg = "Fan : " + s;
        pVlistener.testPSuc(msg);
    }

    @Override
    public void onNext(String resulte, String mothead) {
        pVlistener.onNext(resulte, mothead);
    }

    @Override
    public void onError(ApiException e) {
        pVlistener.onError(e);
    }
}
