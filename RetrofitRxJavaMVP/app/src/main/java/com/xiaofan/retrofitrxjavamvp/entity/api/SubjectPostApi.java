package com.xiaofan.retrofitrxjavamvp.entity.api;


import com.google.gson.annotations.Expose;
import com.xiaofan.retrofitrxjavamvp.http.service.HttpPostService;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * @author: 范建海
 * @createTime: 2017/3/30 11:07
 * @className:  SubjectPostApi
 * @description: 测试数据
 * @changed by:
 */
public class SubjectPostApi extends BaseApi {
    //    接口需要传入的参数 可自定义不同类型
    @Expose
    private boolean all;
    /*任何你想要传递的参数*/
//    String xxxxx;
//    String xxxxx;
//    String xxxxx;
//    String xxxxx;


    /**
     * 默认初始化需要给定回调和rx周期类
     * 可以额外设置请求设置加载框显示，回调等（可扩展）
     * 设置可查看BaseApi
     */
    public SubjectPostApi() {
        setShowProgress(true);
        setCancel(true);
//        setCache(true);
//        setMethod("fan_cache");
    }

    public boolean isAll() {
        return all;
    }

    public void setAll(boolean all) {
        this.all = all;
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpPostService httpService = retrofit.create(HttpPostService.class);
        return httpService.getAllVedioBy(isAll());
    }
}
