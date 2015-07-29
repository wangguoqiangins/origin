package com.ins.myapplication4.app.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.ins.myapplication4.app.R;

import java.util.List;

/**
 * Created by Administrator on 15-7-29.
 */
public class GuideAdapter extends PagerAdapter{
    private List<Integer> images;
    private Context context;
    private View.OnClickListener goOnClickListener;

    public GuideAdapter(List<Integer> images, Context context) {
        this.images = images;
        this.context = context;
    }
    public void setGoOnClickListener(View.OnClickListener goOnClickListener){
        this.goOnClickListener = goOnClickListener;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View ret = null;
        Integer resId = images.get(position);
        if(position==images.size()-1){
            //TODO 最后一个，设置布局，添加按钮
            FrameLayout frameLayout = new FrameLayout(context);
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(resId);
            ViewGroup.LayoutParams lp1 = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            );
            imageView.setLayoutParams(lp1);
            frameLayout.addView(imageView);
            ////////////////////////////////////////////
            Button btnGo = new Button(context);
            btnGo.setText(R.string.guide_start_main);
            //第三个参数指定控件在framelayout的哪个位置
            FrameLayout.LayoutParams lp2 = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM
            );
            //TODO 需要进行机型适配
            lp2.bottomMargin = 80;
            btnGo.setLayoutParams(lp2);
            btnGo.setOnClickListener(goOnClickListener);
            //代码创建的button是没有id的，通过tag可以进行按钮的区分
            btnGo.setTag("go");
            frameLayout.addView(btnGo);
            ret = frameLayout;
        }else {
            //TODO 直接是图片
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(resId);
            ret = imageView;
        }
        return ret;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
        if(object instanceof View){
            container.removeView((View)object);
        }
    }

    @Override
    public int getCount() {
        int ret = 0;
        if(images!=null){
            ret = images.size();
        }
        return ret;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        //对于fragmentpageradapter，o是fragment
        //view与o的判断就不能够直接view == o
        return view == o;
    }
}
