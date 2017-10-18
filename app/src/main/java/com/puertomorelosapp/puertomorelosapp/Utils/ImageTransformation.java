package com.puertomorelosapp.puertomorelosapp.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.ExifInterface;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;

/**
 * Created by rudielavilaperaza on 18/10/17.
 */

public class ImageTransformation extends BitmapTransformation {

    private int orientation;

    public ImageTransformation(Context context, int orientation) {
        super(context);

        this.orientation=orientation;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        int exifOrientationDegrees = getExifOrientationDegrees(orientation);
        return TransformationUtils.rotateImageExif(toTransform, pool, exifOrientationDegrees);
    }

    @Override
    public String getId() {
        return null;
    }

    private int getExifOrientationDegrees(int orientation) {
        int exifInt=0;
        switch (orientation) {

            case ExifInterface.ORIENTATION_ROTATE_90:
                exifInt= ExifInterface.ORIENTATION_ROTATE_90;
                break;

            case ExifInterface.ORIENTATION_ROTATE_180:
                exifInt= ExifInterface.ORIENTATION_ROTATE_90;
                break;

            case ExifInterface.ORIENTATION_ROTATE_270:
                exifInt= ExifInterface.ORIENTATION_ROTATE_90;
                break;

            case ExifInterface.ORIENTATION_NORMAL:
                exifInt= ExifInterface.ORIENTATION_NORMAL;
            default:

        }
        return exifInt;
    }


}
