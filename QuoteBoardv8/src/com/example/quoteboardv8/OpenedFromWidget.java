package com.example.quoteboardv8;

import java.util.Calendar;

import com.example.quoteboardv8.R;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//LATER: ADD ALL THE TRY/CATCH BLOCKS TO THE ONCLICK METHOD
//AS IN THE VIDEO (SQLITE? SEE NB APP)

public class OpenedFromWidget extends Activity implements OnClickListener {
	Button sqlAdd, sqlView, sqlModify, sqlGetInfo, sqlDelete;
	Button sqlSetWidget;
	EditText sqlQuote, sqlAuthor, sqlRow;
	Context c;
	int awID;

	AppWidgetManager awm;

	public static String samplestring = "samplestring";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_quotes_screen);

		sqlSetWidget = (Button) findViewById(R.id.bSetWidgetText);
		sqlSetWidget.setOnClickListener(this);

		sqlAdd = (Button) findViewById(R.id.bAdd);
		sqlView = (Button) findViewById(R.id.bView);
		sqlModify = (Button) findViewById(R.id.bModify);
		sqlGetInfo = (Button) findViewById(R.id.bGetInfo);
		sqlDelete = (Button) findViewById(R.id.bDelete);

		sqlQuote = (EditText) findViewById(R.id.etQuote);
		sqlAuthor = (EditText) findViewById(R.id.etAuthor);
		sqlRow = (EditText) findViewById(R.id.etRowID);

		sqlAdd.setOnClickListener(this);
		sqlView.setOnClickListener(this);
		sqlModify.setOnClickListener(this);
		sqlGetInfo.setOnClickListener(this);
		sqlDelete.setOnClickListener(this);

		c = OpenedFromWidget.this;
		awm = AppWidgetManager.getInstance(c);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bAdd:
			boolean didItWork = true;
			try {
				String quote = sqlQuote.getText().toString(); // you are getting
																// the text from
																// the edittext
																// sqlQuote
				String author = sqlAuthor.getText().toString();

				Database entry = new Database(OpenedFromWidget.this);
				entry.open();
				entry.createEntry(quote, author);
				// only took the stuff from the try block. Didn't use catch,
				// boolean, or finally.
				// also code modifications because of changing constructor and
				// open and close methods of Database (HotOrNot) class
				entry.close();
			} catch (Exception e) {
				didItWork = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Error");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			} finally {
				if (didItWork) {
					Dialog d = new Dialog(this);
					d.setTitle("Success");
					TextView tv = new TextView(this);
					tv.setText("Success");
					d.setContentView(tv);
					d.show();
				}
			}

			break;
		case R.id.bView:
			Intent i = new Intent("com.example.quoteboardv8.QUOTELIBRARY");
			startActivity(i);
			break;

		case R.id.bModify:
			String sRow = sqlRow.getText().toString();
			long lRow = Long.parseLong(sRow); // this long is the row ID
			String mQuote = sqlQuote.getText().toString();
			String mAuthor = sqlQuote.getText().toString();

			Database ex = new Database(this);
			ex.open();
			ex.updateEntry(lRow, mQuote, mAuthor);
			ex.close();
			break;

		case R.id.bGetInfo:
			String s = sqlRow.getText().toString();
			// below: convert string into long type variable
			long l = Long.parseLong(s); // this long is the row ID
			Database dat = new Database(this);
			dat.open();
			String returnedQuote = dat.getQuote(l);
			String returnedAuthor = dat.getAuthor(l);
			dat.close();
			sqlQuote.setText(returnedQuote);
			sqlAuthor.setText(returnedAuthor);
			break;

		case R.id.bDelete:
			String sRow1 = sqlRow.getText().toString();
			long lRow1 = Long.parseLong(sRow1);
			Database ex1 = new Database(this);
			ex1.open();
			ex1.deleteEntry(lRow1);
			ex1.close();

			sqlRow.setText("");
			sqlQuote.setText("");
			sqlAuthor.setText("");

			break;
		case R.id.bSetWidgetText:
			String str = sqlRow.getText().toString();
			// below: convert string into long type variable
			long lng = Long.parseLong(str); // this long is the row ID
			Database dta = new Database(this);
			dta.open();
			String rtquote = dta.getQuote(lng);
			String rtauthor = dta.getAuthor(lng);
			dta.close();
			samplestring = rtquote + "\n" + rtauthor;

			Intent updateWidget = new Intent(c, QuoteBoardWidgetProvider.class); // Widget.class
																					// is
																					// your
																					// widget
																					// class
			updateWidget.setAction("update_widget");
			PendingIntent pending = PendingIntent.getBroadcast(c, 0,
					updateWidget, PendingIntent.FLAG_CANCEL_CURRENT);
			try {
				pending.send();
			} catch (CanceledException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.bStartTimer:
			Calendar cur_cal = Calendar.getInstance();
			cur_cal.setTimeInMillis(System.currentTimeMillis());
			cur_cal.add(Calendar.SECOND, 50);
			
			Intent intent = new Intent(OpenedFromWidget.this, MyService.class);
			PendingIntent pintent = PendingIntent.getService(OpenedFromWidget.this, 0, intent, 0);
			AlarmManager alarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
			alarm.setRepeating(AlarmManager.RTC_WAKEUP, cur_cal.getTimeInMillis(), 30*1000, pintent);
			
			Toast.makeText(this, "ServiceClass.onCreate()", Toast.LENGTH_LONG)
					.show();
			break;

		}

	}
}
