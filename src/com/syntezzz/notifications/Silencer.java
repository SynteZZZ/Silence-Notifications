package com.syntezzz.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.media.AudioManager;
import android.provider.Settings;
import android.util.Log;

/**
 * Created by IntelliJ IDEA.
 * User: S.Chernov
 * Date: 29.04.2010
 * Time: 7:56:31
 * To change this template use File | Settings | File Templates.
 */
public class Silencer {

    private AudioManager audioManager;
    private Context context;

    public Silencer(Context context) {
        this.context = context;
        this.audioManager = (AudioManager) this.context.getSystemService(Context.AUDIO_SERVICE);
    }

    public boolean ToggleSilentNotifications() {
        int origIsTogether = 0;
        try {
            origIsTogether = Settings.System.getInt(context.getContentResolver(), "notifications_use_ring_volume");
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }



        if (origIsTogether == 1)
        {
            Settings.System.putInt(context.getContentResolver(), "notifications_use_ring_volume", 0);
            audioManager.setStreamVolume(AudioManager.STREAM_NOTIFICATION, 0, 0);
        }
        else
        {
            int ringVolume = audioManager.getStreamVolume(AudioManager.STREAM_RING);
            Log.d("fire", String.format("ringVolume%d", ringVolume));
            audioManager.setStreamVolume(AudioManager.STREAM_NOTIFICATION, ringVolume, 0);
            
            Settings.System.putInt(context.getContentResolver(), "notifications_use_ring_volume", 1);
        }



        if (origIsTogether == 1)
        {
            return false;
        }
        else
        {
            Log.d("fire", "beforeNotify");
            final NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            final Notification n = new Notification(0, "test", 0);
            n.defaults = n.defaults | Notification.DEFAULT_SOUND;
            notificationManager.notify(0, n);
            Log.d("fire", "afterNotify");

            return true;
        }
    }
}
