package com.onlybeyond.guidedemo.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import java.util.List;

import static com.onlybeyond.guidedemo.utils.LogUtils.LOGD;


/**
 * Created by only on 16/6/15.
 * Email: onlybeyond99@gmail.com
 */
public class CommonFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    public CommonFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList=fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        LOGD("fragment","size"+fragmentList.size());
        return fragmentList.size();

    }

}
