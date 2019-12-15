package com.example.recycleritemanimator.animator;

import android.support.v7.widget.RecyclerView;

import jp.wasabeef.recyclerview.animators.BaseItemAnimator;

/**
 * @author lipengfei
 * @date 2019/12/15
 * @email 1219742019@qq.com
 * @description 360度上丢显示动画
 */
public class UpShowAnimator extends BaseItemAnimator{

    private float mRecyclerViewHight;   //recyclerView高
    private float mMiskViewHight;       //底部麦克风高度

    public UpShowAnimator(float recyclerViewHight,float miskHeight){
        super();
        mRecyclerViewHight = recyclerViewHight;
        mMiskViewHight = miskHeight;
    }

    @Override
    protected void preAnimateRemoveImpl(RecyclerView.ViewHolder holder) {
        super.preAnimateRemoveImpl(holder);
        //实现移除动画前的处理

    }

    @Override
    protected void animateRemoveImpl(RecyclerView.ViewHolder holder) {
        //执行移除动画
        holder.itemView.animate()
                .alpha(0f)//逐渐透明
                .setDuration(getRemoveDuration())//使用默认的移除时间
                .start();
    }

    @Override
    protected void preAnimateAddImpl(RecyclerView.ViewHolder holder) {
        super.preAnimateAddImpl(holder);
        //添加动画前调用

        /**
         * 计算位移的位置
         * mRecyclerViewHight 参数传入的RecyclerView的高,
         * getY() item的Y坐标
         * mMiskViewHight 参数传入的麦克风图片的高
        * */
        float translationY = mRecyclerViewHight - holder.itemView.getY() - mMiskViewHight;

        //提前设置item的Y轴偏移，透明度为0。
        holder.itemView.setTranslationY(translationY);
        holder.itemView.setAlpha(0f);

    }

    @Override
    protected void animateAddImpl(RecyclerView.ViewHolder holder) {
        //执行添加动画
        holder.itemView.animate()
                .setDuration(getAddDuration())
                .translationY(0)//回到原位置
                .rotation(360)//旋转360度
                .alpha(1f)//透明度显现
                .start();
    }

    
}
