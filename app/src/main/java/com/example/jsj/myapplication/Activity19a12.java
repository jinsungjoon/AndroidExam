package com.example.jsj.myapplication;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class Activity19a12 extends AppCompatActivity {
    int mValue;
    ProgressDialog mProgress;
    TextView mTextView;
    AcuumThread mThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity19a12);
        mTextView = (TextView)findViewById(R.id.text);
        findViewById(R.id.btn).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("col1", "1");
                    jsonObject.put("col2", "2");
                }catch (Exception e){
                    System.out.println("----------------");
                    System.out.println(e.toString());
                    System.out.println("----------------");
                }
                new AccumulateTask().execute(jsonObject);
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                mTextView.setText("0");
                int start = 0, end = 100;
                int result;
                mProgress = new ProgressDialog(Activity19a12.this);
                mProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                mProgress.setTitle("Updating");
                mProgress.setMessage("Wait....");
                mProgress.setCancelable(false);
                mProgress.setProgress(0);
                mProgress.setButton("cancel",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog,int whichButton){
                        mThread.mQuit=true;
                        mProgress.dismiss();
                    }
                });
                mProgress.show();
                mThread = new AcuumThread(start,end,mAfterAccum);
                mThread.start();
            }
        });
    }
    int Accumulate(int start,int end){
        int sum = 0;
        for(int i = start; i <= end; i++){
            sum += i;
        }
        try {
            Thread.sleep(20);
        }catch (InterruptedException e){
            ;
        }
        return  sum;
    }

    class AccumulateTask extends AsyncTask<JSONObject,Integer,Integer>{
        protected void onPreExecute(){
            mValue =0;

            mProgress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgress.setTitle("Updating");
            mProgress.setMessage("Wait....");
            mProgress.setCancelable(false);
            mProgress.setProgress(0);
            mProgress.setButton("cancel",new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog,int whichButton){
                    cancel(true);
                }
            });
            mProgress.show();
        }

        protected Integer doInBackground(JSONObject...arg0){
            System.out.println("----------------");
            System.out.println(arg0[0].toString());
            System.out.println("----------------");
            while(isCancelled()==false){
                mValue++;
                if(mValue<=100){
                    publishProgress(mValue);
                }else{
                    break;
                }
                try {
                    Thread.sleep(50);
                }catch (InterruptedException e){
                    ;
                }
            }
            return mValue;
        }

        protected void onProgressUpdate(Integer... progress){
            mProgress.setProgress(progress[0]);
            mTextView.setText(Integer.toString(progress[0]));
        }

        protected void OnCancelled(){
            mProgress.dismiss();
        }
    }

    class AcuumThread extends Thread{
        int mStart,mEnd,mResult;
        Handler mAfter;
        boolean mQuit;
        AcuumThread(int start,int end,Handler after){
            mStart = start;
            mEnd   = end;
            mQuit  = false;
            mAfter = after;
        }
        public void run(){
            mResult = 0;
            for(int i = mStart ; i <= mEnd ; i++){
                mResult +=1;
                System.out.println("mResult:"+mResult);
                try{
                    Thread.sleep(20);
                }catch (InterruptedException e){
                    ;
                }
                if(mQuit){
                    return;
                }
            }
            mAfter.sendEmptyMessage(0);
        }
    }
    Handler mAfterAccum = new Handler(){
      public void handleMessage(Message msg){
          mProgress.dismiss();
          if(mThread.mQuit==false){
              mTextView.setText(""+mThread.mResult);
          }
      }
    };
}
