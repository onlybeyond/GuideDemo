package com.onlybeyond.guidedemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.onlybeyond.guidedemo.R;

import static com.onlybeyond.guidedemo.utils.LogUtils.*;


/**
 * Created by only on 16/6/23.
 * Email: onlybeyond99@gmail.com
 */

public abstract class BaseActivity extends AppCompatActivity  {


    private static String TAG = makeLogTag(BaseActivity.class);

    //state
    private boolean isInitTop = true;// is init toolbar
    private boolean isHandlerNetworkError = true;//是否在基类处理网络异常

    private boolean isRegisterEvent;

    //data
    private long requestStartingTime;//record request data start time


    //view
    protected Toolbar toolbar;


    /**
     * 是否初始化顶部工具栏，若界面需要自定义工具栏则在initView()方法中调用该方法
     *
     * @param initTop 默认会初始化,不需要则传false
     */
    public void setInitTop(boolean initTop) {
        isInitTop = initTop;
    }

    public void setRegisterEvent(boolean registerEvent) {
        isRegisterEvent = registerEvent;
    }


    protected Bundle savedInstanceState;

    /**
     * 是否自己处理ret非200的情况，默认已在BaseActivity中处理
     *
     * @param handlerNetworkError
     */
    public void setHandlerNetworkError(boolean handlerNetworkError) {
        isHandlerNetworkError = handlerNetworkError;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        if (savedInstanceState != null) {
            restore(savedInstanceState);
        } else {
            initData();
        }
        initView();
        initTop();
        fillDate();
        requestData();

    }


    /**
     * restore data from savedInstanceState
     */
    public void restore(Bundle savedInstanceState) {
    }


    /**
     * init  get query from other page
     */
    public void initData() {

    }

    /**
     * find view from layout and set listener
     */
    public abstract void initView();

    /**
     * 初始化toolbar 可以重新不使用父类方法,也可以设置isInitTop 不继承父类
     */
    public void initTop() {
        if (isInitTop) {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            if (toolbar != null) {
                toolbar.setNavigationIcon(R.mipmap.ic_arrow_back);
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });

            }
        }

    }

    /**
     * init data
     */
    public abstract void fillDate();

    /**
     * network request
     */
    public void requestData() {

    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    /********************************
     * jump to other activity
     *******************************************/
    public void openActivity(Class<?> pClass) {
        openActivity(pClass, null);
    }

    public void openActivity(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }

    public void openActivity(String pAction) {
        openActivity(pAction, null);
    }

    public void openActivity(String pAction, Bundle pBundle) {
        Intent intent = new Intent(pAction);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }

    /**
     * 显示TOAST
     */
    public void showToast(final String text) {
        if (!TextUtils.isEmpty(text)) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    /**
     * 显示TOAST
     */
    public void showToast(final int resId) {
        if (resId > 0) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), resId, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }





}
