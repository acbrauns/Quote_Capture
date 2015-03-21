package com.copycat.listviewtrial1;


import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class PRCharListFragment extends ListFragment 
{
	

	private ArrayList<PRChar> mPRChars;

	/*//I think the onCreate method is when the Fragment is created,
	 * whereas the onActivityCreated method is when the Activity is created
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		PRCharLab prcl = PRCharLab.get(getActivity());
		mPRChars = prcl.getPRChars();
		
		PRCharAdapter adapter = new PRCharAdapter (mPRChars);
		
		setListAdapter(adapter);
	} */
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) 
	{
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);
		PRCharLab prcl = PRCharLab.get(getActivity());
		mPRChars = prcl.getPRChars();
		
	
		PRCharAdapter adapter = new PRCharAdapter (mPRChars);
		
		ListView listview = getListView();
		listview.setDivider(new ColorDrawable(Color.parseColor("#b5b5b5")));
		listview.setDividerHeight(3);
		
		
		
		setListAdapter(adapter);
	
	} 


	@Override
	public void onListItemClick(ListView l, View v, int position, long id) 
	{
		super.onListItemClick(l, v, position, id);
		
		PRChar prc = ((PRCharAdapter)getListAdapter()).getItem(position);
		
		Intent i = new Intent (getActivity(), PRCharPagerActivity.class);
		i.putExtra(PRCharFragment.EXTRA_PRCHAR_ID, prc.getCharId());
		startActivity(i);
	}
	
	private class PRCharAdapter extends ArrayAdapter<PRChar>
	{
		public PRCharAdapter(ArrayList<PRChar> prchars)
		{
			super(getActivity(), 0 , prchars);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			//if we weren't given a View, inflate one
			if (convertView == null)
			{
				convertView = getActivity().getLayoutInflater().
						inflate(R.layout.list_item_prchar, parent, false);
			}
			
			
			PRChar prc = getItem(position);
			
			TextView nameTextView = (TextView)convertView.findViewById(R.id.prchar_list_item_name);
			nameTextView.setText(prc.getName());
			
			TextView taglineTextView = (TextView)convertView.findViewById(R.id.prchar_list_item_tagline);
			taglineTextView.setText(prc.getTagline());
			
			ImageView icon = (ImageView)convertView.findViewById(R.id.list_image);
			icon.setImageResource(prc.getThumbnailPhoto());
			
			return convertView;
		}
		
		
	}

	@Override
	public void onResume() 
	{
		super.onResume();
		((PRCharAdapter)getListAdapter()).notifyDataSetChanged();
	}


	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) 
	{
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.fragment_prchar_list, menu);
	}

	
	
}
