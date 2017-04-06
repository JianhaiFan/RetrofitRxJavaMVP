package com.xiaofan.retrofitrxjavamvp.entity.api;

import com.alibaba.fastjson.JSONObject;
import com.xiaofan.retrofitrxjavamvp.entity.resulte.BaseResultEntity;
import com.xiaofan.retrofitrxjavamvp.exception.HttpTimeException;

import retrofit2.Retrofit;
import rx.Observable;
import rx.functions.Func1;

/**
 * @author: 范建海
 * @createTime: 2017/3/27 17:00
 * @className:  BaseApi
 * @description:
 * @changed by:请求数据统一封装类
 */
public abstract class BaseApi<T> implements Func1<T, String> {
    /*是否能取消加载框*/
    private boolean cancel=false;
    /*是否显示加载框*/
    private boolean showProgress=true;
    /*是否需要缓存处理*/
    private boolean cache=false;
    /*基础url*/
    private  String baseUrl="http://www.izaodao.com/Api/";
    /*方法-如果需要缓存必须设置这个参数；不需要不用設置(缓存的时候必须给method赋唯一值)*/
    private String method;
    /*超时时间-默认6秒*/
    private int connectionTime = 6;
    /*有网情况下的本地缓存时间默认60秒*/
    private int cookieNetWorkTime=60;
    /*无网络的情况下本地缓存时间默认30天*/
    private int cookieNoNetWorkTime=24*60*60*30;

    /**
     * 设置参数
     *
     * @param retrofit
     * @return
     */
    public abstract Observable getObservable(Retrofit retrofit);



    public int getCookieNoNetWorkTime() {
        return cookieNoNetWorkTime;
    }

    public void setCookieNoNetWorkTime(int cookieNoNetWorkTime) {
        this.cookieNoNetWorkTime = cookieNoNetWorkTime;
    }

    public int getCookieNetWorkTime() {
        return cookieNetWorkTime;
    }

    public void setCookieNetWorkTime(int cookieNetWorkTime) {
        this.cookieNetWorkTime = cookieNetWorkTime;
    }

    public String getMethod() {
        return method;
    }

    public int getConnectionTime() {
        return connectionTime;
    }

    public void setConnectionTime(int connectionTime) {
        this.connectionTime = connectionTime;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getUrl() {
        return baseUrl+method;
    }

    public boolean isCache() {
        return cache;
    }

    public void setCache(boolean cache) {
        this.cache = cache;
    }

    public boolean isShowProgress() {
        return showProgress;
    }

    public void setShowProgress(boolean showProgress) {
        this.showProgress = showProgress;
    }

    public boolean isCancel() {
         return cancel;
     }

     public void setCancel(boolean cancel) {
         this.cancel = cancel;
     }

    @Override
    public String call(T httpResult) {
        BaseResultEntity baseResulte= JSONObject.parseObject(httpResult.toString(),BaseResultEntity.class);
        if (baseResulte.getRet() == 0) {
            throw new HttpTimeException(baseResulte.getMsg());
        }
        return baseResulte.getData();
    }
}
