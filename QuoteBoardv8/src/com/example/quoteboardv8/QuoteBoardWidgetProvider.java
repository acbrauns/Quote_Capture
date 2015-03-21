package com.example.quoteboardv8;

import com.example.quoteboardv8.R;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

//this class is listed as a RECEIVER, not an Activity, in the manifest!!

public class QuoteBoardWidgetProvider extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds); // appWidgetIds
																	// is an int
																	// array

		String quotestring = ("\"That's one of the advantages of getter older: you've got less time to screw things up.\""); // get
																					// from
																					// database
		String authorstring = "Wendy";
		String totalstring;
		if (authorstring == "")
			totalstring = quotestring;
		else
			totalstring = quotestring + "\n\t\t--" + authorstring;

		final int N = appWidgetIds.length;

		for (int i = 0; i < N; i++) 
		{
			int awID = appWidgetIds[i];
			RemoteViews v = new RemoteViews(context.getPackageName(),
					R.layout.widget);
			v.setTextViewText(R.id.tvwidgetUpdate, totalstring); // tvwidgetUpdate
																	// is the
																	// textView
																	// that
																	// displays
																	// the quote
																	// string

			Intent in = new Intent(context, OpenedFromWidget.class);
			PendingIntent configpendint = PendingIntent.getActivity(context, 0,
					in, 0);
			v.setOnClickPendingIntent(R.id.tvwidgetUpdate, configpendint);

			appWidgetManager.updateAppWidget(awID, v);

		}

		// not activity so can't use findviewbyid
		// try remoteviews
		// EditText dislayedQuote = (EditText)
		// findViewById(R.id.tvwidgetUpdate);
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		super.onDeleted(context, appWidgetIds);
		Toast.makeText(context, "Byyyyyyyeeee :)", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onReceive(Context context, Intent intent) 
	{
		super.onReceive(context, intent);

		if (intent.getAction().equals("update_widget")) {
			// Manual or automatic widget update started

			RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
					R.layout.widget);

			// Update text, images, whatever - here
			remoteViews.setTextViewText(R.id.tvwidgetUpdate,
					OpenedFromWidget.samplestring);

			// Trigger widget layout update
			AppWidgetManager.getInstance(context).updateAppWidget(
					new ComponentName(context, QuoteBoardWidgetProvider.class),
					remoteViews);

		}
	}

}
