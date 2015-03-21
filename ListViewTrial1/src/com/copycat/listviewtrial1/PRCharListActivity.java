package com.copycat.listviewtrial1;


import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;


public class PRCharListActivity extends SingleFragmentActivity 
{

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		 // ActionBar
        ActionBar actionbar = getActionBar();
        actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // create new tabs and set up the titles of the tabs
        ActionBar.Tab mTab1 = actionbar.newTab().setText(
                                getString(R.string.ui_tabname_1));
        ActionBar.Tab mTab2 = actionbar.newTab().setText(
                                getString(R.string.ui_tabname_2));
        ActionBar.Tab mTab3 = actionbar.newTab().setText(
                                getString(R.string.ui_tabname_3));
        ActionBar.Tab mTab4 = actionbar.newTab().setText(
                                getString(R.string.ui_tabname_4));
        
        
        // bind the fragments to the tabs - set up tabListeners for each tab
        
        mTab1.setTabListener(new TabListener (){

			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
			}
        	
        });
        
        mTab2.setTabListener(new TabListener (){

    			@Override
    			public void onTabSelected(Tab tab, FragmentTransaction ft) {
    				// TODO Auto-generated method stub
    			}

    			@Override
    			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    				// TODO Auto-generated method stub
    				
    			}

    			@Override
    			public void onTabReselected(Tab tab, FragmentTransaction ft) {
    				// TODO Auto-generated method stub
    			}
            	
            });
        
        mTab3.setTabListener(new TabListener (){

    			@Override
    			public void onTabSelected(Tab tab, FragmentTransaction ft) {
    				// TODO Auto-generated method stub
    			}

    			@Override
    			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    				// TODO Auto-generated method stub
    				
    			}

    			@Override
    			public void onTabReselected(Tab tab, FragmentTransaction ft) {
    				// TODO Auto-generated method stub
    			}
            	
            });

        
        mTab4.setTabListener(new TabListener (){

    			@Override
    			public void onTabSelected(Tab tab, FragmentTransaction ft) {
    				// TODO Auto-generated method stub
    			}

    			@Override
    			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    				// TODO Auto-generated method stub
    				
    			}

    			@Override
    			public void onTabReselected(Tab tab, FragmentTransaction ft) {
    				// TODO Auto-generated method stub
    			}
            	
            });
        
        // add the tabs to the action bar
        actionbar.addTab(mTab1);
        actionbar.addTab(mTab2);
        actionbar.addTab(mTab3);
        actionbar.addTab(mTab4);
        
        //these methods below must be called
        //...AFTER tabs are added to action bar to get scroll view!
        actionbar.setDisplayShowTitleEnabled(false);
        actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
	}

	@Override
	protected Fragment createFragment() 
	{
		return new PRCharListFragment();
	}

}
