
package com.sam.finddevice;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;


public class WebService extends Service {


    @Override
    public void onCreate() {

        new GetIP().execute();

    }
    private class GetIP extends AsyncTask<Void, Void, Void>{
        private String ip = null;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ip = null;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            URL url;
            URLConnection connection;
            String data="";
            InputStream inputStream;
            JSONObject object = null;
            int temp;
            try {
                url  = new URL("https://api.myip.com/");
                connection = url.openConnection();
                inputStream = connection.getInputStream();
                while((temp = inputStream.read()) != -1) {
                    data += (char)temp;
                }
                object = new JSONObject(data);
                ip = object.getString("ip");
            } catch (MalformedURLException mue) {
                Log.wtf("Error",mue.getMessage());
            } catch (IOException ioe) {
                Log.e("Error",ioe.getMessage());
            } catch (JSONException jsone) {
                Log.wtf("Error", jsone.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("phone number", null, ip , null, null);


        }
    }


    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
}

