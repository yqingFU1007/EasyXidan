package com.example.asa.easyxiian;

import org.apache.commons.httpclient.HttpClient;

import java.io.Serializable;

/**
 * Created by asa on 2018/4/23.
 */

public class PassData implements Serializable{

    private Client mClient;
    private String mJSESSIONID;
    private String mUserName;
    private String mPassword;

    public PassData(Client client,String JSESSIONID,String userName,String password){
        mClient = client;
        mJSESSIONID = JSESSIONID;
        mUserName = userName;
        mPassword = password;
    }

    public Client getmClient(){
        return mClient;
    }

    public String getmJSESSIONID(){
        return mJSESSIONID;
    }

    public String getmUserName(){
        return mUserName;
    }

    public String getmPassword(){
        return mPassword;
    }
}
