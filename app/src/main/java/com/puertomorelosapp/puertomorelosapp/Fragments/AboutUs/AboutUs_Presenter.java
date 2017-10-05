package com.puertomorelosapp.puertomorelosapp.Fragments.AboutUs;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;
import com.puertomorelosapp.puertomorelosapp.Utils.Utils;

/**
 * Created by rudielavilaperaza on 6/13/17.
 */

public class AboutUs_Presenter implements IAboutUs_Presenter {

    IAboutUs_View view;

    public AboutUs_Presenter(Context context) {
        ((PuertoMorelosApplication) context).getAppComponent().inject(this);

    }

    @Override
    public void setView(IAboutUs_View view) {
        this.view = view;

    }

    @Override
    public void onTwitterClick(Context context) {

        Intent intent = null;
        try {
            context.getPackageManager().getPackageInfo("com.twitter.android", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=802228522634854400"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } catch (Exception e) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Utils.SOCIAL_TWITTER));
        }
        context.startActivity(intent);
    }

    @Override
    public void onFacebookClick(Context context) {

        Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
        String facebookUrl = getFacebookPageURL(context);
        facebookIntent.setData(Uri.parse(facebookUrl));
        context.startActivity(facebookIntent);

    }

    @Override
    public void onInstagramClick(Context context) {

        String url = Utils.SOCIAL_INSTAGRAM;

        final Intent intent = new Intent(Intent.ACTION_VIEW);
        try {
            if (context.getPackageManager().getPackageInfo("com.instagram.android", 0) != null) {
                if (url.endsWith("/")) {
                    url = url.substring(0, url.length() - 1);
                }
                final String username = url.substring(url.lastIndexOf("/") + 1);
                intent.setData(Uri.parse("http://instagram.com/_u/" + username));
                intent.setPackage("com.instagram.android");
            }
        } catch (PackageManager.NameNotFoundException ignored) {
            intent.setData(Uri.parse(url));
        }
        context.startActivity(intent);
    }

    @Override
    public void onContactClick(Context context) {

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + Utils.SOCIAL_EMAIL));
        intent.putExtra(Intent.EXTRA_EMAIL, "info@puertomorelosapp.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Info");
        if (intent.resolveActivity(context.getPackageManager()) != null)
            context.startActivity(intent);
    }


    private  String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) {
                return "fb://facewebmodal/f?href=" + Utils.SOCIAL_FACEBOOK;
            } else {
                return "fb://page/" + Utils.FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return Utils.SOCIAL_FACEBOOK;
        }
    }

}
