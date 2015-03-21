package com.example.quoteboardv8;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database 

{
	public static final String KEY_ROWID = "_id";
	public static final String KEY_QUOTE = "quote";
	public static final String KEY_AUTHOR = "quote_author";
	
	private static final String DATABASE_NAME = "QuoteDB";
	private static final String DATABASE_TABLE = "quoteTable";
	private static final int DATABASE_VERSION = 1;
	
	private QuoteDBHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	
	private static class QuoteDBHelper extends SQLiteOpenHelper
	{

		public QuoteDBHelper(Context context)
		{
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) 
		{
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
					KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_QUOTE + " TEXT NOT NULL, " +
					KEY_AUTHOR + " TEXT NOT NULL);" //could also be referenced as an integer, but is referenced as a text or string
					
					);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
		{
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
		
	}
	
	public Database(Context c)
	{
		ourContext = c;
	}
	
	public Database open()
	{
		ourHelper = new QuoteDBHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void close()
	{
		ourHelper.close();
	}

	
	public long createEntry(String quote, String author)
	{
		ContentValues cv = new ContentValues();
		cv.put(KEY_QUOTE, quote);
		cv.put(KEY_AUTHOR, author);
		return ourDatabase.insert(DATABASE_TABLE, null, cv); 
	}
	
	public String getData()
	{
		String [] columns = new String [] {KEY_ROWID, KEY_QUOTE, KEY_AUTHOR};
		//we read info through a cursor
		Cursor c = ourDatabase.query
				(DATABASE_TABLE, columns, null, null, null, null, null);
		String result = "";
		
		//i refers to index 
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iQuote = c.getColumnIndex(KEY_QUOTE);
		int iAuthor = c.getColumnIndex(KEY_AUTHOR);
		
		//cursor starts at first item; stops after last;
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		{
							//gets id of row
			result = result + c.getString(iRow) + " "+ c.getString(iQuote) + " " 
					+ c.getString(iAuthor) + "\n";
		}
		
		
		return result;
	}
	
	
	public String getQuote(long l)
	{
		String [] columns = new String [] {KEY_ROWID, KEY_QUOTE, KEY_AUTHOR};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, 
				KEY_ROWID + "=" + l, null, null, null, null);
		if (c != null)
		{
			c.moveToFirst();
			String name = c.getString(1); //cursor.getString(int index of row)
			//the index is 1 b/c 0= rowID, 1=name, 2=hotness
			return name;
		}
		
		return null;
	}
	
	public String getAuthor(long l)
	{
		String [] columns = new String [] {KEY_ROWID, KEY_QUOTE, KEY_AUTHOR};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, 
				KEY_ROWID + "=" + l, null, null, null, null);
		if (c != null)
		{
			c.moveToFirst();
			String author = c.getString(2); //cursor.getString(int index of row)
			//the index is 2 b/c 0= rowID, 1=name, 2=hotness
			return author;
		}
		
		return null;
	}
	
	public void updateEntry(long lRow, String quote, String author)
	{
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(KEY_QUOTE, quote);
		cvUpdate.put(KEY_AUTHOR, author);
		ourDatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + "=" + lRow, null);
		
	}

	public void deleteEntry(long lRow1)
	{
		ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + lRow1, null);
	}
}