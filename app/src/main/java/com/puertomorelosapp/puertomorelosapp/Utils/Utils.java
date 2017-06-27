package com.puertomorelosapp.puertomorelosapp.Utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by rudielavilaperaza on 6/8/17.
 */

public class Utils {

    private static final String BASE_URL = "https://puerto-morelos-app.firebaseio.com/";

    public static final String CATE_URL = "PuertoMorelos/Idiomas/Español/Menu";

    public static final String PLACES_URL = "PuertoMorelos/Idiomas/Español";

    public static final String CATE_URL_BACK = "PuertoMorelos/imagenesURL/Header";

    public static final String COMMENTS_URL = "PuertoMorelos/SocialAPP/Comments/";

    public static final String LIKES_URL = "PuertoMorelos/SocialAPP/Loves/";

    //https://puerto-morelos-app.firebaseio.com/PuertoMorelos/imagenesURL/Header/Comercios



    public static Typeface getbukharisLetter(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/bukhariscript.ttf");
    }

    public static final String HISTORIA_BACKGROUND = "PuertoMorelos/imagenesURL/Header/Historia";
    public static final String EVENTOS_BACKGROUND = "";
    public static final String LUGARES_BACKGROUND = "";
    public static final String ATRACTIVOS_BACKGROUND = "";
    public static final String HOTELES_BACKGROUND = "";
    public static final String RESTAURANTES_BACKGROUND = "";
    public static final String COMIDA_BACKGROUND = "";
    public static final String COMERCIOS_BACKGROUND = "";
    public static final String SERVICIOS_BACKGROUND = "";


}
