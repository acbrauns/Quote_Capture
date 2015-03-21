package com.copycat.listviewtrial1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public abstract class SingleFragmentActivity extends FragmentActivity
{
	//below: this is an abstract method that will be implemented by subclasses
		//will always return a Fragment
		protected abstract Fragment createFragment();

		@Override
		public void onCreate(Bundle savedInstanceState) 
		{
			super.onCreate(savedInstanceState);
			setContentView (R.layout.activity_fragment);
			
			//make sure to import android.support.v4.app.FragmentManager !!!!
			//it's the support.v4 one that you want!!!
			FragmentManager fm = getSupportFragmentManager();
			Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
			
			if (fragment == null)
			{
				fragment = createFragment();
				fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
			}
			
		}

}
