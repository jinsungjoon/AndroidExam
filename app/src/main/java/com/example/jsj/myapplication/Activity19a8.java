package com.example.jsj.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity19a8 extends AppCompatActivity {
    int mMainValue = 0;
    int mBackValue = 0;
    TextView mMainText;
    TextView mBackText;
    BackThread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity19a8);
        mMainText = (TextView)findViewById(R.id.mainvalue);
        mBackText = (TextView)findViewById(R.id.backvalue);
        Button button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainValue++;
                mMainText.setText("MainValue:"+mMainValue);
            }
        });
         thread = new BackThread(mHandler);
         thread.setDaemon(true);
         thread.start();
    }
    Handler mHandler = new Handler(){
        public void handleMessage(Message msg){
            if(msg.what==0){
                mBackText.setText("mBackValue:"+msg.arg1);
            }
        }
    };


}
