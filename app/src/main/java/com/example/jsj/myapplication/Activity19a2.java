package com.example.jsj.myapplication;

import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity19a2 extends AppCompatActivity {
    int mMainValue = 0;
    int mBackValue = 0;
    TextView mMainText;
    TextView mBackText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity19a2);
        mMainText = (TextView)findViewById(R.id.mainvalue);
        mBackText = (TextView)findViewById(R.id.backvalue);
        Button button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainValue++;
                mMainText.setText("MainValue:"+mMainValue);
                mBackText.setText("BackValue:"+mBackValue);
            }
        });
        BackRunnable runnable = new BackRunnable();
        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        thread.start();
    }

    public void mOnClick(View v){
        mMainValue++;
        mMainText.setText("MainValue:"+mMainValue);
        mBackText.setText("BackValue:"+mBackValue);
    }
    class BackRunnable implements Runnable{
        public void run(){
            while(true){
                mBackValue++;
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    ;
                }
            }
        }
    }
}
