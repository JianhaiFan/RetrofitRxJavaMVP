package com.xiaofan.retrofitrxjavamvp.mvp.presenter;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.xiaofan.retrofitrxjavamvp.entity.api.BaseApi;
import com.xiaofan.retrofitrxjavamvp.exception.ApiException;
import com.xiaofan.retrofitrxjavamvp.mvp.model.M;
import com.xiaofan.retrofitrxjavamvp.mvp.model.Mlistener;
import com.xiaofan.retrofitrxjavamvp.mvp.ui.Vlistener;

/**
 * @author: 范建海
 * @createTime: 2017/3/30 10:44
 * @className:  P
 * @description:  presenter两个接口，一个请求一个回调
 *               目的：确保Model层不直接操作View层
 * @changed by:
 */
public class P implements Plistener,PVlistener {

    private Vlistener vlistener;

    private Mlistener mlistener;

    public P(Vlistener vlistener) {
        this.vlistener = vlistener;
        mlistener = new M(this);
    }

    @Override
    public void onNext(String resulte, String mothead) {
        vlistener.onNext(resulte,mothead);
        vlistener.dismissProg();
    }

    @Override
    public void onError(ApiException e) {
        vlistener.onError(e);
        vlistener.dismissProg();
    }

    @Override
    public void startPost(RxAppCompatActivity rxAppCompatActivity, BaseApi baseApi) {
        vlistener.showProg();
        mlistener.startPost(rxAppCompatActivity,baseApi);
    }

    @Override
    public void doTest(String msg) {
        mlistener.testDo(msg);
    }

    @Override
    public void testPSuc(String msg) {
        vlistener.onTestNext(msg);
    }
}
