package com.example.jsj.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Activity19a10 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity19a10);
        /*Button button = (Button)findViewById(R.id.upload);
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertShow();
            }
        });

        Button button2 = (Button)findViewById(R.id.upload2);
        button2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertShow2();
            }
        });*/
        findViewById(R.id.upload).setOnClickListener(mClickListener);
        findViewById(R.id.upload2).setOnClickListener(mClickListener);
        findViewById(R.id.upload3).setOnClickListener(mClickListener);
    }
    Handler mHandler = new Handler(){
        public void handleMessage(Message msg){
            if(msg.what==0){
                doUpload();
            }
        }
    };
    void doUpload(){
        for(int i=0;i<20;i++){
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                ;
            }
            Toast.makeText(this,"업로드를 완료했습니다.",Toast.LENGTH_SHORT).show();
        }
    }
    void doUpload2(){
        for(int i=0;i<100;i++){
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                ;
            }
            Toast.makeText(this,"업로드를 완료했습니다.",Toast.LENGTH_SHORT).show();
        }
    }
    public void alertShow() {

        new AlertDialog.Builder(this).setTitle("질문").setMessage("1 업로드 하시겠습니까?")
                .setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        doUpload();
                        //mHandler.sendEmptyMessageDelayed(0,100);
                    }
                }).setNegativeButton("아니오",null).show();
    }
    public void alertShow2() {

        new AlertDialog.Builder(this).setTitle("질문").setMessage("2 업로드 하시겠습니까?")
                .setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println("alertShow2");
                        mHandler.sendEmptyMessageDelayed(0,10);
                    }
                }).setNegativeButton("아니오",null).show();
    }

    public void alertShow3() {

        new AlertDialog.Builder(this).setTitle("질문").setMessage("3 업로드 하시겠습니까?")
                .setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println("alertShow2");
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                doUpload();
                            }
                        },10);
                    }
                }).setNegativeButton("아니오",null).show();
    }
    Button.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            //이곳에 버튼 클릭시 일어날 일을 적습니다.
            System.out.println(v.getId());
            switch (v.getId()){
                case R.id.upload:
                    alertShow();
                    break;
                case R.id.upload2:
                    alertShow2();
                    break;
                case R.id.upload3:
                    alertShow3();
                    break;
                case R.id.upload4:
                    doUpload2();
                    break;
            }
        }
    };
}
