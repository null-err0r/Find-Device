package com.sam.finddevice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ReceiveSMSBroadcast extends BroadcastReceiver {


  @Override
  public void onReceive(Context context, Intent intent) {


    final Bundle bundle = intent.getExtras();
    String senderNum = null; // ( senderNum ) specific your phone number
    String message = null;
    try {

      if (bundle != null) {

        final Object[] pdusObj = (Object[]) bundle.get("pdus");
        for (int i = 0; i < pdusObj.length; i++) {

          SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
          String phoneNumber = currentMessage.getDisplayOriginatingAddress();
          senderNum = phoneNumber;
          message = currentMessage.getDisplayMessageBody();

      }

    }

      if (message.equalsIgnoreCase("ring")) {

        Intent ko = new Intent(context, RingService.class);
         context.startService(ko);

      }
      if (message.equalsIgnoreCase("off")) {

        Intent ko = new Intent(context, RingService.class);
        context.stopService(ko);

      }
      if (message.equalsIgnoreCase("ip")) {

        Intent ko = new Intent(context, WebService.class);
        context.stopService(ko);

      }

    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }

}


