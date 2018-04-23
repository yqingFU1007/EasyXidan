package com.example.asa.easyxiian;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.apache.commons.httpclient.HttpClient;

public class MenuActivity extends AppCompatActivity {

    PassData mData;
    HttpClient mClient;
    String mJSESSION;
    String mUserName;
    String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final Intent intent = this.getIntent();
        mData = (PassData) intent.getExtras().getSerializable("info");
        mClient = mData.getmClient();
        mJSESSION = mData.getmJSESSIONID();
        Log.e("MenuActivity",mJSESSION + "");
        mUserName = mData.getmUserName();
        mPassword = mData.getmPassword();



        TextView classInfo = (TextView)findViewById(R.id.class_info);
        classInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                PassData data = new PassData(mClient,mJSESSION,mUserName,mPassword);
                Intent intentClassInfo = new Intent(MenuActivity.this,ClassesMessageActivity.class);
                Bundle bundleClassInfo = new Bundle();
                bundleClassInfo.putSerializable("info",data);
                intentClassInfo.putExtras(bundleClassInfo);
                startActivity(intentClassInfo);
            }
        });
    }
}
