package com.moon.msupport.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.moon.msupport.R;

/**
 * Created by Moon on 2017/3/16.
 * Desc: base activity
 */

public abstract class BaseActivity extends AppCompatActivity {

    private boolean mUseCommonTitle;

    private TextView mTitleView;
    private TextView mRightView;
    private TextView mLeftView;
    private FrameLayout mFrameContent;
    private View mNetErrorView;
    private View mNoDataView;

    // 网络错误刷新
    private View.OnClickListener mNetErrorRefreshCLickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mFrameContent.removeView(mNetErrorView);
            reloadData();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);

        mUseCommonTitle = useCommonTitle();
        if (!mUseCommonTitle) {
            findViewById(R.id.rl_common_title).setVisibility(View.GONE);
        } else {
            mTitleView = (TextView) findViewById(R.id.tv_title);
            mRightView = ((TextView) findViewById(R.id.tv_right));
            mLeftView = ((TextView) findViewById(R.id.tv_left));
        }

        mFrameContent = ((FrameLayout) findViewById(R.id.frameLayout_content));
        if (getContentResId() != 0) {
            mFrameContent.addView(LayoutInflater.from(this).inflate(getContentResId(), null));
        }
    }

    /**
     * 获取内容布局
     *
     * @return resId
     */
    protected abstract int getContentResId();

    /**
     * 是否使用默认Title布局
     *
     * @return true/false
     */
    protected boolean useCommonTitle() {
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 设置页面标题
     *
     * @param title 标题
     */
    public void setActivityTitle(String title) {
        if (mUseCommonTitle) {
            mTitleView.setText(title);
        }
    }

    public TextView getTitleView() {
        return mTitleView;
    }

    public TextView getRightView() {
        return mRightView;
    }

    public TextView getLeftView() {
        return mLeftView;
    }

    public void initNetErrorView(View v) {
        mNetErrorView = v;
    }

    public void initNoDataView(View v) {
        mNoDataView = v;
    }

    public void showNetError() {
        if (mNetErrorView == null) {
            mNetErrorView = LayoutInflater.from(thisActivity()).inflate(R.layout.default_net_error_layout, null);
            mNetErrorView.findViewById(R.id.btn_refresh).setOnClickListener(mNetErrorRefreshCLickListener);
        }
        mFrameContent.addView(mNetErrorView);
    }

    public void removeNetErrorView() {
        for (int i = 0; i < mFrameContent.getChildCount(); i++) {
            if (mFrameContent.getChildAt(i) == mNetErrorView) {
                mFrameContent.removeView(mNetErrorView);
                break;
            }
        }
    }

    public void showNoData() {
        if (mNoDataView != null) {
            mNoDataView = LayoutInflater.from(thisActivity()).inflate(R.layout.default_no_data_layout, null);
        }
        mFrameContent.addView(mNoDataView);
    }

    public void removeNoDataView() {
        for (int i = 0; i < mFrameContent.getChildCount(); i++) {
            if (mFrameContent.getChildAt(i) == mNoDataView) {
                mFrameContent.removeView(mNoDataView);
                break;
            }
        }
    }

    public Activity thisActivity() {
        return this;
    }

    /**
     * 网络错误重试（子类可以重写）
     */
    protected void reloadData() {
    }
}
