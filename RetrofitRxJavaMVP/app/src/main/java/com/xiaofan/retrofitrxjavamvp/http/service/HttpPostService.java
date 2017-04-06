package com.xiaofan.retrofitrxjavamvp.http.service;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author: 范建海
 * @createTime: 2017/3/30 11:06
 * @className:  HttpPostService
 * @description: 测试接口service-post相关
 * @changed by:
 */
public interface HttpPostService {

    @FormUrlEncoded
    @POST("AppFiftyToneGraph/videoLink")
    Observable<String> getAllVedioBy(@Field("once_no") boolean once_no);

    //http://192.168.1.24:9090/sysCommon/control/restful/ajaxGetVerificationCode
//        0 = {HashMap$HashMapEntry@830049680328} "partyClient" -> "appTypeEnum_0"
//        1 = {HashMap$HashMapEntry@830049680216} "type" -> "5006"
//        2 = {HashMap$HashMapEntry@830049680184} "userLoginId" -> "83112345678"
//        {"status":"2","msg":"该手机号已被注册"}

}
