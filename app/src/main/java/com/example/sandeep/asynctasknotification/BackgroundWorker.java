package com.example.sandeep.asynctasknotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Parcel;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by sandeep on 01-02-2017.
 */

public class BackgroundWorker extends AsyncTask<Void,Integer,Integer>
{
    public String OPEN_GALLERY = "";
    private static final int IMAGE = 1;
    MediaScannerConnection mediascanner;
    PendingIntent gallery;
    public NotificationManager notificationManager;
    //Notification.Builder builder;
    public NotificationCompat.Builder builder;
    public  Uri uri;
    int id = 1;
    Uri ImageUri;
    String name = "";
    String path="";
    File file ;
    File output;
    InputStream inputStream ;
    FileOutputStream fos;
    String urlpath = "https://www.planwallpaper.com/static/images/HD-Wallpaer.jpg";
    Context context;
    BackgroundWorker(Context context)
    {
        this.context = context;
    }
    @Override
    protected Integer doInBackground(Void... params)
    {
        /*File file = null;
        File output;
        InputStream inputStream = null;
        FileOutputStream fos;*/
        try {
            URL url = new URL(urlpath);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection)url.openConnection();
            inputStream = httpsURLConnection.getInputStream();
            file = new File(urlpath);
            name = file.getName();
            output = new File(Environment.getExternalStorageDirectory(),name);
            fos = new FileOutputStream(output.getPath());
            int filelength = httpsURLConnection.getContentLength();
            path = output.getAbsolutePath();
            byte bytes[] = new byte[filelength];
            int total = 0;
            int count = 0;
            while ((count = inputStream.read(bytes , 0, filelength)) != -1)
            //while ((count = inputStream.read(bytes)) != -1)
                {
                    total = total + count;
                    //if (filelength > 0)
                    //{
                       publishProgress((total*100) / filelength);
                    //}
                    //fos.write(bytes , 0 , filelength);
                    fos.write(bytes , 0 , count);

                }
            inputStream.close();
            fos.close();
            }

        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }




    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(context);
        builder.setProgress(100,0,false);
        builder.setContentTitle("Download Picture");
        builder.setContentText("Downloading in progress");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setOngoing(true);

      notificationManager.notify(id, builder.build());
        Toast.makeText(context, "Downloading statred", Toast.LENGTH_SHORT).show();

        //  MainActivity.builder.setProgress(100, 0, false);
       // Maiger.notify(MainActivity.id, MainActivity.builder.build());
    }

    @Override
    protected void onPostExecute(Integer aVoid) {
        super.onPostExecute(aVoid);
        builder.setProgress(0,0,false);
        //builder.setContentText("Download Complete");

        builder.setOngoing(false);
        //notificationManager.notify(id, builder.build());

        MediaScannerConnection.scanFile(context, new String[]{path}, null, new MediaScannerConnection.OnScanCompletedListener() {
            @Override
            public void onScanCompleted(String path, Uri uri) {

            }
        });

        //Intent intent = new Intent(Intent.ACTION_VIEW);
        //Intent intent = new Intent();
       // intent.setType("image/*");
        //intent.setAction(Intent.ACTION_PICK);
      //  Intent intent = new Intent(context,MainActivity.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

            //ImageUri = Uri.parse(output.getPath());



    //    PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);
        /*TaskStackBuilder task = TaskStackBuilder.create(context);
        task.addParentStack(BackgroundWorker.class);
        task.addNextIntent(intent);
        task.getPendingIntent(0,0);*/
       // PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);
        //builder.setContentIntent(pendingIntent);
        //builder.addAction(1,"",pendingIntent);

        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("check",true);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
               intent , PendingIntent.FLAG_UPDATE_CURRENT);


        builder.setContentIntent(contentIntent);


        notificationManager.notify(id,builder.build());
        //Intent sendintent = new Intent();
        //sendintent.putExtra("check",true);
       // sendintent.setAction(OPEN_GALLERY);





        //mediascanner.scanFile(name,"image/*");

        //Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //PendingIntent image = PendingIntent.getActivities(context,0,intent,PendingIntent.FLAG_ONE_SHOT);
       // Intent intent = new Intent(Intent.ACTION_VIEW,ImageUri);
        //ImageUri = "content://media/external/images/media/1210";
        //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromFile(name ));

       // Intent intent = new Intent();
        //intent.setAction(Intent.ACTION_GET_CONTENT);
        //intent.setType("image");

        //builder.addAction(1,"image",intent);
       // Intent openpic = new Intent(Intent.ACTION_CAMERA_BUTTON);
        //gallery = new PendingIntent(openpic);
       /* MainActivity.builder.setContentText("Download completed");
        MainActivity.builder.setProgress(0, 0 , false);
        MainActivity.notificationManager.notify(MainActivity.id, MainActivity.builder.build());

    */
    }

    @Override
    protected void onProgressUpdate(Integer... values)
    {
        builder.setProgress(100, values[0], false);
        int  i = values[0];
        //builder.setContentText("Downloading :"+String.valueOf(values)+"/100");
        builder.setContentText("Downloading :"+i+"%"+"/100");
        notificationManager.notify(id,builder.build());
       // Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(name));

        //builder.addAction(pendingIntent);
        //builder.setContentIntent(pendingIntent);

       /*Intent intent = new Intent(Intent.ACTION_VIEW,Uri.fromFile((Environment.getExternalStorageDirectory().getAbsoluteFile())));
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_ONE_SHOT);
        builder.addAction(1,"",pendingIntent);*/
        //Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(name));
        //Intent intent = new Intent(Intent.ACTION_VIEW,ImageUri);
        //ImageUri = "Path:/storage/emulated/0/HD-Wallpaper.jpg";

      /*  MainActivity.builder.setProgress(100, values[0], false);
        MainActivity.notificationManager.notify(MainActivity.id, MainActivity.builder.build());
        super.onProgressUpdate(values);
    */}




}
