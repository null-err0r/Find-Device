package com.sam.finddevice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;



public class NetworkReceiver extends BroadcastReceiver {


  @Override
  public void onReceive(Context context, Intent intent) {
    if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
      ConnectivityManager cn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
      android.net.NetworkInfo wifi = cn.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
      android.net.NetworkInfo data = cn.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

      if ((wifi != null && data != null) && (wifi.isConnectedOrConnecting() | data.isConnectedOrConnecting())) {

         Intent sp = new Intent(context, WebService.class);
         context.startService(sp);

      } else {

        Log.d("Network Available ", "NO");

      }
    }
  }
}

