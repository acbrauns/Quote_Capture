package com.copycat.listviewtrial1;


import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

public class PRCharLab 
{
	
	private static PRCharLab sPRCharLab;
	
	private Context mAppContext;
	
	private ArrayList<PRChar> mPRChars;
	
	private ArrayList<String> mNameArray;
	private ArrayList<String> mTaglineArray;
	private ArrayList<Integer> mPhotoArray;
	private ArrayList<Integer> mThumbnailPhotoArray;
	
	
	
	private PRCharLab(Context context)
	{
		mAppContext = context;
		mPRChars = new ArrayList<PRChar>();
		initializeArrays();
		
		for (int i = 0; i < mNameArray.size(); i++)
		{
			PRChar c  = new PRChar(); //just give constructor a uuid
			c.setName(mNameArray.get(i)); 
			c.setTagline(mTaglineArray.get(i));
			c.setPhoto(mPhotoArray.get(i));
			c.setThumbnailPhoto(mThumbnailPhotoArray.get(i));
			mPRChars.add(c);
		}
	}
	public static PRCharLab get(Context c) 
	{
		if (sPRCharLab == null)
			sPRCharLab = new PRCharLab(c.getApplicationContext());
		
		return sPRCharLab;
	}
	
	
	public ArrayList<PRChar> getPRChars()
	{
		return mPRChars;
	}
	
	public PRChar getPRChars(UUID id)
	{
		for (PRChar c: mPRChars)
		{
			if (c.getCharId().equals(id))
				return c;
		}
		
		return null;
	}
	


	private void initializeArrays() 
	{
		mNameArray = new ArrayList<String>();
		mTaglineArray = new ArrayList<String>();
		mPhotoArray = new ArrayList<Integer>();
		mThumbnailPhotoArray = new ArrayList<Integer>();
		
		mNameArray.add("Leslie Knope");
		mPhotoArray.add(R.drawable.leslie);
		mTaglineArray.add("\"Crap on the cob!\"");
		mThumbnailPhotoArray.add(R.drawable.ic_leslie);
		
		mNameArray.add("Ben Wyatt");
		mPhotoArray.add(R.drawable.ben);
		mTaglineArray.add("\"He's a lawyer, I'm an accountant...I mean, obviously, accountants are a little more bad boy, but, uh, there's a respect there.\"");
		mThumbnailPhotoArray.add(R.drawable.ic_ben);
		
		mNameArray.add("Ron Swanson");
		mPhotoArray.add(R.drawable.ron);
		mTaglineArray.add("\"You had me at 'meat tornado'.\"");
		mThumbnailPhotoArray.add(R.drawable.ic_ron);
		
		mNameArray.add("April Ludgate");
		mPhotoArray.add(R.drawable.april);
		mTaglineArray.add("\"I hate talking...to people...about things.\"");
		mThumbnailPhotoArray.add(R.drawable.ic_april);
		
		mNameArray.add("Andy Dwyer");
		mPhotoArray.add(R.drawable.andy);
		mTaglineArray.add("\"Butter is my favorite food!\"");
		mThumbnailPhotoArray.add(R.drawable.ic_andy);
		
		mNameArray.add("Tom Haverford");
		mPhotoArray.add(R.drawable.tom2);
		mTaglineArray.add("\"I'm like an elephant, okay? " +
				"If I walk into a room, it's like, okay, he's in there.\"");
		mThumbnailPhotoArray.add(R.drawable.ic_tom);
		
		mNameArray.add("Donna Meagle");
		mPhotoArray.add(R.drawable.donna);
		mTaglineArray.add("\"Do I look like I drink water?\"");
		mThumbnailPhotoArray.add(R.drawable.ic_donna);
		
		mNameArray.add("Chris Traeger");
		mPhotoArray.add(R.drawable.chris);
		mTaglineArray.add("\"I can LIT'RALLY make anything sound positive.\"");
		mThumbnailPhotoArray.add(R.drawable.ic_chris);
		
		mNameArray.add("Ann Perkins");
		mPhotoArray.add(R.drawable.ann4);
		mTaglineArray.add("\"Well...I salsa your face.\"");
		mThumbnailPhotoArray.add(R.drawable.ic_ann);
		
		mNameArray.add("Garry Gergich");
		mPhotoArray.add(R.drawable.jerry);
		mTaglineArray.add("\"Aw jeez...\"");
		mThumbnailPhotoArray.add(R.drawable.ic_jerry);
		
	}
	
}
