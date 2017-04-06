package wdwd.com.booklib.view.Adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by tomchen on 17/4/6.
 */

public class SimpleAdapter<T> extends RecyclerView.Adapter<BindingViewHolder>{

    private List<T> mData;

    private int layoutId;

    private int brId;

    public SimpleAdapter(List<T> mData, int layoutId, int brId) {
        this.mData = mData;
        this.layoutId = layoutId;
        this.brId = brId;
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(inflater,layoutId,parent,false);
        BindingViewHolder viewHolder = new BindingViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        holder.getBinding().setVariable(brId,mData.get(position));
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
}
