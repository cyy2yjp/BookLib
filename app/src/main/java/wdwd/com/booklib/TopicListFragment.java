package wdwd.com.booklib;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;

import com.cpoopc.scrollablelayoutlib.ScrollableHelper;
import com.cpoopc.scrollablelayoutlib.ScrollableLayout;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wdwd.com.booklib.bean.TopicBean;
import wdwd.com.booklib.view.Adapter.TopicListAdapter;
import wdwd.com.booklib.view.RecycleViewDivider;

import static wdwd.com.booklib.R.id.topic_top;

/**
 * Created by tomchen on 17/2/28.
 */

public class TopicListFragment extends BaseFragment implements ScrollableHelper.ScrollableContainer {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(topic_top)
    ScrollableLayout topicTop;
    @BindView(R.id.top_title)
    TextView topTitle;


    private List<TopicBean> topicBeanList = new ArrayList<>();

    @Override
    public int getViewRes() {
        return R.layout.fragment_topic_list;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        recyclerView.setAdapter(new TopicListAdapter(topicBeanList, getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.VERTICAL, (int) getResources().getDimension(R.dimen.topic_divider_height), getResources().getColor(android.R.color.transparent)));

        topicTop.getHelper().setCurrentScrollableContainer(this);

        topTitle.animate().z(6f).translationZ(30f).setDuration(430).setInterpolator(new BounceInterpolator()).setStartDelay(1000);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                switch (newState) {
                    case RecyclerView.SCROLL_STATE_SETTLING:
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        if (!Fresco.getImagePipeline().isPaused()) {
                            Fresco.getImagePipeline().pause();
                        }

                        break;
                    case RecyclerView.SCROLL_STATE_IDLE:
                        if (Fresco.getImagePipeline().isPaused()) {
                            Fresco.getImagePipeline().resume();
                        }
                        break;
                }
            }

        });
//        new RecyclerViewScrollController().attachToView(touchView, topicTop);
    }

    @Override
    public View getScrollableView() {
        return recyclerView;
    }

    public boolean isRecyclerViewAttach(RecyclerView recyclerView) {
        if (recyclerView != null && recyclerView.getChildCount() > 0) {
            if (recyclerView.getChildAt(0).getTop() < 0) {
                return false;
            }
        }
        return true;
    }

    private void initData() {

        for (int i = 0; i < 10; i++) {
            TopicBean topicBean = new TopicBean();
            if (i == 0) {
                topicBean.setImg("http://img.tuku.cn/file_big/201501/e27ff51ee8b24733afbe46e78940d7bc.jpg");
                topicBean.setTitle("lundun");
            } else if (i == 1) {
                topicBean.setImg("http://img.tuku.cn/file_big/201501/7dcd94aafb5f4b02a32b072f8f69bf2f.jpg");
                topicBean.setTitle("newZand");
            } else if (i == 2) {
                topicBean.setImg("http://img.tuku.cn/file_big/201501/b5d2f53d9e4d4b708b51e57e1a4ce682.jpg");
                topicBean.setTitle("beijing");
            } else {
                topicBean.setImg("http://img.tuku.cn/file_big/201501/e85a8ba568c145a497f1cc42e7c646f4.jpg");
                topicBean.setTitle("tokey");
            }
            topicBeanList.add(topicBean);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
