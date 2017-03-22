package wdwd.com.booklib;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

/**
 * Created by tomchen on 17/2/20.
 */

public class BookLibApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        ImagePipelineConfig imagePipelineConfig = ImagePipelineConfig.newBuilder(this).setDownsampleEnabled(true).build();
        Fresco.initialize(this,imagePipelineConfig);
    }
}
