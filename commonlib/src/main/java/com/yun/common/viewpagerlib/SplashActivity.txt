package com.company.yun.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.company.yun.R;
import com.company.yun.base.BaseActivity;
import com.company.yun.base.Constants;
import com.yun.common.utils.SharePreferenceUtil;
import com.yun.common.utils.StatusBarUtil;
import com.yun.common.utils.StatusBarUtils;


/**
 * Created by Lovelin on 2019/3/27
 * <p>
 * Describe:启动页
 */
public class SplashActivity extends BaseActivity {

    private Boolean isFirstIn;
    private ImageView ivSplash;
    private Boolean isLogined;


    private void initView() {
        setTitleBarVisibility(View.GONE);
        ivSplash = findViewById(R.id.iv_splash);
        StatusBarUtils.setColor(this, getResources().getColor(R.color.wheel_timebtn_nor), 0);
        StatusBarUtil.darkMode(this, true);  //设置了状态栏文字的颜色
        //是否第一次进入app
        isFirstIn = (Boolean) SharePreferenceUtil.get(this, Constants.SP_IS_FIRST_IN,
                true);
        //是否登入
        isLogined = (Boolean) SharePreferenceUtil.get(this, Constants.Is_Logined, false);

        // 从浅到深,从百分之10到百分之百
        AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
        aa.setDuration(1500);// 设置动画时间
        ivSplash.setAnimation(aa);// 给image设置动画
        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                switchGoing();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int getContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    public void init() {
        initView();

    }

    //判断进入那个activity
    private void switchGoing() {
        if (isFirstIn) {
//            第一次进入-- 走引导页，否则进入MainActivity
            SharePreferenceUtil.put(SplashActivity.this, Constants.SP_IS_FIRST_IN,
                    false);
            Intent intent = new Intent();
            intent.setClass(SplashActivity.this, GuideActivity.class);
            startActivity(intent);

            finish();

        } else {
            if (!isLogined) {  //登入成功
                Intent intent = new Intent();
                intent.setClass(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent();
                intent.setClass(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

        }
    }
}
