package wdwd.com.booklib;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by tomchen on 17/2/24.
 */

public class ViewPagerActivity extends Activity {

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.btn_switch)
    Button btnSwitch;
    private List<ImageView> views = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_list);
        ButterKnife.bind(this);

        for (int i = 0; i < 10; i++) {
            ImageView img = new ImageView(this);
            img.setBackground(getResources().getDrawable(R.mipmap.home_banner_bg));
            views.add(img);
        }


        viewpager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return views.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
//                super.destroyItem(container, position, object);
                container.removeView(views.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = views.get(position);
                container.addView(imageView);
                return imageView;
            }


            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        });
    }

    boolean isUpdate = false;
    @OnClick(R.id.btn_switch)
    public void onClick() {
        ImageView img = new ImageView(this);
        if(isUpdate){
            img.setBackground(getResources().getDrawable(R.mipmap.home_banner_bg));
        }else {
            img.setBackground(getResources().getDrawable(R.mipmap.spalsh_bg));
        }
        isUpdate = !isUpdate;
        views.set(1,img);
        viewpager.getAdapter().notifyDataSetChanged();
    }
}
