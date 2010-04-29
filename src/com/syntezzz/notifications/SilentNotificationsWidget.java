package com.syntezzz.notifications;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Created by IntelliJ IDEA.
 * User: S.Chernov
 * Date: 29.04.2010
 * Time: 5:44:07
 * To change this template use File | Settings | File Templates.
 */
public class SilentNotificationsWidget extends AppWidgetProvider {
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Log.d("fire", "onReceive");
        if (intent.getAction()==null) {
			context.startService(new Intent(context, WidgetService.class));
		}
		else {

            Log.d("fire", intent.getAction());
            if (intent.getAction().equals("com.syntezzz.notifications.CLICK")){
                Log.d("fire", "startingService");
                context.startService(new Intent(context, WidgetService.class));
            }

            if (intent.getAction().equals("com.syntezzz.notifications.SETIMAGE")){
                Log.d("fire", "setImage");
                Log.d("fire", String.format("enabled=%s", intent.getData().getQueryParameter("enabled")));
            }
			super.onReceive(context, intent);
		}

//        String action = intent.getAction();
//        if (AppWidgetManager.ACTION_APPWIDGET_UPDATE.equals(action))
//        {
//
//            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.silentwidget);
//
//            views.setTextViewText(R.id.widgetText, );
//
////            Intent AlarmClockIntent = new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER).setComponent(new ComponentName("com.syntezzz.notifications", "com.syntezzz.notifications.MainActivity"));
////            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, AlarmClockIntent, 0);
////            views.setOnClickPendingIntent(R.id.silentNotificationsWidget, pendingIntent);
////
////            AppWidgetManager.getInstance(context).updateAppWidget(intent.getIntArrayExtra(SAppWidgetManager.EXTRA_APPWIDGET_ID), views);
//        }
    }

    @Override
	public void onUpdate(Context context, AppWidgetManager mgr, int[] appWidgetIds) {
        Log.d("fire", "onUpdate");

        int max = appWidgetIds.length;

        for (int i=0; i<max; i++ ){
            RemoteViews views=new RemoteViews(context.getPackageName(), R.layout.silentwidget);
            Intent clickintent = new Intent("com.syntezzz.notifications.CLICK");
            PendingIntent pendingIntentClick= PendingIntent.getBroadcast(context,0, clickintent, 0);
            views.setOnClickPendingIntent(R.id.silentButton, pendingIntentClick);
            mgr.updateAppWidget(appWidgetIds[i], views);
        }


	}



        

    


}
