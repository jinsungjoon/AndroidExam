package com.example.jsj.myapplication;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Activity19a11 extends AppCompatActivity {
    boolean bUploading = false;
    TextView txtcounter;
    int mvalue=0;
    int mvalue2=0;
    int mvalue3=0;
    ProgressDialog mProgressDialog;
    boolean mQuit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_activity19a11);
        findViewById(R.id.upload).setOnClickListener(mClickListener);
        findViewById(R.id.upload2).setOnClickListener(mClickListener);
        findViewById(R.id.plusss).setOnClickListener(mClickListener);
        findViewById(R.id.update).setOnClickListener(mClickListener);
        findViewById(R.id.update2).setOnClickListener(mClickListener);
        findViewById(R.id.update3).setOnClickListener(mClickListener);
        txtcounter = (TextView)findViewById(R.id.txtcounter);
    }

    public void doUpload(){
        for(int i=0;i<100;i++){
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                ;
            }
            Toast.makeText(this,"업로드를 완료했습니다.",Toast.LENGTH_SHORT).show();
        }
    }
    public void doUpload2(){
        for(int i=0;i<100;i++){
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                ;
            }
            //Toast.makeText(this,"업로드를 완료했습니다.",Toast.LENGTH_SHORT).show();
        }
    }

    public void update(){
        mvalue=0;
        txtcounter.setText("0");
        for(int i=0;i<100;i++){
            mvalue++;
            txtcounter.setText(Integer.toString(mvalue));
            try{
                Thread.sleep(50);
            }catch (InterruptedException e){
                ;
            }
            Toast.makeText(this,"업로드를 완료했습니다.",Toast.LENGTH_SHORT).show();
        }
    }
    @SuppressWarnings("deprecation")
    protected Dialog onCreateDialog(int id){
        switch (id){
            case 0:
                mProgressDialog = new ProgressDialog(this);
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                mProgressDialog.setTitle("Updating");
                mProgressDialog.setMessage("Wait...");
                mProgressDialog.setCancelable(false);
                mProgressDialog.setButton("cancel", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog,int whichButton){
                        mQuit = true;
                        dismissDialog(0);
                    }
                });
                return mProgressDialog;
        }
        return null;
    }
    Button.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            //이곳에 버튼 클릭시 일어날 일을 적습니다.
            System.out.println(v.getId());
            switch (v.getId()){
                case R.id.plusss:

                    int count = Integer.parseInt(txtcounter.getText().toString()) ;
                    System.out.println(11);
                    txtcounter.setText(Integer.toString(count+1));
                    break;
                case R.id.upload:
                    System.out.println(22);
                    doUpload();
                    break;
                case R.id.upload2:
                    System.out.println(33);
                    if(bUploading)return;
                    Thread thread = new Thread(){
                        public void run(){
                            doUpload2();
                            mHandler.sendEmptyMessage(0);
                        }
                    };
                    bUploading = true;
                    thread.start();
                    break;
                case R.id.update:

                    update();
                    break;
                case R.id.update2:
                    mvalue2=0;
                    txtcounter.setText("0");
                    mHandler2.sendEmptyMessage(0);
                    break;
                case R.id.update3:
                    mvalue3=0;
                    txtcounter.setText("0");
                    showDialog(0);
                    mQuit =false;
                    mHandler3.sendEmptyMessage(0);
                    break;
            }
        }
    };

    Handler mHandler = new Handler(){
        public void handleMessage(Message msg){
            if(msg.what==0){
                bUploading = false;
                Toast.makeText(getApplicationContext(),"업로드를 완료했습니다.",Toast.LENGTH_SHORT).show();
            }
        }
    };

    Handler mHandler2 = new Handler(){
        public void handleMessage(Message msg){
            mvalue2++;
            txtcounter.setText(Integer.toString(mvalue2));
            try{
                Thread.sleep(50);
            }catch (InterruptedException e){
                ;
            }
            if(mvalue2<100){
                mHandler2.sendEmptyMessage(0);
            }
        }
    };

    Handler mHandler3 = new Handler(){
        public void handleMessage(Message msg){
            mvalue3++;
            txtcounter.setText(Integer.toString(mvalue3));
            try{
                Thread.sleep(50);
            }catch (InterruptedException e){
                ;
            }
            if(mvalue3<100 && mQuit == false){
                mProgressDialog.setProgress(mvalue3);
                mHandler3.sendEmptyMessage(0);
            }else{
                dismissDialog(0);
            }
        }
    };
}
