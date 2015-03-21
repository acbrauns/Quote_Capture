package com.copycat.listviewtrial1;


import java.util.UUID;


public class PRChar 
{
	private String mName;
	private String mTagline;
	private int mPhoto;
	private int mThumbnail;
	private UUID mCharId;
	
	public PRChar()
	{
		mCharId = UUID.randomUUID();
	}

	public String getName() {
		return mName;
	}
	

	public void setName(String name) {
		mName = name;
	}

	public String getTagline() {
		return mTagline;
	}

	public void setTagline(String tagline) {
		mTagline = tagline;
	}

	public int getPhoto() {
		return mPhoto;
	}
	

	public int getThumbnailPhoto() 
	{
		return mThumbnail;
	}

	public void setPhoto(int photo) {
		mPhoto = photo;
	}

	public void setThumbnailPhoto(int thumbnail) 
	{
		mThumbnail = thumbnail;
	}
	
	public UUID getCharId() {
		return mCharId;
	}

	public void setCharId(UUID charId) {
		mCharId = charId;
	}

}
