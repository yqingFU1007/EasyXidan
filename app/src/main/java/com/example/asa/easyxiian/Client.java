package com.example.asa.easyxiian;

import org.apache.commons.httpclient.HttpClient;

import java.io.Serializable;

/**
 * Created by asa on 2018/4/24.
 */

public class Client{

    private static HttpClient mHttpClient = null;
    //将构造函数封掉，只能通过对外接口来获取HttpClient实例
    private Client(){


    }
    public static HttpClient getHttpClient(){
        if(mHttpClient == null){
            mHttpClient = new HttpClient();
        }
        return mHttpClient;
    }
}
