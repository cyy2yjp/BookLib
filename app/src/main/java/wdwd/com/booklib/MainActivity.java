package wdwd.com.booklib;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;
import wdwd.com.booklib.view.Adapter.LifeCycleViewAdapter;
import wdwd.com.booklib.view.weight.BakedBezierInterpolator;
import wdwd.com.booklib.view.weight.CircularRevealView;

/**
 * Created by tomchen on 17/2/27.
 */

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottomNavigation)
    BottomNavigation bottomNavigation;
    @BindView(R.id.fl_content)
    ViewPager flContent;
    @BindView(R.id.circularView)
    CircularRevealView circularView;
    @BindView(R.id.ll_content)
    LinearLayout llContent;

    private List<Fragment> fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragments();
        flContent.setAdapter(new LifeCycleViewAdapter(fragments, getSupportFragmentManager()));
        flContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigation.setSelectedIndex(position,true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bottomNavigation.setOnMenuItemClickListener(new BottomNavigation.OnMenuItemSelectionListener() {
            @Override
            public void onMenuItemSelect(@IdRes int i, int i1, boolean b) {
                flContent.setCurrentItem(bottomNavigation.getSelectedIndex(),true);
            }

            @Override
            public void onMenuItemReselect(@IdRes int i, int i1, boolean b) {

            }
        });
        setupEnterAnimation();
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    private void setupEnterAnimation() {
        circularView.post(new Runnable() {
            @Override
            public void run() {
                createRevealAnimator(false).start();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    private AnimatorSet createRevealAnimator(boolean reversed) {
        int x = getIntent().getIntExtra("x", 0);
        int y = getIntent().getIntExtra("y", 0);
        int height = getIntent().getIntExtra("height", 0);
        float hypot = (float) Math.hypot(circularView.getHeight(), circularView.getWidth()) * 2;
        float startRadius = reversed ? hypot : height;
        float endRadius = reversed ? height : hypot;

        AnimatorSet animatorSet = new AnimatorSet();
        Animator animator = ViewAnimationUtils.createCircularReveal(
                circularView, x, y,
                startRadius,
                endRadius);
        animator.setInterpolator(BakedBezierInterpolator.getInstance());
        animator.setDuration(2200);

        Animator animator1 = ObjectAnimator.ofFloat(flContent, View.ALPHA, 0, 1);
        Animator animator2 = ObjectAnimator.ofFloat(circularView, View.ALPHA, 1, 0);
        animator1.setDuration(50);
        animator1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                llContent.setBackgroundResource(android.R.color.white);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator2.setDuration(1800);
        animator1.setStartDelay(450);
        animator2.setStartDelay(600);

        animatorSet.setInterpolator(BakedBezierInterpolator.getInstance());
        animatorSet.playTogether(animator, animator1, animator2);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                bottomNavigation.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        return animatorSet;
    }

    private void initFragments() {
        fragments = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            if (i == 0) {
                fragments.add(new TopicListFragment());
            }else if(i == 1){
                fragments.add(new TopicDynamicFragment());
            } else {
                fragments.add(new ProfileFragment());
            }
        }
    }
}
