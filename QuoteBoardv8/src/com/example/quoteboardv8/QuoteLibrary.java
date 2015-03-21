package com.example.quoteboardv8;

import com.example.quoteboardv8.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class QuoteLibrary extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quote_library);
		TextView tv = (TextView) findViewById(R.id.tvSQLinfo);
		Database info = new Database(this); // THIS is the context of this
											// SQLView class

		info.open(); // info is the Database object
		String data = info.getData(); // getData is a single string defined in
										// Database
		info.close();
		tv.setText(data);

	}

}

