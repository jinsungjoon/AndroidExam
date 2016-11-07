package com.example.jsj.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Activity19a9 extends AppCompatActivity {
    int mMainValue = 0;
    int mBackValue = 0;
    TextView mMainText;
    TextView mBackText;
    EditText mNumEdit;
    CalcThread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity19a9);
        mMainText = (TextView)findViewById(R.id.mainvalue);
        mBackText = (TextView)findViewById(R.id.backvalue);
        mNumEdit  = (EditText)findViewById(R.id.number);
        Button btnincrease = (Button) findViewById(R.id.increase);
        Button btnsquarea  = (Button) findViewById(R.id.squarea);
        Button btnroot     = (Button) findViewById(R.id.root);
        BtnClickListener btnClickListener = new BtnClickListener();
        btnincrease.setOnClickListener(btnClickListener);
        btnsquarea.setOnClickListener(btnClickListener);
        btnroot.setOnClickListener(btnClickListener);

        thread = new CalcThread(mHandler);
        thread.setDaemon(true);
        thread.start();
    }
    Handler mHandler = new Handler(){
        public void handleMessage(Message msg){
            if(msg.what==0){
                System.out.println("1111");
                mBackText.setText("Squarea Result:"+msg.arg1);
            }else if(msg.what==1){
                System.out.println("2222");
                mBackText.setText("Root Result:"+((Double)msg.obj).doubleValue());
            }

        }

    };
    class CalcThread extends Thread{
        Handler mMainHandler;
        Handler mBackHandler;

        CalcThread(Handler handler){
            mMainHandler = handler;
        }

        public void run(){
            Looper.prepare();
            mBackHandler = new Handler(){
                public void handleMessage(Message msg){
                    Message retmsg = new Message();
                    switch (msg.what){
                        case 0:
                            try{
                                Thread.sleep(200);
                            }catch (InterruptedException e){
                                ;
                            }
                            retmsg.what=0;
                            retmsg.arg1 = msg.arg1 * msg.arg1;
                            System.out.println("1");
                            break;
                        case 1:
                            try{
                                Thread.sleep(200);
                            }catch (InterruptedException e){
                                ;
                            }
                            retmsg.what=1;
                            retmsg.obj = new Double(Math.sqrt((double)msg.arg1));
                            System.out.println("2");
                            break;
                    }
                    mMainHandler.sendMessage(retmsg);
                }
            };
            Looper.loop();
        }
    }

    class BtnClickListener implements Button.OnClickListener{
        @Override
        public void onClick(View v) {
            Message msg;
            switch (v.getId()){
                case R.id.increase:
                    mMainValue++;
                    mMainText.setText("MainValue : "+mMainValue);
                    System.out.println("increase");
                    break;

                case R.id.squarea:
                    msg = new Message();
                    msg.what = 0;
                    msg.arg1=Integer.parseInt(mNumEdit.getText().toString());
                    thread.mBackHandler.sendMessage(msg);
                    System.out.println("squarea");
                    break;

                case R.id.root:
                    msg = new Message();
                    msg.what = 1;
                    msg.arg1=Integer.parseInt(mNumEdit.getText().toString());
                    thread.mBackHandler.sendMessage(msg);
                    System.out.println("root");
                    break;

            }
        }
    }

}
