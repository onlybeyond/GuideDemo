package com.onlybeyond.guidedemo.utils;

import android.content.Context;

/**
 * Created by only on 16/11/25.
 * Email: onlybeyond99@gmail.com
 */

public class SizeUtils {
    /**
     * dp转px
     *
     * @param context 上下文
     * @param dpValue dp值
     * @return px值
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
