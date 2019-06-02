package com.sam.finddevice;


import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

public class MainActivity extends Activity {
    ArrayList<String> permissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int sendSms = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS);

            permissions = new ArrayList();

            if (sendSms == PackageManager.PERMISSION_DENIED) {
                permissions.add(Manifest.permission.SEND_SMS);
            }

            if(permissions.size() > 0) {
                ActivityCompat.requestPermissions(MainActivity.this, permissions.toArray(new String[permissions.size()]), 1);

            }else{

                // ....
            }
        }

  }
}
