package wdwd.com.booklib.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

/**
 * 需要注意的是这个jobService 运行在你的主线程，这意味着你需要还用子线程，handler，或者一个异步任务来运行耗时的
 * 操作以防止阻塞主线程
 *
 * Created by tomchen on 17/1/23.
 */

public class JobSchedulerService extends JobService {

    /**
     * 当任务开始会执行 onStartJob，因为这是系统用来触发已经被执行的任务。
     * 正如你所看到的，这个方法返回一个boolean值，
     *
     * @param params 如果返回false，系统假设这个方法返回时，任务已经执行完毕。如果返回true ,那么系统假定这个任
     *               务正要被执行，执行任务的重担就落在你肩上
     * @return
     */
    @Override
    public boolean onStartJob(JobParameters params) {
        mJobHandler.sendMessage(Message.obtain(mJobHandler,1,params));
        return true; //返回TRUE 自己处理后续事物
    }

    /**
     * 当任务执行完毕时你需要调用 jobFinished 来通知系统
     * 当系统接收到一个取消请求时，系统会调用onStopJob,方法取消正在等待执行的任务。很重要的一点是如果onStartJob
     * 返回false，那么系统假定在接收到一个取消请求时已经没有正在运行的任务。换句话说，onStopJob这种情况下不会被调
     * 用
     * @param params
     * @return
     */
    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }


    private Handler mJobHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Toast.makeText(getApplicationContext(), "JobService task running", Toast.LENGTH_SHORT).show();

            //needsRescheduled参数是让系统知道这个任务是否应该在最初的的条件下被重复执行
            //这个boolean 值很有用，因为它致命了你如何处理由于其他原因导致任务执行失败的情况，
            //
            jobFinished((JobParameters) msg.obj,false);
            return true;
        }
    });
}
