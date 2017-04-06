package wdwd.com.booklib;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

import wdwd.com.booklib.bean.FriendBean;
import wdwd.com.booklib.databinding.FragmentDynamicBinding;

/**
 * Created by tomchen on 17/4/6.
 */

public class TopicDynamicFragment extends Fragment{

    private FriendBean friendBean;
    private FragmentDynamicBinding fragmentDynamicBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentDynamicBinding =  DataBindingUtil.inflate(inflater,R.layout.fragment_dynamic,container,false);
        friendBean = new FriendBean();
        friendBean.setImg("sealskdjf");
        friendBean.setName("ceshi yi xia ");
        fragmentDynamicBinding.setTopicDynamicFragment(this);
        fragmentDynamicBinding.setUser(friendBean);
        return fragmentDynamicBinding.getRoot();
    }

    public void onClickFriend(View view){
        Logger.d("点击了");

        fragmentDynamicBinding.getUser().setName("tom");
        friendBean.setImg("++++");
        fragmentDynamicBinding.notifyChange();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
