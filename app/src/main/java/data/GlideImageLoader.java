package data;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.youth.banner.loader.ImageLoaderInterface;

/**
 * Created by xieyumin on 2018/7/16.
 */

public class GlideImageLoader extends ImageLoader implements ImageLoaderInterface {

    @Override
    public void displayImage(Context context, Object path, View imageView) {
        Glide.with(context).load(path).into((ImageView) imageView);
    }

    @Override
    public View createImageView(Context context) {
        ImageView imageView = new ImageView(context);
        return imageView;
    }
}
