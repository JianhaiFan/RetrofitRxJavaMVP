package com.xiaofan.retrofitrxjavamvp.http;

import com.trello.rxlifecycle.android.ActivityEvent;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.xiaofan.retrofitrxjavamvp.entity.api.BaseApi;
import com.xiaofan.retrofitrxjavamvp.exception.FactoryException;
import com.xiaofan.retrofitrxjavamvp.exception.RetryWhenNetworkException;
import com.xiaofan.retrofitrxjavamvp.listener.HttpOnNextListener;
import com.xiaofan.retrofitrxjavamvp.subscribers.ProgressSubscriber;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author: 范建海
 * @createTime: 2017/3/27 17:59
 * @className:  HttpManager
 * @description: http交互处理类
 * @changed by:
 */


/**
     Rxlifecycle 使用
         Activity/Fragment需继承RxAppCompatActivity/RxFragment，目前支持的有RxAppCompatActivity、RxFragment、RxDialogFragment、RxFragmentActivity。
         一、bindToLifecycle()方法
             在子类使用Observable中的compose操作符，调用，完成Observable发布的事件和当前的组件绑定，实现生命周期同步。从而实现当前组件生命周期结束时，自动取消对Observable订阅。
                 Observable.interval(1, TimeUnit.SECONDS)
                 .compose(this.bindToLifecycle())
                 .subscribe(new Action1<Long>() {
                 @Override
                 public void call(Long num) {
                 Log.i(TAG, "  " +num);
                 }
                 });
         二、bindUntilEvent() 方法
            使用ActivityEvent类，其中的CREATE、START、 RESUME、PAUSE、STOP、 DESTROY分别对应生命周期内的方法。使用bindUntilEvent指定在哪个生命周期方法调用时取消订阅。
                 Observable.interval(1, TimeUnit.SECONDS)
                 .compose(this.bindUntilEvent(ActivityEvent.PAUSE))
                 .subscribe(mSub);
 */
public class HttpManager {
    /*软引用對象*/
    private SoftReference<HttpOnNextListener> onNextListener;
    private SoftReference<RxAppCompatActivity> appCompatActivity;


    public HttpManager(HttpOnNextListener onNextListener, RxAppCompatActivity appCompatActivity) {
        this.onNextListener = new SoftReference(onNextListener);
        this.appCompatActivity = new SoftReference(appCompatActivity);
    }

    /**
     * 处理http请求
     *
     * @param basePar 封装的请求数据
     */
    public void doHttpDeal(BaseApi basePar) {
        //手动创建一个OkHttpClient并设置超时时间缓存等设置
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(basePar.getConnectionTime(), TimeUnit.SECONDS);

        /*创建retrofit对象*/
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(basePar.getBaseUrl())
                .build();
        /*rx处理*/
        ProgressSubscriber subscriber = new ProgressSubscriber(basePar, onNextListener);

        Observable observable = basePar.getObservable(retrofit)
                /*失败后的retry配置*/
                .retryWhen(new RetryWhenNetworkException())
                /*异常处理*/
                //TODO 待修改的地方
                //TODO http://blog.csdn.net/johnny901114/article/details/51533586
                .onErrorResumeNext(funcException)
                /*生命周期管理*/
//                .compose(appCompatActivity.get().bindToLifecycle())
                .compose(appCompatActivity.get().bindUntilEvent(ActivityEvent.DESTROY))
                /*http请求线程*/
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                /*回调线程*/
                .observeOn(AndroidSchedulers.mainThread())
                /*结果判断*/
                .map(basePar);

        /*数据回调*/
        observable.subscribe(subscriber);
    }


    /**
     * 异常处理
     */
    Func1 funcException = new Func1<Throwable, Observable>() {
        @Override
        public Observable call(Throwable throwable) {
            return Observable.error(FactoryException.analysisExcetpion(throwable));
        }
    };

}
