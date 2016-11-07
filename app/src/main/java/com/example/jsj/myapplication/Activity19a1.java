package com.example.jsj.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity19a1 extends AppCompatActivity {
    int mMainValue = 0;
    int mBackValue = 0;
    TextView mMainText;
    TextView mBackText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity19a1);
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
        BackThread thread = new BackThread();
        thread.setDaemon(true);
        thread.start();
    }

    public void mOnClick(View v){
        mMainValue++;
        mMainText.setText("MainValue:"+mMainValue);
        mBackText.setText("BackValue:"+mBackValue);
    }
    class BackThread extends Thread{
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
