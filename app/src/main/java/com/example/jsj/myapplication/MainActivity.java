package com.example.jsj.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //btnOnclickListner onclickListner = new btnOnclickListner();

        Button btn19 = (Button)findViewById(R.id.btn19);
        btn19.setOnClickListener(onClickListener);
        findViewById(R.id.scoreapp).setOnClickListener(onClickListener);
    }
    Button.OnClickListener onClickListener = new Button.OnClickListener(){
        @Override
        public void onClick(View view){
            switch (view.getId()){
                case R.id.btn19 :
                    Log.d("aa","btn19");
                    alertShow("btn19");
                    break;
                case R.id.scoreapp :
                    //alertShow("btn19");
                    Log.d("aa","scoreapp");
                    scoreAppStart();
                    break;
            }
        }
    };
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void alertShow(String item) {

        System.out.println(item);
        new AlertDialog.Builder(this)
                .setTitle("Chap19중 선택하세요.")
                .setItems(R.array.chap19,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String[] item = getResources().getStringArray(R.array.chap19);
                                System.out.println(item[which]);
                                Intent intent = null;
                                if(item[which].equals("chap19-1")) intent = new Intent (getApplicationContext(), Activity19a1.class);
                                else if(item[which].equals("chap19-2")) intent = new Intent (getApplicationContext(), Activity19a2.class);
                                else if(item[which].equals("chap19-3")) intent = new Intent (getApplicationContext(), Activity19a3.class);
                                else if(item[which].equals("chap19-4")) intent = new Intent (getApplicationContext(), Activity19a4.class);
                                else if(item[which].equals("chap19-5")) intent = new Intent (getApplicationContext(), Activity19a5.class);
                                else if(item[which].equals("chap19-6")) intent = new Intent (getApplicationContext(), Activity19a6.class);
                                else if(item[which].equals("chap19-7")) intent = new Intent (getApplicationContext(), Activity19a7.class);
                                else if(item[which].equals("chap19-8")) intent = new Intent (getApplicationContext(), Activity19a8.class);
                                else if(item[which].equals("chap19-9")) intent = new Intent (getApplicationContext(), Activity19a9.class);
                                else if(item[which].equals("chap19-10")) intent = new Intent (getApplicationContext(), Activity19a10.class);
                                else if(item[which].equals("chap19-11")) intent = new Intent (getApplicationContext(), Activity19a11.class);
                                else if(item[which].equals("chap19-12")) intent = new Intent (getApplicationContext(), Activity19a12.class);
                                startActivity(intent);
                            }
                        })
                .setNegativeButton("취소", null).show();
        }

    public void scoreAppStart(){
        Log.d("aa","aa");
        Intent intent = new Intent (getApplicationContext(), ActivityScore.class);
        startActivity(intent);
    }

}
