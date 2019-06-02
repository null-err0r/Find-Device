package com.sam.finddevice;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;

public class RingService extends Service {

     MediaPlayer  mpintro;

    @Override
    public void onCreate () {

      AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

       if(am.getRingerMode() == AudioManager.RINGER_MODE_NORMAL){

         am.setStreamVolume(AudioManager.STREAM_RING, 7, AudioManager.FLAG_ALLOW_RINGER_MODES|AudioManager.FLAG_PLAY_SOUND);

         am.setStreamVolume(AudioManager.STREAM_RING,am.getStreamMaxVolume(AudioManager.STREAM_RING),0);

        mpintro = new MediaPlayer();
        mpintro = MediaPlayer.create(this, R.raw.audio);
        mpintro.setLooping(true);
        mpintro.start();

       } else if (am.getRingerMode() == AudioManager.RINGER_MODE_SILENT && am.getRingerMode() == AudioManager.RINGER_MODE_VIBRATE) {
        am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);

        am.setStreamVolume(AudioManager.STREAM_RING, 7, AudioManager.FLAG_ALLOW_RINGER_MODES|AudioManager.FLAG_PLAY_SOUND);

        am.setStreamVolume(AudioManager.STREAM_RING,am.getStreamMaxVolume(AudioManager.STREAM_RING),0);

        mpintro = new MediaPlayer();
        mpintro = MediaPlayer.create(this, R.raw.audio);
        mpintro.setLooping(true);
        mpintro.start();

     }
 }

  @Override
    public IBinder onBind(Intent arg0) {

      return null;
    }

  }
