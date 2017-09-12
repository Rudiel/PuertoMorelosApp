package com.puertomorelosapp.puertomorelosapp.Dagger;

import com.google.android.gms.maps.MapFragment;
import com.puertomorelosapp.puertomorelosapp.Fragments.AboutUs.AboutUs_Fragment;
import com.puertomorelosapp.puertomorelosapp.Fragments.AboutUs.AboutUs_Presenter;
import com.puertomorelosapp.puertomorelosapp.Fragments.Activities.Likes.Megusta_Fragment;
import com.puertomorelosapp.puertomorelosapp.Fragments.Activities.Likes.Megusta_Presenter;
import com.puertomorelosapp.puertomorelosapp.Fragments.Categories.Categories_Fragment;
import com.puertomorelosapp.puertomorelosapp.Fragments.Categories.Categories_Presenter;
import com.puertomorelosapp.puertomorelosapp.Fragments.Details.Comments.Comments_Detail_Fragment;
import com.puertomorelosapp.puertomorelosapp.Fragments.Details.Comments.Comments_Detail_Presenter;
import com.puertomorelosapp.puertomorelosapp.Fragments.Details.Detail_Fragment;
import com.puertomorelosapp.puertomorelosapp.Fragments.Details.Detail_Fragment_Presenter;
import com.puertomorelosapp.puertomorelosapp.Fragments.Details.Photos.Photos_Detail_Fragment;
import com.puertomorelosapp.puertomorelosapp.Fragments.Details.Photos.Photos_Detail_Presenter;
import com.puertomorelosapp.puertomorelosapp.Fragments.Map.Fragment_Map;
import com.puertomorelosapp.puertomorelosapp.Fragments.Map.Map_Presenter;
import com.puertomorelosapp.puertomorelosapp.Fragments.Secundary_Main.SecundaryMain_Fragment;
import com.puertomorelosapp.puertomorelosapp.Fragments.Secundary_Main.SecundaryMain_Presenter;
import com.puertomorelosapp.puertomorelosapp.Fragments.Thrid_Main.Third_Main_Fragment;
import com.puertomorelosapp.puertomorelosapp.Fragments.Thrid_Main.Third_Main_Presenter;
import com.puertomorelosapp.puertomorelosapp.Login.Login_Activity;
import com.puertomorelosapp.puertomorelosapp.Login.Login_Presenter;
import com.puertomorelosapp.puertomorelosapp.Main.Main_Activity;
import com.puertomorelosapp.puertomorelosapp.Main.Main_Presenter;
import com.puertomorelosapp.puertomorelosapp.Recover.Recover_Activity;
import com.puertomorelosapp.puertomorelosapp.Recover.Recover_Presenter;
import com.puertomorelosapp.puertomorelosapp.Register.Register_Activity;
import com.puertomorelosapp.puertomorelosapp.Register.Register_Presenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by rudielavilaperaza on 6/8/17.
 */
@Singleton
@Component(modules = {AppModule.class, PresenterModule.class})
public interface AppComponent {

    //Login
    void inject(Login_Activity activity);

    void inject(Login_Presenter target);

    //Register

    void inject(Register_Activity activity);

    void inject(Register_Presenter target);

    //Recovery

    void inject(Recover_Activity activity);

    void inject(Recover_Presenter target);

    //Main

    void inject(Main_Activity activity);

    void inject(Main_Presenter target);

    //AboutUs
    void inject(AboutUs_Fragment fragment);

    void inject(AboutUs_Presenter target);

    //Categories

    void inject(Categories_Fragment fragment);

    void inject(Categories_Presenter target);

    //Map

    void inject(Fragment_Map fragment);

    void inject(Map_Presenter target);

    //SubCategory

    void inject(SecundaryMain_Fragment fragment);

    void inject(SecundaryMain_Presenter target);

    //Third Category

    void inject(Third_Main_Fragment fragment);

    void inject(Third_Main_Presenter target);

    //Details

    void inject(Detail_Fragment fragment);

    void inject(Detail_Fragment_Presenter target);

    //Comments

    void inject(Comments_Detail_Presenter target);

    void inject(Comments_Detail_Fragment fragment);

    //Photos

    void inject(Photos_Detail_Fragment fragment);

    void inject(Photos_Detail_Presenter target);

    //Activities Likes

    void inject(Megusta_Fragment fragment);

    void inject(Megusta_Presenter target);


}
