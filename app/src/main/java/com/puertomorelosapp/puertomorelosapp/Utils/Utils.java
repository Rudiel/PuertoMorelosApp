package com.puertomorelosapp.puertomorelosapp.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;

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

    public static final String COMMENTS_SOCIAL_URL = "SocialUSER/comments";

    public static final String SELFIES_URL = "PuertoMorelos/SocialAPP/PhotoAlbum/PhotoSelfies/";

    public static final String GALLERY_URL = "PuertoMorelos/SocialAPP/PhotoAlbum/officialPhotos/";

    public static final String USERS_URL = "users";

    public static final String COMMENTS_COUNT = "usersCountSocial/";

    public static final String LIKE_SOCIAL_USER_URL = "SocialUSER/loves";

    public static final String WELCOME_AD = "PuertoMorelos/imagenesURL/Publicidad/WelcomeBackground/imageURL";

    public static final String SELFIES_URL_NEW = "SocialUSER/PhotoSelfies/";

    public static final String ADS_URL = "PuertoMorelos/imagenesURL/Publicidad/";


    public static Typeface getbukharisLetter(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/bukhariscript.ttf");
    }

    public static void saveUserEmail(Context context, String email
    ) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("USER_EMAIL", email);
        editor.apply();
    }

    public static String getUserEmail(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("USER_EMAIL", "");
    }

    public static void saveUserImage(Context context, String image) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("USER_IMAGE", image);
        editor.apply();
    }

    public static String getUserImage(Context context) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("USER_IMAGE", "");
    }

    public static void saveProvider(Context context, String provider) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("USER_PROVIDER", provider);
        editor.apply();
    }

    public static String getProvider(Context context) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("USER_PROVIDER", "");
    }

    public static void saveUserName(Context context, String username) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("USER_NAME", username);
        editor.apply();
    }

    public static String getUserName(Context context) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("USER_NAME", "");

    }


}
