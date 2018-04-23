package com.example.asa.easyxiian;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.apache.commons.httpclient.HttpClient;

public class MenuActivity extends AppCompatActivity {


    HttpClient mClient = Client.getHttpClient();
    String mJSESSION;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final Intent intent = this.getIntent();
        mJSESSION = intent.getStringExtra("JSESSION");


        TextView classInfo = (TextView)findViewById(R.id.class_info);
        classInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent intentClassInfo = new Intent(MenuActivity.this,ClassesMessageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("JSESSION",mJSESSION);
                intent.putExtras(bundle);
                startActivity(intentClassInfo);
            }
        });

        TextView scoresInfo = (TextView)findViewById(R.id.scores_info);
        scoresInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent intentClassInfo = new Intent(MenuActivity.this,ScoresActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("JSESSION",mJSESSION);
                intent.putExtras(bundle);
                startActivity(intentClassInfo);
            }
        });
    }
}
