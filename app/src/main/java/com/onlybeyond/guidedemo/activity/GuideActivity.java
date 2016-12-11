package com.onlybeyond.guidedemo.activity;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.onlybeyond.guidedemo.R;
import com.onlybeyond.guidedemo.adapter.CommonFragmentPagerAdapter;
import com.onlybeyond.guidedemo.fragment.GuideFragment;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.onlybeyond.guidedemo.utils.LogUtils.*;


/**
 * Created by only on 16/11/17.
 * Email: onlybeyond99@gmail.com
 */

public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnTouchListener {

    private static String TAG = makeLogTag(GuideActivity.class);
    @BindView(R.id.ll_scroll)
    LinearLayout llScroll;
    @BindView(R.id.iv_ride)
    ImageView ivRide;


    @BindView(R.id.vp_guide)
    ViewPager vpGuide;
    @BindView(R.id.iv_guide_down)
    ImageView ivGuideDown;
    @BindView(R.id.v_scroll)
    View vScroll;


    private float lastOffset;
    private float lastX;
    private int pagerPosition;

    private int[] imgResUp;//上半部分图片
    private int[] imgResDown;//下半部分图片
    private AnimationDrawable animationDrawable = null;

    private LinkedList<Fragment> mFragmentList;

    @Override
    public void initData() {
        super.initData();
        Bundle extras = getIntent().getExtras();

    }

    @Override
    public void initView() {
        setInitTop(false);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);


    }

    @Override
    public void fillDate() {
        imgResUp = new int[]{R.mipmap.guide_up_one_bg, R.mipmap.guide_up_two_bg, R.mipmap.guide_up_three_bg};
        imgResDown = new int[]{R.mipmap.guide_down_one, R.mipmap.guide_down_two, R.mipmap.guide_down_three};
        mFragmentList = new LinkedList<>();
        parseFragment();
        vpGuide.setAdapter(new CommonFragmentPagerAdapter(getFragmentManager(), mFragmentList));
        vpGuide.addOnPageChangeListener(this);

        ivRide.setBackgroundResource(R.drawable.guide_animation_list);
        animationDrawable = (AnimationDrawable) ivRide.getBackground();
        animationDrawable.start();

        vpGuide.setOnTouchListener(this);


    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) vScroll.getLayoutParams();
        float distance = vScroll.getWidth();
        int leftDistance = 0;
        if (lastOffset < positionOffset && positionOffset > 0) {
            //正向
            leftDistance = (int) (distance * (position));
            lp.leftMargin = leftDistance +
                    (int) (positionOffset * distance);
            if (positionOffset < 0.5) {
             /*   if (position == 0) {
                    ivGuideDown.setImageResource(imgResDown[0]);
                } else if (position == 1) {
                    ivGuideDown.setImageResource(imgResDown[1]);
                }*/
                ivGuideDown.setImageResource(imgResDown[position]);

                float alpha = 1 - 2 * positionOffset;
                ivGuideDown.setAlpha(alpha < 0.2 ? 0.2f : alpha);

            } else {
              /*  if (position == 0) {
                    ivGuideDown.setImageResource(imgResDown[1]);
                } else if (position == 1) {
                    ivGuideDown.setImageResource(imgResDown[2]);
                }*/
                ivGuideDown.setImageResource(imgResDown[position+1]);

                float alpha = (float) (2 * (positionOffset - 0.5));
                ivGuideDown.setAlpha(alpha < 0.2 ? 0.2f : alpha);
            }

        }
        if (lastOffset > positionOffset && positionOffset > 0) {
            //反向
            leftDistance = (int) (distance * (position + 1));
            lp.leftMargin = leftDistance +
                    (int) ((positionOffset - 1) * distance);

            if (positionOffset < 0.5) {
//                if (position == 0) {
//                    ivGuideDown.setImageResource(imgResDown[0]);
//                } else if (position == 1) {
//                    ivGuideDown.setImageResource(imgResDown[1]);
//                }
                                    ivGuideDown.setImageResource(imgResDown[position]);

                float alpha = 1 - 2 * positionOffset;
                ivGuideDown.setAlpha(alpha < 0.2 ? 0.2f : alpha);

            } else {
               /* if (position == 0) {
                    ivGuideDown.setImageResource(imgResDown[1]);
                } else if (position == 1) {
                    ivGuideDown.setImageResource(imgResDown[2]);
                }*/
                                    ivGuideDown.setImageResource(imgResDown[position+1]);

                float alpha = 2 * (positionOffset - 0.5f);
                ivGuideDown.setAlpha(alpha < 0.2 ? 0.2f : alpha);
            }
        }
        lastOffset = positionOffset;

        LOGD(TAG, "---left margin" + lp.leftMargin + "--- position offset"
                + positionOffset + "---distance" + distance + "left distance" + leftDistance +
                "---current page" + position + "---position" + position);
        lp.width = (int) distance;
        vScroll.setLayoutParams(lp);
        //设置底部图片的透明图

        pagerPosition = position;


    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void parseFragment() {
        if (imgResUp != null) {
            int length = imgResUp.length;
            for (int i = 0; i < length; i++) {
                GuideFragment guideFragment = new GuideFragment();
                Bundle args = new Bundle();
                args.putInt("imgRes", imgResUp[i]);
                guideFragment.setArguments(args);
                mFragmentList.add(guideFragment);

            }
        }
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (pagerPosition == 2) {
            int action = motionEvent.getAction();

            if (action == MotionEvent.ACTION_DOWN) {
                lastX = motionEvent.getX();
            } else if (action == MotionEvent.ACTION_MOVE) {
                if (lastX - motionEvent.getX() > 100) {
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            }

        }

        return false;
    }
}
