package wdwd.com.booklib.view.Adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wdwd.com.booklib.R;
import wdwd.com.booklib.bean.FriendBean;

/**
 * Created by tomchen on 17/2/20.
 */

public class FriendListAdapter extends SimpleAdapter<FriendBean> {

    private final static String TAG = "FriendListAdapter";

    public FriendListAdapter(List<FriendBean> mData,int brId) {
        super(mData, R.layout.item_friends, brId);
    }

    @Override
    public void onViewDetachedFromWindow(BindingViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        super.onBindViewHolder(holder,position);
        float fromScale = position == 0 ? 1.01f : position + 1.03f;
        if (fromScale > 1.4) {
            fromScale = 1.4f;
        }

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(holder.itemView, View.SCALE_X, fromScale, 1.0f);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(holder.itemView, View.TRANSLATION_Y, -80, 1);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setStartDelay(300);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.playTogether(objectAnimator, objectAnimator2);
        animatorSet.setDuration(500);
        animatorSet.start();

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.icon)
        SimpleDraweeView icon;
        @BindView(R.id.topic)
        TextView topic;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.tv_place)
        TextView tvPlace;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
