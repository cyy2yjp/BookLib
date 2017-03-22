package wdwd.com.booklib.util;


import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

import it.sephiroth.android.library.bottomnavigation.BottomNavigation;

/**
 * Created by tomchen on 17/2/27.
 */

public class TabManager implements BottomNavigation.OnMenuItemSelectionListener {

    private FragmentManager fragmentManager;
    private int mLastTabIndex = -1;
    private int mNewTabIndex ;
    private List<Fragment> fragments;
    private int mContainerId;


    @Override
    public void onMenuItemSelect(@IdRes int i, int i1, boolean b) {
        Fragment fragment = fragments.get(i1);
        Fragment oldFragment = fragments.get(i1);
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if(mLastTabIndex != mNewTabIndex){
            ft.hide(fragments.get(mLastTabIndex));
        }

        if(oldFragment.isHidden()){
            ft.show(oldFragment);
        }else {
            ft.add(mContainerId,fragment);
        }

        ft.commitAllowingStateLoss();
        fragmentManager.executePendingTransactions();
    }

    @Override
    public void onMenuItemReselect(@IdRes int i, int i1, boolean b) {

    }
}
