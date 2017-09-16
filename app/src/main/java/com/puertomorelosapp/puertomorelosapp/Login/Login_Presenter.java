package com.puertomorelosapp.puertomorelosapp.Login;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.facebook.AccessToken;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.puertomorelosapp.puertomorelosapp.Models.Request.NewUser;
import com.puertomorelosapp.puertomorelosapp.Models.User;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;
import com.puertomorelosapp.puertomorelosapp.Utils.Utils;

/**
 * Created by rudielavilaperaza on 6/8/17.
 */

public class Login_Presenter implements ILogin_Presenter {

    private ILogin_View view;

    public Login_Presenter(Context context) {
        ((PuertoMorelosApplication) context).getAppComponent().inject(this);
    }

    @Override
    public void setView(ILogin_View view) {
        this.view = view;
    }

    @Override
    public void starRegisterActivity() {
        view.showRegisterActivity();
    }

    @Override
    public void loginWithPassandUser(final FirebaseAuth auth, final User user) {
        view.showLoading();

        auth.signInWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener((Activity) user.getContext(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    getUserCredentials(task.getResult().getUser());
                } else {
                    view.hideLoading();
                    view.showErrorMessage(task.getException().getMessage());
                }

            }
        });

    }

    @Override
    public void startRecoveryActivity() {
        view.showRecoveryActivity();
    }

    @Override
    public void loginWithFacebook(AccessToken token, final FirebaseAuth auth, Context context) {

        view.showLoading();
        Log.d("FACE", "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        auth.signInWithCredential(credential)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            task.getResult().getUser().getUid();
                            FirebaseUser user = auth.getCurrentUser();
                            getUserCredentials(user);
                            //getUserCredentials(user);
                            //view.saveUserData(user);
                            //view.hideLoading();
                            //getUserCredentials(user.getEmail());
                        } else {
                            Log.w("FACE", "signInWithCredential:failure", task.getException());
                            view.hideLoading();
                            view.showErrorMessage(task.getException().getMessage());
                        }


                    }
                });

    }

    private void getUserCredentials(final FirebaseUser firebaseUser) {

        FirebaseDatabase.getInstance().getReference().child("users/" + firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                com.puertomorelosapp.puertomorelosapp.Models.Response.User user = dataSnapshot.getValue(com.puertomorelosapp.puertomorelosapp.Models.Response.User.class);
                if (user != null) {
                    view.hideLoading();
                    view.saveUserData(user);
                } else {

                    com.puertomorelosapp.puertomorelosapp.Models.Response.User usuario = new com.puertomorelosapp.puertomorelosapp.Models.Response.User();
                    usuario.setEmail(firebaseUser.getEmail());
                    if (firebaseUser.getPhotoUrl() != null)
                        usuario.setImageURL("SomeimageURL");
                    else
                        usuario.setImageURL(firebaseUser.getPhotoUrl().toString());

                    usuario.setProvider(firebaseUser.getUid());
                    usuario.setUsername(firebaseUser.getDisplayName());

                    FirebaseDatabase.getInstance().getReference().child("users").child(firebaseUser.getUid()).setValue(usuario);
                    view.hideLoading();
                    view.saveUserData(usuario);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

}
