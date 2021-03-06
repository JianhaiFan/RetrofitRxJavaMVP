package com.xiaofan.retrofitrxjavamvp.http.cookie;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author: 范建海
 * @createTime: 2017/3/28 10:57
 * @className:  CookieResulte
 * @description: POST请求缓存数据
 * @changed by:
 */
@Entity
public class CookieResulte {
    @Id
    private long id;
    /*url*/
    private String url;
    /*返回结果*/
    private String resulte;
    /*时间*/
    private long time;

    public CookieResulte(String url, String resulte, long time) {
        this.url = url;
        this.resulte = resulte;
        this.time = time;
    }

    @Generated(hash = 1796425720)
    public CookieResulte(long id, String url, String resulte, long time) {
        this.id = id;
        this.url = url;
        this.resulte = resulte;
        this.time = time;
    }

    @Generated(hash = 2104390000)
    public CookieResulte() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResulte() {
        return this.resulte;
    }

    public void setResulte(String resulte) {
        this.resulte = resulte;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
