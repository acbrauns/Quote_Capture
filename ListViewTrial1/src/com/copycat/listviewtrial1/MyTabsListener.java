package com.copycat.listviewtrial1;

import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.Toast;

class MyTabsListener implements TabListener {
    public android.app.Fragment mFragment;
    public Context mContext;

    public MyTabsListener(android.app.Fragment fragment, Context context) {
                mFragment = fragment;
                mContext = context;

    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
                Toast.makeText(mContext, "Reselected!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
                Toast.makeText(mContext, "Selected!", Toast.LENGTH_SHORT).show();
                ft.replace(R.id.fragmentContainer, mFragment);
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
                Toast.makeText(mContext, "Unselected!", Toast.LENGTH_SHORT).show();
                ft.remove(mFragment);
    }
    
}