package com.one.direction.nabehha;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class tabPageAdapter extends FragmentStatePagerAdapter {
    String [] tabsArray = new String[] {"scheduled","past","trash"};
    Integer tabsNumber = 3;

    public tabPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabsArray[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0 :
                Scheduled one1 = new Scheduled();
                return one1 ;
            case 1 :
                Past two2 = new Past();
                return two2 ;
            case 2 :
                Trash three3 = new Trash();
                return  three3 ;
        }

        return null;
    }

    @Override
    public int getCount() {
        return tabsNumber;
    }
}
