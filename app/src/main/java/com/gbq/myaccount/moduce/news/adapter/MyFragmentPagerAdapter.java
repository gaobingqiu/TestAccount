package com.gbq.myaccount.moduce.news.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * fragment的适配器
 * FragmentPagerAdapter:会根据layout id缓存，大于两个是容易混乱；
 * FragmentStatePagerAdapter：只是加载当前
 * Created by gbq on 2016-12-20.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
	private List<Fragment> list;

	public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
		super(fm);
		this.list = list;
	}

	@Override
	public Fragment getItem(int position) {
		return list.get(position);
	}

	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	}
}
