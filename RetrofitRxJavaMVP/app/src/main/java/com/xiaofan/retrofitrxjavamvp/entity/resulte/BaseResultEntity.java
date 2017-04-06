package com.xiaofan.retrofitrxjavamvp.entity.resulte;

/**
 * @author: 范建海
 * @createTime: 2017/3/27 16:17
 * @className:  BaseResultEntity
 * @description: 回调信息统一封装类
 * @changed by:
 */
public class BaseResultEntity {
    //  判断标识
    private int ret;
    //  提示信息
    private String msg;
    //  显示数据（用户需要关心的数据）
    private String data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }
}
