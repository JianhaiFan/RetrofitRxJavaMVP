package com.xiaofan.retrofitrxjavamvp;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn)
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    static final String TAG = "fanjianhai";
    static boolean flag = false;

    @OnClick(value = R.id.btn)
    void onSubmit(View v) {
        Log.e(TAG, "-----------------------------");
        final Observable<String> observable =  Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (flag) {
                    subscriber.onNext("业务请求...");
                }else  {
                    subscriber.onError(new Throwable("ERROR"));
                }
//                for (int i = 0; i < 5; i++) {
//                    if(i == 3) {
//                        subscriber.onError(new Throwable("ERROR"));
//                    }else{
//                        subscriber.onNext(i + "");
//                    }
//                }
                subscriber.onCompleted();
            }
        });

        Subscriber<String> mySubscriber =new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                Log.e(TAG,"onNext................."+s);
            }

            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted.................");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError.....................");
            }
        };

//        observable
//                .onErrorResumeNext(Observable.just("this is an error observable","error2"))
//                .subscribe(mySubscriber);

        observable.onErrorResumeNext(new Func1<Throwable, Observable<? extends String>>() {
            @Override
            public Observable<? extends String> call(Throwable throwable) {
                return createTokenObvervable().flatMap(new Func1<String, Observable<? extends String>>() {
                    @Override
                    public Observable<? extends String> call(String token) {
                        // TODO 这里用来展示新的Token，缓存号新的Token值
                        Log.e(TAG,"token: " + token);
                        return observable;
                    }
                });
            }
        }).subscribe(mySubscriber);



    }

    public Observable<String> createTokenObvervable() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> observer) {
                try {
                    if (!observer.isUnsubscribed()) {
                        //TODO 填写请求回来的Token值
                        Log.e(TAG,"HttpManager createTokenObvervable call!");

                        SystemClock.sleep(2000);
                        flag = true;
                        observer.onNext("从服务器获取的新的Token值：" + "123");
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io());
    }

}