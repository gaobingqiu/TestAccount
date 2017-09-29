package com.gbq.myaccount.model.bean;

import android.text.TextUtils;

/**
 * 新闻
 * 
 * @author gbq
 */
@SuppressWarnings("unused")
public class News {
	private String ctime;
	private String title;
	private String description;
	private String picUrl;
	private String url;

	public String getTime() {
		return ctime;
	}

	public void setTime(String time) {
		this.ctime = time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "News [time=" + ctime + ", title=" + title + ", description=" + description + ", picUrl=" + picUrl
				+ ", url=" + url + "]";
	}

	@Override
	public boolean equals(Object object){
		if(object instanceof News){
			News compare = (News)object;
			return TextUtils.equals(this.getUrl(),compare.getUrl());
		}
		return super.equals(object);
	}

}
