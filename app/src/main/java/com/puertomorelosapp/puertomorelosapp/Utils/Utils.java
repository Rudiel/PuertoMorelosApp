package com.puertomorelosapp.puertomorelosapp.Utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by rudielavilaperaza on 6/8/17.
 */

public class Utils {

    public static Typeface getbukharisLetter(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/bukhariscript.ttf");
    }

}
