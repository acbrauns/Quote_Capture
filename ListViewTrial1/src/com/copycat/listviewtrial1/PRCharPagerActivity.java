package com.copycat.listviewtrial1;



import java.util.ArrayList;
import java.util.UUID;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

public class PRCharPagerActivity extends FragmentActivity
{
	private ViewPager mViewPager;
	private ArrayList<PRChar> mPRChars;

	@Override
	protected void onCreate(Bundle arg0) 
	{
		super.onCreate(arg0);
		
		mViewPager = new ViewPager(this);
		mViewPager.setId(R.id.viewPager); //this item is in res/values/ids
		
		UUID prcharId =(UUID)getIntent().getSerializableExtra
				(PRCharFragment.EXTRA_PRCHAR_ID);
		
		setContentView(mViewPager);
		
		mPRChars = PRCharLab.get(this).getPRChars();
		
		
		FragmentManager fm = getSupportFragmentManager();
		mViewPager.setAdapter(new FragmentStatePagerAdapter(fm){

			@Override
			public Fragment getItem(int position) 
			{
				PRChar prchar = mPRChars.get(position);
				return PRCharFragment.newInstance(prchar.getCharId());
			}

			@Override
			public int getCount() {
				return mPRChars.size();
			}
			
		});
		
		mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) 
			{
				// TODO Auto-generated method stub
				PRChar prchar = mPRChars.get(position);
				if(prchar.getName()!= null)
				{
					setTitle(prchar.getName());
				}
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		
		
		for (int i = 0; i < mPRChars.size(); i++)
		{
			if (mPRChars.get(i).getCharId().equals(prcharId))
			{
				mViewPager.setCurrentItem(i);
				setTitle(mPRChars.get(i).getName());
				break; //once if finds the item you can break out of for loop
			}
		}
		
	}
	
}
