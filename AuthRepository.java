package com.example.weatherapp.auth;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthRepository {
    private final FirebaseAuth auth = FirebaseAuth.getInstance();

    public interface AuthCallback {
        void onSuccess(FirebaseUser user);
        void onFailure(Exception e);
    }

    public void signIn(String email, String password, final AuthCallback callback) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(task -> {
                if (task.isSuccessful() && auth.getCurrentUser() != null) {
                    callback.onSuccess(auth.getCurrentUser());
                } else {
                    callback.onFailure(task.getException());
                }
            });
    }

    public void signUp(String email, String password, final AuthCallback callback) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(task -> {
                if (task.isSuccessful() && auth.getCurrentUser() != null) {
                    callback.onSuccess(auth.getCurrentUser());
                } else {
                    callback.onFailure(task.getException());
                }
            });
    }

    public FirebaseUser getCurrentUser() {
        return auth.getCurrentUser();
    }
}
