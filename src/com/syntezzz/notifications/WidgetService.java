package com.syntezzz.notifications;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.TimerTask;

public class WidgetService extends IntentService {
    private Handler handler;

    public WidgetService() {
        super("SilentNotificationsWidget$ToggleNotifications");
    }

    @Override
    public void onCreate() {
        Log.d("fire", "serviceCreated");
        handler = new Handler(Looper.getMainLooper());
        
        super.onCreate();
    }

    @Override
    public void onHandleIntent(Intent intent) {
        Log.d("fire", "firedHadle");
        ComponentName me = new ComponentName(this, SilentNotificationsWidget.class);
        AppWidgetManager mgr = AppWidgetManager.getInstance(this);

        mgr.updateAppWidget(me, buildUpdate(this));
    }



    private RemoteViews buildUpdate(final Context context) {
        RemoteViews updateViews=new RemoteViews(context.getPackageName(), R.layout.silentwidget);


        Silencer silencer = new Silencer(context);

        final boolean isSoundEnabled = silencer.ToggleSilentNotifications();

        if (isSoundEnabled) {
            updateViews.setImageViewResource(R.id.silentButton, R.drawable.on);
        }
        else {
            updateViews.setImageViewResource(R.id.silentButton, R.drawable.off);
        }





        

//            String user=prefs.getString("user", null);
//			String password=prefs.getString("password", null);
//
//			if (user!=null && password!=null) {
//				Twitter client=new Twitter(user, password);
//				List<Twitter.Status> timeline=client.getFriendsTimeline();
//
//				if (timeline.size()>0) {
//					Twitter.Status s=timeline.get(0);
//
//					updateViews.setTextViewText(R.id.friend,
//																			s.user.screenName);
//					updateViews.setTextViewText(R.id.status,
//																			s.text);
//
//					Intent i=new Intent(this, TwitterWidget.class);
//					PendingIntent pi=PendingIntent.getBroadcast(context,
//																											0	, i,
//																											0);
//
//					updateViews.setOnClickPendingIntent(R.id.refresh,
//																							pi);
//
//					i=new Intent(this, TWPrefs.class);
//					pi=PendingIntent.getActivity(context, 0	, i, 0);
//					updateViews.setOnClickPendingIntent(R.id.configure,
//																							pi);
//				}
//			}

        return(updateViews);
    }
}
