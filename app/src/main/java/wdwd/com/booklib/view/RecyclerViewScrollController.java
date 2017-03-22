package wdwd.com.booklib.view;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * Created by tomchen on 17/3/7.
 */

public class RecyclerViewScrollController implements View.OnTouchListener{

    private View touchView;
    private View subsidiaryView;
    private int maxScrollSize;
    public static final int SCROLL_VERTICAL = 0;
    public static final int SCROLL_HORIZONTAL = 1;

    private int dir;


    public void attachToView(View touchView,View subsidiaryView){
        attachToView(touchView,subsidiaryView,SCROLL_VERTICAL);
    }

    public void attachToView(View recyclerView, final View subsidiaryView, final int direction){
        this.touchView = recyclerView;
        this.subsidiaryView = subsidiaryView;
        this.dir = direction;
        this.touchView.setOnTouchListener(this);
        if(subsidiaryView.getWidth() == 0){
            subsidiaryView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    calculateScrollSize();
                    subsidiaryView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }else {
            calculateScrollSize();
        }
    }

    private void calculateScrollSize() {
        switch (dir){
            case SCROLL_HORIZONTAL:
                maxScrollSize =  subsidiaryView.getWidth();
                break;
            case SCROLL_VERTICAL:
                maxScrollSize =  subsidiaryView.getHeight();
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float size = event.getHistorySize();
        if(size == 0){
            return false;
        }
        float y =  event.getHistoricalY(0);
        float delaY =  event.getY() - y;
        return false;
    }


//    @Override
//    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//        switch (dir){
//            case SCROLL_HORIZONTAL:
//                ViewCompat.offsetLeftAndRight(subsidiaryView,-dx);
//                break;
//            case SCROLL_VERTICAL:
//                ViewCompat.setTranslationY(subsidiaryView,ViewCompat.getTranslationY(subsidiaryView)-dy);
//                ViewCompat.offsetTopAndBottom(recyclerView,-dy);
//                break;
//        }
//    }


}
