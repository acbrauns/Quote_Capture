package com.copycat.listviewtrial1;

import java.util.UUID;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class PRCharFragment extends Fragment {
	public static final String EXTRA_PRCHAR_ID = "com.copycat.listviewtrail1.prcharid";

	private PRChar mPrchar;
	private TextView mName;
	private TextView mTagline;
	private ImageView mPhoto;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		UUID prcharID = (UUID) getArguments().getSerializable(EXTRA_PRCHAR_ID);

		mPrchar = PRCharLab.get(getActivity()).getPRChars(prcharID);
		
		setHasOptionsMenu(true);
	}

	@TargetApi(11)
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_prchar, container, false);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			if (NavUtils.getParentActivityName(getActivity()) != null) {
				getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
			}
		}

		mName = (TextView) v.findViewById(R.id.character_name);
		mName.setText(mPrchar.getName());

		mTagline = (TextView) v.findViewById(R.id.character_tagline);
		mTagline.setText(mPrchar.getTagline());

		mPhoto = (ImageView) v.findViewById(R.id.character_photo);
		mPhoto.setImageResource(mPrchar.getPhoto());

		return v;
	}

	public static Fragment newInstance(UUID charId) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_PRCHAR_ID, charId);

		PRCharFragment fragment = new PRCharFragment();
		fragment.setArguments(args);

		return fragment;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			if (NavUtils.getParentActivityName(getActivity()) != null)
			{
				NavUtils.navigateUpFromSameTask(getActivity());
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);

		}

	}


	
	
}
