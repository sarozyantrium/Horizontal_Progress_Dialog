package com.example.horizontalprogressdialog;

import static android.os.SystemClock.sleep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity
{
    Button btn_click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_click=(Button) findViewById(R.id.btn_click);
    }

    public void horizontal_progress_dialog(View view)
    {
        ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Horizontal progress bar");
        progressDialog.setMessage("This is Horizontal progress dialog");
        progressDialog.setCancelable(false);

        //THIS IS TO SET PROGRESS DIALOG AS HORIZONTAL SURFACE
        progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
        progressDialog.show();

        //to show increment in progress dialog we need handler
        Handler handler=new Handler()
        {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);

                progressDialog.incrementProgressBy(1);
            }
        };

        //to show time WE USE THREAD

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(progressDialog.getProgress()<=100)
                    {
                        sleep(100);
                        handler.sendMessage(handler.obtainMessage());

                        if(progressDialog.getProgress()==100)
                        {
                            progressDialog.dismiss();
                        }
                    }
                }

                catch (Exception e)
                {
                    e.printStackTrace();
                }


            }
        });thread.start();



    }
}