package com.one.direction.nabehha.data.network.userapi;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UserFireBase {
    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    public void login(String email, String password, OnCompleteListener<AuthResult> mAuthResultOnCompleteListener, OnFailureListener onFailureListener) {

        mFirebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(mAuthResultOnCompleteListener).addOnFailureListener(onFailureListener);
    }

    public void register(String email, String password, String userName, OnCompleteListener<AuthResult> authResultOnCompleteListener, OnFailureListener onFailureListener) {

        mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(authResultOnCompleteListener).addOnFailureListener(onFailureListener);
    }
    public static UserFireBase getInstance() {

        return new UserFireBase();
    }
}
