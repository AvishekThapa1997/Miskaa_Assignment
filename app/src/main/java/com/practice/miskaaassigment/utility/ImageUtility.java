package com.practice.miskaaassigment.utility;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.practice.miskaaassigment.R;

public class ImageUtility {
    public static void setImage(String imageUrl, ImageView holder, Context context){
        GlideToVectorYou.init().
                with(context).
                setPlaceHolder(Integer.MIN_VALUE, R.drawable.no_image).
                load(Uri.parse(imageUrl),holder);
    }
}
