package com.example.jsj.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity19a7 extends AppCompatActivity {
    int mMainValue = 0;
    int mBackValue = 0;
    TextView mMainText;
    TextView mBackText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity19a7);
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

                //mHandler.sendEmptyMessage(0);
                /*mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mBackText.setText("BackValue:"+mBackValue);
                    }
                });*/
                /*Runnable runUpdate = new Runnable() {
                    @Override
                    public void run() {
                        mBackText.setText("BackValue:"+mBackValue);
                    }
                };*/
               // mHandler.post(runUpdate);
                //runOnUiThread(runUpdate);
                mBackValue++;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mBackText.setText("BackValue:"+mBackValue);
                    }
                });
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    ;
                }
            }
        }
    }
   // Handler mHandler = new Handler();
}
