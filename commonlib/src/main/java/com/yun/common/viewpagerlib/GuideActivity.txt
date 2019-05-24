package com.company.yun.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.company.yun.R;
import com.company.yun.base.BaseActivity;
import com.company.yun.base.Constants;
import com.yun.common.utils.SharePreferenceUtil;
import com.yun.common.viewpagerlib.bean.PageBean;
import com.yun.common.viewpagerlib.callback.PageHelperListener;
import com.yun.common.viewpagerlib.indicator.TransIndicator;
import com.yun.common.viewpagerlib.view.GlideViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lovelin on 2019/3/27
 * <p>
 * Describe:新手引导页
 */
public class GuideActivity extends BaseActivity {
    private static final Integer[] RES = {R.mipmap.guide1, R.mipmap.guide2, R.mipmap.guide3,
            R.mipmap.guide4};
    private Boolean isLogined;

    @Override
    public int getContentViewId() {
        return R.layout.activity_glide_trans;
    }

    @Override
    public void init() {
        initView();

    }

    private void initView() {
//        StatusBarUtils.setColor(this, getResources().getColor(R.color.transparent), 0);
//        StatusBarUtil.darkMode(this, true);  //设置了状态栏文字的颜色
        setTitleBarVisibility(View.GONE);

        GlideViewPager viewPager = (GlideViewPager) findViewById(R.id.splase_viewpager);
        TransIndicator linearLayout = (TransIndicator) findViewById(R.id.splase_bottom_layout);
        //点击跳转的按钮
        Button button = (Button) findViewById(R.id.splase_start_btn);
        isLogined = (Boolean) SharePreferenceUtil.get(this, Constants.Is_Logined, false);


        //先把本地的图片 id 装进 list 容器中
        List<Integer> imagesList = new ArrayList<>();
        for (int i = 0; i < RES.length; i++) {
            imagesList.add(RES[i]);

        }
        //配置pagerbean，这里主要是为了viewpager的指示器的作用，注意记得写上泛型
        PageBean bean = new PageBean.Builder<Integer>()
                .setDataObjects(imagesList)
                .setIndicator(linearLayout)
                .setOpenView(button)
                .builder();

        // 把数据添加到 viewpager中，并把view提供出来，这样除了方便调试，也不会出现一个view，多个
        // parent的问题，这里在轮播图比较明显
        viewPager.setPageListener(bean, R.layout.image_layout, new PageHelperListener() {
            @Override
            public void getItemView(View view, Object data) {
                ImageView imageView = view.findViewById(R.id.icon);
                imageView.setImageResource((Integer) data);
            }
        });


        //点击实现跳转功能
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isLogined) {  //登入成功
                    Intent intent = new Intent();
                    intent.setClass(GuideActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    startActivity(new Intent(GuideActivity.this, MainActivity.class));
                    finish();
                }


            }
        });
    }
}
