package com.ansar.wallsy.data;
import android.os.Parcelable;

import com.google.auto.value.AutoValue;

/**
 * Class used for handling an image.
 * Created by Musenkishi on 2014-02-23.
 */
@AutoValue
public abstract class Image implements Parcelable {

    public static final String TAG = Image.class.getName();

    public abstract String imageId();
    public abstract String thumbURL();
    public abstract String imagePageURL();
    public abstract String resolution();

    public static Image create(String imageId, String thumbURL, String imageURL, String resolution) {
        return new AutoValue_Image(imageId, thumbURL, imageURL, resolution);
    }

    public int getWidth(){
        if (resolution() != null && resolution().contains("x")) {
            String[] parts = resolution().split("x");
            String width = parts[0]; // width
            if (width != null) {
                return Integer.parseInt(width.trim());
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public int getHeight(){
        if (resolution() != null && resolution().contains("x")) {
            String[] parts = resolution().split("x");
            String height = parts[1]; // height
            if (height != null) {
                return Integer.parseInt(height.trim());
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }
}
