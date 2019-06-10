package com.example.www.gyapplication;


import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by hezijie on 2019/4/25.
 */

public interface RequestInterface {

    @GET("LoginAction_loginMobile.action?update=gViewerAndroid&server=login&userAccount=FYD0001&password=000000")
    Observable<Translations> getCall();
}
