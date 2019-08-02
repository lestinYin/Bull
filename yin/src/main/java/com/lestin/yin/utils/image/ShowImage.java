package com.lestin.yin.utils.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.lestin.yin.R;
import com.lestin.yin.widget.photoview.PhotoView;

import java.io.ByteArrayOutputStream;


/**
 * autour: lestin.yin
 * email:lestin.yin@gmail.com
 * date: 2017/6/7 17:53
 * version:
 * description:
*/

public class ShowImage {

    public static void showPhotoView(Context context, String url, PhotoView mImageView) {
        GlideApp.with(context).asBitmap()
                .load(url)
                .centerInside()//等比例缩放在正中间
                .error(R.drawable.no_data)//占位图片
                .placeholder(R.drawable.no_data)//异常图片
                .into(mImageView);

    }public static void show(Context context, String url, ImageView imageView) {
        GlideApp.with(context).asBitmap()
                .load(url)
                .centerCrop()//等比例缩放在正中间
                .error(R.drawable.no_data)//占位图片
                .placeholder(R.drawable.no_data)//异常图片
                .into(imageView);

    }
    public static void showWithDefault(Context context, String url, ImageView imageView, int defaultPic) {
        GlideApp.with(context).asBitmap()
                .load(url)
                .centerCrop()//等比例缩放在正中间
                .error(defaultPic)//占位图片
                .placeholder(defaultPic)//异常图片
                .into(imageView);

    }
    public static void showCircle(Context context, String url, ImageView imageView) {
        GlideApp.with(context).asBitmap()
                .load(url)
                .circleCrop()
                .error(R.drawable.no_data)//占位图片
                .placeholder(R.drawable.no_data)//异常图片
                .into(imageView);

    }
    public static void showRoundCorners(Context context, String url, ImageView imageView,int radius) {
        GlideApp.with(context).asBitmap()
                .load(url)
                .error(R.drawable.no_data)//占位图片
                .placeholder(R.drawable.no_data)//异常图片
                .centerCrop()
                .transform(new RoundedCorners(radius))
                .into(imageView);

    }
    public static void showBitmap(Context context, Bitmap bitmap, ImageView imageView) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes=baos.toByteArray();
        GlideApp.with(context.getApplicationContext())
                .load(bytes)
                .diskCacheStrategy( DiskCacheStrategy.NONE )//禁用磁盘缓存
                .skipMemoryCache( true )//跳过内存缓存
                .transform(new RoundedCorners(5))
                .into(imageView);
//        transform(new CenterCrop(context), new GlideRoundTransform(context))
    }
}
