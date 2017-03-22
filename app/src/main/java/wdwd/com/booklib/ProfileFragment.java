package wdwd.com.booklib;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wdwd.com.booklib.bean.FriendBean;
import wdwd.com.booklib.view.Adapter.FriendListAdapter;
import wdwd.com.booklib.view.RecycleViewDivider;

/**
 * Created by tomchen on 17/2/17.
 */

public class ProfileFragment extends Fragment {


    @BindView(R.id.app_bar_image)
    SimpleDraweeView appBarImage;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.friend_list)
    RecyclerView friendList;
    @BindView(R.id.tv_msg_notify)
    TextView tvMsgNotify;
    @BindView(R.id.float_option)
    FloatingActionButton floatOption;
    @BindView(R.id.collapsing_layout)
    CollapsingToolbarLayout collapsingLayout;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    private Unbinder unbinder;

    private List<FriendBean> friendBeanList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profile_view, null);
        unbinder = ButterKnife.bind(this,rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        appBarImage.setImageURI("http://p3.wmpic.me/article/2015/04/14/1428989717_ESIQxJUt.jpeg");
        loadFakeData();
        friendList.setNestedScrollingEnabled(true);
        friendList.setAdapter(new FriendListAdapter(getActivity(), friendBeanList));
        friendList.setLayoutManager(new LinearLayoutManager(getActivity()));
        friendList.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.HORIZONTAL, (int) getResources().getDimension(R.dimen.friend_list_item_divider_height), getResources().getColor(R.color.divider_color)));

        setupEnterAnimation(); //入场动画
        setupExitAnimation();//退场动画

        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(), R.animator.brage_scale);
        set.setTarget(tvMsgNotify);
        set.start();

        AnimatorSet set1 = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(), R.animator.brage_scale);
        set1.setTarget(floatOption);
        set1.start();
    }

    private void loadFakeData() {
        for (int i = 0; i < 10; i++) {
            FriendBean friendBean = new FriendBean();
            friendBeanList.add(friendBean);
        }
    }

    private void setupEnterAnimation() {
        Transition transition = TransitionInflater.from(getActivity()).inflateTransition(R.transition.arc_motion);
        getActivity().getWindow().setSharedElementEnterTransition(transition);
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                animateRevealShow();
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
    }

    private void animateRevealShow() {
//        GuiUtils.animateRevealShow(
//                this, mRlContainer,
//                btnFab.getWidth() / 2, R.color.colorAccent,
//                new GuiUtils.OnRevealAnimationListener() {
//                    @Override
//                    public void onRevealHide() {
//
//                    }
//
//                    @Override
//                    public void onRevealShow() {
//                        initViews();
//                    }
//                });
    }

    // 初始化视图
    private void initViews() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
//            Animation animation = AnimationUtils.loadAnimation(HomeActivity.this, android.R.anim.fade_in);
//            animation.setDuration(300);
//            mTVContainer.startAnimation(animation);
//            mIvClose.setAnimation(animation);
//            mTVContainer.setVisibility(View.VISIBLE);
//            mIvClose.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setupExitAnimation() {
        Fade fade = new Fade();
        getActivity().getWindow().setReturnTransition(fade);
        fade.setDuration(300);
    }

    // 退出事件
//    @Override
//    public void onBackPressed() {
//        animateRevealHide();
//    }

    private void animateRevealHide() {
        defaultBackPressed();
//        GuiUtils.animateRevealHide(
//                this, mRlContainer,
//                btnFab.getWidth() / 2, R.color.colorAccent,
//                new GuiUtils.OnRevealAnimationListener() {
//                    @Override
//                    public void onRevealHide() {
//                        defaultBackPressed();
//                    }
//
//                    @Override
//                    public void onRevealShow() {
//
//                    }
//                });
    }

    // 默认回退
    private void defaultBackPressed() {
//        super.onBackPressed();
    }
}
