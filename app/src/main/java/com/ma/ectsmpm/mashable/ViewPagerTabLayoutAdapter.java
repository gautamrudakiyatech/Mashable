package com.ma.ectsmpm.mashable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


@SuppressWarnings("deprecation")
public class ViewPagerTabLayoutAdapter extends FragmentPagerAdapter {

    private static final int NUM_PAGES = 5;

    public ViewPagerTabLayoutAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return Frag2.newInstance();
            case 2:
                return Frag3.newInstance();
            case 3:
                return Frag4.newInstance();
            case 4:
                return Frag5.newInstance();
            default:
                return Frag1.newInstance();
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Tech";
            case 1:
                return "Entertainment";
            case 2:
                return "Culture";
            case 3:
                return "Science";
            case 4:
                return "Automobiles";
            default:
                return null;
        }

    }

}
