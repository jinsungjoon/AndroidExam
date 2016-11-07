package com.example.jsj.myapplication;

import android.os.Handler;
import android.os.Message;

/**
 * Created by jsj on 2016-11-01.
 */

public class BackThread extends Thread{
    int mBackValue = 0;
    Handler mHandler;

    BackThread(Handler handler){
        mHandler = handler;
    }

    public void run(){
        while (true){
            mBackValue++;
            //Message msg = new Message();
            Message msg = Message.obtain(mHandler,0,mBackValue,0);
            msg.what = 0;
            msg.arg1 = mBackValue;
            mHandler.sendMessage(msg);
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                ;
            }
        }
    }
}
