package com.ins.myapplication4.app;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Administrator on 15-7-30.
 */

/**
 * 基础的activity
 */
public class BaseActivity extends FragmentActivity {
    /**
     * 获取startactivity之后，新的activity进入的动画
     * 默认时从右往左，如果定制不同的动画，重写这个方法即可
     * @return
     */
    protected int getEnterAnimationId(){
        return R.anim.anim_slide;
    }

    protected int getExitAnimationId(){
        return R.anim.anim_dropdown;
    }
    /**
     * 启动activity，并且给启动的activity指定一个动画
     * @param intent
     */
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(getEnterAnimationId(),0);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.anim_dropdown);
    }
}
