package com.yun.common.utils.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yun.common.R;
import com.yun.common.utils.DensityUtils;
import com.yun.common.utils.ScreenUtils;


/**
 * 作者：xujm on 2015/12/29
 * 备注：com.lzyc.framwork.widget.popupwindow
 */
public class PopupWindowOneButton extends PopupWindow {

    private Activity mContext;
    private TextView tv_content;
    private TextView tv_ok;

    public TextView getTv_content() {
        return tv_content;
    }

    public void setTv_content(TextView tv_content) {
        this.tv_content = tv_content;
    }

    public TextView getTv_ok() {
        return tv_ok;
    }

    public void setTv_ok(TextView tv_ok) {
        this.tv_ok = tv_ok;
    }

    public PopupWindowOneButton(Activity context) {
        this.mContext = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View conentView = inflater.inflate(R.layout.popup_one_button, null);
        tv_content = (TextView) conentView.findViewById(R.id.tv_one_bt_content);
        tv_ok = (TextView) conentView.findViewById(R.id.tv_one_bt_ok);
        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = ScreenUtils.getScreenHeight(mContext) - DensityUtils.dp2px(mContext, 60);
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(w);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(new BitmapDrawable());
        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        // 设置SelectPicPopupWindow弹出窗体动画效果
    }


    /**
     * 显示popupWindow
     *
     * @param parent
     */
    public void showPopupWindow(View parent, int gravity) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            setBackgroundAlpha(mContext, 0.5f);
            this.showAtLocation(parent, gravity, 0, 0);
        } else {
            this.dismiss();
        }

    }

    @Override
    public void dismiss() {
        super.dismiss();
        setBackgroundAlpha(mContext, 1f);
    }


    public static void setBackgroundAlpha(Activity activity, float bgAlpha) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        window.setAttributes(lp);
    }

}
