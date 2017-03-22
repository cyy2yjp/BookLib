package wdwd.com.booklib;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Looper;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import wdwd.com.booklib.service.JobSchedulerService;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {

        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("wdwd.com.booklib", appContext.getPackageName());

        JobScheduler mJobScheduler = (JobScheduler) appContext.getSystemService(Context.JOB_SCHEDULER_SERVICE);

        //这个builder 允许你设置很多不同选项来控制任务的执行。下面的代码片段就是展示了如何设置以使得你的任务可以每三秒运行一次
        JobInfo.Builder builder = new JobInfo.Builder(1, new ComponentName(appContext.getPackageName(), JobSchedulerService.class.getName()));
        builder.setPeriodic(3000);

        if (mJobScheduler.schedule(builder.build()) <= 0) {
            //if something goes wrong
        }

        mJobScheduler.cancelAll();
    }

    @Test
    public void testMainThread() throws Exception {
        //emit 发射；发表；颁布；发表
        //This will execute the Observable on a new thread, and emit results through onNext on the
        //main thread
        Observable.just("one", "two", "three", "four", "five")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e("ExampleInstrumented", s);
                    }
                });

        System.out.print("出书");
    }

    @Test
    public void testBackgroundLooper() throws Exception{
         //merely 只是；仅仅，只不过；纯粹；全然
         // concept: 挂念，概念；观点；思想，摄像，想法；总得印象
         //
        //the previous sample is merely a specialization of a more general concept:binding asynchronous
        // communication  to an Android message loop,or Looper,In order to observe an Observable
        //to an Android message loop,or Looper.In order to observe an Observable on an arbitrary Looper,create an
        //associated Scheduler by calling AndroidSchedulers.from:

        Looper background = Looper.getMainLooper();

        Observable.just("one","two","three","four","five")
                .observeOn(AndroidSchedulers.from(background))
                .subscribe(/** an Observer*/);

        //this will execute the Observable on a new thread and emit results through onNext on
        // whatever thread is running backgroundLooper.
    }
}
