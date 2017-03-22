package wdwd.com.booklib.view.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wdwd.com.booklib.R;
import wdwd.com.booklib.bean.TopicBean;

/**
 * Created by tomchen on 17/2/28.
 */

public class TopicListAdapter extends RecyclerView.Adapter<TopicListAdapter.TopicViewHolder> {

    private List<TopicBean> topicBeanList;
    private Activity activity;

    public TopicListAdapter(List<TopicBean> topicBeanList, Activity activity) {
        this.topicBeanList = topicBeanList;
        this.activity = activity;
    }

    @Override
    public TopicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = activity.getLayoutInflater().inflate(R.layout.item_topic, null);
        return new TopicViewHolder(itemView);
    }

    public static class TopicViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.topic_img)
        SimpleDraweeView topicImg;
        @BindView(R.id.topic_title)
        TextView topicTitle;

        public TopicViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public void onBindViewHolder(TopicViewHolder holder, int position) {
        TopicBean topicBean = topicBeanList.get(position);
        holder.topicImg.setImageURI(topicBean.getImg());
        holder.topicTitle.setText(topicBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return topicBeanList == null ? 0 : topicBeanList.size();
    }

}
