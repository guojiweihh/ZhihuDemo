package com.guo.zhihudemo;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;


/**
 * Created by guo on 2016/5/31.
 */
public class ZhihuDailyApplication extends Application{
    public void initImageLoader(Context context){
        ImageLoaderConfiguration config=new ImageLoaderConfiguration.Builder(context)
                .denyCacheImageMultipleSizesInMemory()
                .threadPriority(Thread.NORM_PRIORITY-2)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();
        ImageLoader.getInstance().init(config);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader(this);
    }
}
