package com.example.quoteboardv8;

import java.util.Random;

import com.example.quoteboardv8.R;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

public class MyService extends Service  
{  
	Context context;
    @Override  
    public void onCreate()  
    {  
        super.onCreate();  
    }  
  
    @Override  
    public int onStartCommand(Intent intent, int flags, int startId)  
    {  
        buildUpdate();  
  
        return super.onStartCommand(intent, flags, startId);  
    }  
  
    private void buildUpdate()  
    {   int number = (new Random().nextInt(100));
    	String randstring = String.valueOf(number);
  
        RemoteViews view = new RemoteViews(getPackageName(), R.layout.widget);  
  
        view.setTextViewText(R.id.tvwidgetUpdate, randstring);  
  
        // Push update for this widget to the home screen  
        ComponentName thisWidget = new ComponentName(this, QuoteBoardWidgetProvider.class);  
        AppWidgetManager manager = AppWidgetManager.getInstance(this);  
        manager.updateAppWidget(thisWidget, view);  
    }  
  
    @Override  
    public IBinder onBind(Intent intent)  
    {  
        return null;  
    }  
}  