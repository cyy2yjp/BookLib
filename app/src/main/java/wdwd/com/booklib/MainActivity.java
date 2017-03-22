package wdwd.com.booklib;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;
import wdwd.com.booklib.view.Adapter.LifeCycleViewAdapter;
import wdwd.com.booklib.view.weight.CircleImageView;

/**
 * Created by tomchen on 17/2/27.
 */

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottomNavigation)
    BottomNavigation bottomNavigation;
    @BindView(R.id.fl_content)
    ViewPager flContent;
    @BindView(R.id.circularView)
    CircleImageView circularView;

    private List<Fragment> fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_view);
        ButterKnife.bind(this);
        initFragments();

        setupEnterAnimation();
        flContent.setAdapter(new LifeCycleViewAdapter(fragments, getSupportFragmentManager()));

        flContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigation.setSelectedIndex(position, true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bottomNavigation.setOnMenuItemClickListener(new BottomNavigation.OnMenuItemSelectionListener() {
            @Override
            public void onMenuItemSelect(@IdRes int i, int i1, boolean b) {
                flContent.setCurrentItem(i1, true);
            }

            @Override
            public void onMenuItemReselect(@IdRes int i, int i1, boolean b) {

            }
        });
    }

    private void setupEnterAnimation() {

        circularView.animate().scaleY(1).scaleY(1).alpha(0).setDuration(2000).start();
    }

    private void initFragments() {
        fragments = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            if (i == 0) {
                fragments.add(new TopicListFragment());
            } else {
                fragments.add(new ProfileFragment());
            }
        }
    }
}
