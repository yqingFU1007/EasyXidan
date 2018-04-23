package com.example.asa.easyxiian;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;

public class ClassesMessageActivity extends AppCompatActivity {

    final static String classInforURL = "http://jwxt.xidian.edu.cn/xkAction.do?actionType=6";

    PassData mData;
    HttpClient mClient;
    String mJSESSION;
    String mClassInfo;
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes_message);

        final Intent intent = this.getIntent();
        mData = (PassData) intent.getExtras().getSerializable("info");
        mClient = mData.getmClient();
        mJSESSION = mData.getmJSESSIONID();
        Log.e("ClassesMessageActivity","" + mJSESSION);

//        final Intent intent = this.getIntent();
//        mData = (PassData) intent.getSerializableExtra("info");
//        mClient = mData.getmClient();
//        mJSESSION = mData.getmJSESSIONID();


        mTextView = (TextView)findViewById(R.id.class_info);

        new getClassesInformationTask().execute(classInforURL);


    }

    private class getClassesInformationTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String result = null;
            GetMethod getMethod = null;

            try {
                getMethod = NetworkUtils.getMethodUseCookie(mClient, mJSESSION, strings[0]);
                result = getMethod.getResponseBodyAsString();
                mClassInfo = result;
            } catch (IOException e) {
                Log.e("MainActivity", "使用Cookie的GET失败。");
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            mTextView.setText(s);
        }
    }
}
