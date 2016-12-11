package com.onlybeyond.guidedemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.onlybeyond.guidedemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by only on 16/11/17.
 * Email: onlybeyond99@gmail.com
 */

public class GuideFragment extends BaseFragment {
    @BindView(R.id.iv_guide_up)
    ImageView ivGuideUp;

    private Context mContext;
    private int imgRes;

    @Override
    public void initData() {
        super.initData();
        Bundle arguments = getArguments();
        if(arguments!=null){
            imgRes = arguments.getInt("imgRes", -1);
        }
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View rootView=  inflater.inflate(R.layout.fragment_guide, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void fillData() {
        if(imgRes!=-1){
            ivGuideUp.setImageResource(imgRes);
        }

    }

    @Override
    public void requestData() {

    }




}
