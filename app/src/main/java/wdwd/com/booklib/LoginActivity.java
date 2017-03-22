package wdwd.com.booklib;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.concurrent.Callable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import wdwd.com.booklib.view.weight.CircleImageView;
import wdwd.com.booklib.view.weight.CircularRevealView;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "RxAndroidSamples";

    private final CompositeDisposable disposables = new CompositeDisposable();
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    CircleImageView btnLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @BindView(R.id.av_loading_view)
    AVLoadingIndicatorView avLoadingView;
    @BindView(R.id.scale_view)
    CircularRevealView scaleView;

    /***
     * 登录动画的控制
     **/
    private Disposable btnLoginDisposable = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);
        avLoadingView.hide();

        getWindow().setSharedElementsUseOverlay(true);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear();
    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        avLoadingView.show();
        btnLogin.setVisibility(View.GONE);
        Flowable.defer(new Callable<Publisher<Integer>>() {
            @Override
            public Publisher<Integer> call() throws Exception {
                return new Publisher<Integer>() {
                    @Override
                    public void subscribe(Subscriber<? super Integer> s) {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        s.onNext(0);
                        s.onComplete();
                    }
                };
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        avLoadingView.hide();
                        btnLogin.setVisibility(View.VISIBLE);

                        Point point = getLocationInView(scaleView,btnLogin);
                        scaleView.setVisibility(View.VISIBLE);
                        scaleView.reveal(point.x, point.y, Color.parseColor("#ff4081"), btnLogin.getHeight(), 600, new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(LoginActivity.this,scaleView,"login");
                                startActivity(new Intent(LoginActivity.this,MainActivity.class),optionsCompat.toBundle());
//                                scaleView.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        });

                    }
                });
    }


    private Point getLocationInView(View src, View target) {
        final int[] l0 = new int[2];
        src.getLocationOnScreen(l0);

        final int[] l1 = new int[2];
        target.getLocationOnScreen(l1);

        l1[0] = l1[0] - l0[0] + target.getWidth() / 2;
        l1[1] = l1[1] - l0[1] + target.getHeight() / 2;

        return new Point(l1[0], l1[1]);
    }
}
