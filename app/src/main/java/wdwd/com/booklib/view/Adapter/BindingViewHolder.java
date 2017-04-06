package wdwd.com.booklib.view.Adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by tomchen on 17/4/6.
 */

public class BindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder{

    protected final T mBinding;

    public BindingViewHolder(T binding){
        super(binding.getRoot());
        mBinding = binding;
    }

    public T getBinding() {
        return mBinding;
    }
}
