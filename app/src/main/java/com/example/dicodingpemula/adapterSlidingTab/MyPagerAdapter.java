package com.example.dicodingpemula.adapterSlidingTab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.dicodingpemula.ExampleFragment;
import com.example.dicodingpemula.MainFragment;

public class MyPagerAdapter extends FragmentStatePagerAdapter {
    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int num) {
        Fragment fragment = null;
        switch (num) {
            case 0:
                fragment = new ExampleFragment();
                break;
            case 1:
                fragment =  new MainFragment();
                break;
        }
        return fragment;
    }
    @Override
    public int getCount() {
        return 2;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Example List";
            case 1: return "List Your Store";
            default: return null;
        }
    }

}
