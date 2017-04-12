package com.xiaofan.retrofitrxjavamvp.subscribers;

import android.util.Log;

import com.xiaofan.retrofitrxjavamvp.application.App;
import com.xiaofan.retrofitrxjavamvp.entity.api.BaseApi;
import com.xiaofan.retrofitrxjavamvp.exception.ApiException;
import com.xiaofan.retrofitrxjavamvp.exception.CodeException;
import com.xiaofan.retrofitrxjavamvp.exception.HttpTimeException;
import com.xiaofan.retrofitrxjavamvp.http.cookie.CookieResulte;
import com.xiaofan.retrofitrxjavamvp.listener.HttpOnNextListener;
import com.xiaofan.retrofitrxjavamvp.utils.AppUtil;
import com.xiaofan.retrofitrxjavamvp.utils.CookieDbUtil;

import java.lang.ref.SoftReference;
import java.net.SocketTimeoutException;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * @author: 范建海
 * @createTime: 2017/3/28 10:36
 * @className:  ProgressSubscriber
 * @description: 统一处理缓存-数据持久化 异常处理
 * @changed by:
 */
public class ProgressSubscriber<T> extends Subscriber<T> implements Subscription {
    // 回调接口
    private SoftReference<HttpOnNextListener> mSubscriberOnNextListener;
    /*请求数据*/
    private BaseApi api;


    /**
     * 构造
     *
     * @param api
     */
    public ProgressSubscriber(BaseApi api, SoftReference<HttpOnNextListener> listenerSoftReference) {
        this.api = api;
        this.mSubscriberOnNextListener = listenerSoftReference;
    }


    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        Log.e("fanjianhai","ProgressSubscriber onStart...");
        /*缓存并且有网*/
        if (api.isCache() && AppUtil.isNetworkAvailable(App.app)) {
             /*获取缓存数据*/
            CookieResulte cookieResulte = CookieDbUtil.getInstance().queryCookieBy(api.getUrl());
            if (cookieResulte != null) {
                long time = (System.currentTimeMillis() - cookieResulte.getTime()) / 1000;
                if (time < api.getCookieNetWorkTime()) {
                    if (mSubscriberOnNextListener.get() != null) {
                        mSubscriberOnNextListener.get().onNext(cookieResulte.getResulte(), api.getMethod());
                    }
                    onCompleted();
                    unsubscribe();
                }
            }
        }
    }


    @Override
    public void onCompleted() {
        Log.e("fanjianhai","ProgressSubscriber onCompleted...");
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        Log.e("fanjianhai","ProgressSubscriber onError...");
        /*需要緩存并且本地有缓存才返回*/
        if (api.isCache()) {
            getCache();
        } else {
            errorDo(e);
        }
    }

    /**
     * 获取cache数据
     */
    private void getCache() {
        Observable.just(api.getUrl()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                errorDo(e);
            }

            @Override
            public void onNext(String s) {
                           /*获取缓存数据*/
                CookieResulte cookieResulte = CookieDbUtil.getInstance().queryCookieBy(s);
                if (cookieResulte == null) {
                    throw new HttpTimeException(HttpTimeException.NO_CHACHE_ERROR);
                }
                long time = (System.currentTimeMillis() - cookieResulte.getTime()) / 1000;
                if (time < api.getCookieNoNetWorkTime()) {
                    if (mSubscriberOnNextListener.get() != null) {
                        mSubscriberOnNextListener.get().onNext(cookieResulte.getResulte(), api.getMethod());
                    }
                } else {
                    CookieDbUtil.getInstance().deleteCookie(cookieResulte);
                    throw new HttpTimeException(HttpTimeException.CHACHE_TIMEOUT_ERROR);
                }
            }
        });
    }


    /**
     * 错误统一处理
     *
     * @param e
     */
    private void errorDo(Throwable e) {
        Log.e("fanjianhai","ProgressSubscriber errorDo:" + e.toString());
        HttpOnNextListener httpOnNextListener = mSubscriberOnNextListener.get();
        if (httpOnNextListener == null) return;
        if (e instanceof ApiException) {
            httpOnNextListener.onError((ApiException) e);
        } else if (e instanceof HttpTimeException) {
            HttpTimeException exception = (HttpTimeException) e;
            httpOnNextListener.onError(new ApiException(exception, CodeException.RUNTIME_ERROR, exception.getMessage()));
        } else {
            httpOnNextListener.onError(new ApiException(e, CodeException.UNKNOWN_ERROR, e.getMessage()));
        }
    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        Log.e("fanjianhai","ProgressSubscriber： " + t.toString());
        if ("123".equals(t.toString())) {
            onError(new Throwable());
        }
         /*缓存处理*/
        if (api.isCache()) {
            CookieResulte resulte = CookieDbUtil.getInstance().queryCookieBy(api.getUrl());
            long time = System.currentTimeMillis();
            /*保存和更新本地数据*/
            if (resulte == null) {
                resulte = new CookieResulte(api.getUrl(), t.toString(), time);
                CookieDbUtil.getInstance().saveCookie(resulte);
            } else {
                resulte.setResulte(t.toString());
                resulte.setTime(time);
                CookieDbUtil.getInstance().updateCookie(resulte);
            }
        }

        if (mSubscriberOnNextListener.get() != null) {
            mSubscriberOnNextListener.get().onNext((String) t, api.getMethod());
        }
    }





}