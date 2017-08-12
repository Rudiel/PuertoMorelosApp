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

    private Boolean exist = false;


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

                    //saveUser(newUser);
                    checkExist(user.getEmail(), auth);
                } else {
                    view.showErrorMessage(task.getException().getMessage());
                }
                view.hideLoading();

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
                            FirebaseUser user = auth.getCurrentUser();
                            checkExist(user.getEmail(), auth);
                            //saveUser(newUser);
                        } else {
                            Log.w("FACE", "signInWithCredential:failure", task.getException());
                            view.showErrorMessage(task.getException().getMessage());
                        }

                        view.hideLoading();

                    }
                });

    }

    private void saveUser(NewUser newUser) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference().child("users").push().setValue(newUser);


        view.showMainActivity();
    }

    private void checkExist(final String email, final FirebaseAuth auth) {

        exist = false;

        FirebaseDatabase.getInstance().getReference().child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    FirebaseDatabase.getInstance().getReference().child("users" + "/" + data.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot data) {

                            if (data.child("email").getValue().equals(email)) {
                                exist = true;
                                return;
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void createUser(FirebaseAuth auth) {
        if (!exist) {
            NewUser newUser = new NewUser();
            newUser.setEmail(auth.getCurrentUser().getEmail());
            newUser.setProvider(auth.getCurrentUser().getUid());
            newUser.setUsername(auth.getCurrentUser().getDisplayName());
            newUser.setImageURL(String.valueOf(auth.getCurrentUser().getPhotoUrl()));

            saveUser(newUser);
        } else {
            view.showMainActivity();
        }
    }
}
