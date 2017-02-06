package com.example.sandeep.asynctasknotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
   /*public static NotificationManager notificationManager;
    //Notification.Builder builder;
    public static NotificationCompat.Builder builder;
   */ Button button;
    public static int id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);

        Intent intent = getIntent();
        //intent.getAction();
        boolean b = intent.getBooleanExtra("check",false);
        if (b)
        {
            //intent = new Intent(Intent.ACTION_VIEW);
            Intent ointent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(ointent,1);
            finish();
        }
       /* button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                BackgroundWorker backgroundWorker = new BackgroundWorker(MainActivity.this);


            }
        });*/
    }

    @Override
    public void onClick(View v)
    {
        /*notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
      //  builder = new Notification().
       // builder = new NotificationCompat.Builder(MainActivity.this);
      //  builder = new Notification.Action.Builder(MainActivity.this);
        builder = new NotificationCompat.Builder(MainActivity.this);
        builder.setContentTitle("Download");
        builder.setContentText("download in progress");
       */

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute();
       /* NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);

        builder.setContentText("dsdsds");
        notificationManager.notify(12,builder.build());*/

        /*Intent intent = getIntent();
        //intent.getAction();
        boolean b = intent.getBooleanExtra("check",false);
        if (b == true)
        {
            //intent = new Intent(Intent.ACTION_VIEW);
            Intent ointent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(ointent,1);
        }*/
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
