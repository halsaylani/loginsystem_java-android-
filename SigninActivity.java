package com.example.halsaylani.loginsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.GoogleApiClient;

//import com.facebook.CallbackManager;
//import com.facebook.AccessToken;
import com.google.firebase.auth.FacebookAuthProvider;

import android.app.ProgressDialog;

public class SigninActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authListener;
    EditText emailtext;
    EditText passwordtext;


    @Override
    protected void onStart() {
        super.onStart();

       // auth.addAuthStateListener(authListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        auth = FirebaseAuth.getInstance();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){
                    homepage();
                }

            }
        };


        emailtext = findViewById(R.id.emailtext);
        passwordtext = findViewById(R.id.passwordtext);

    }




    //google login
    public void Google(View view){

//        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleclint);
//        startActivityForResult(signInIntent, RC_SIGN_IN);
        Toast.makeText(SigninActivity.this, "Future will be Provided Soon",
                Toast.LENGTH_SHORT).show();
    }
    //facebook login
    public void Facebook(View iew){
        Toast.makeText(SigninActivity.this, "Future will be Provided Soon",
                Toast.LENGTH_SHORT).show();

    }

    // login
    public void Signin(View view) {

        String email = emailtext.getText().toString();
        String password =  passwordtext.getText().toString();

        if ( email.isEmpty() || password.isEmpty()){
            Toast.makeText(SigninActivity.this, "can not find email/password",
                    Toast.LENGTH_SHORT).show();

        }else {

            final ProgressDialog progressDialog = new ProgressDialog(this, R.style.indicator);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Loading...");
            progressDialog.show();

            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(SigninActivity.this, "Authentication success.",
                                        Toast.LENGTH_SHORT).show();

                                homepage();

                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(SigninActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });

        }

    }

    //to register page
    public void Register(View view) {
        registerpage();
    }

    //to register activity
    public void registerpage(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    //to home page
    public void homepage(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }


}


////    // google result
////    @Override
////    public void onActivityResult(int requestCode, int resultCode, Intent data) {
////        super.onActivityResult(requestCode, resultCode, data);
////
//////        callbackmanager.onActivityResult(requestCode, resultCode, data);
//////        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
//////        if (requestCode == RC_SIGN_IN) {
//////            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//////            if (result.isSuccess()) {
//////                // Google Sign In was successful, authenticate with Firebase
//////                GoogleSignInAccount account = result.getSignInAccount();
//////                firebaseAuthWithGoogle(account);
//////                Toast.makeText(this,"signin with google SUCCESS" , Toast.LENGTH_LONG).show();
//////
//////            } else {
//////                Toast.makeText(this,"signin with google faild" , Toast.LENGTH_LONG).show();
//////            }
//////        }
////    }
//
//    // firebase auth with facebook
//    private void firebaseAuthWithFacebook(AccessToken token) {
//        Log.d("Fcaebook", "firebaseAuthWithFacebook:" + token);
//        // [START_EXCLUDE silent]
//
//        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
//        auth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d("Fcaebook", "signInWithCredential:success");
//                            FirebaseUser user = auth.getCurrentUser();
//                           // updateUI(user);
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w("Facebook", "signInWithCredential:failure", task.getException());
//                            Toast.makeText(SigninActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                            task.getResult();
//                           // updateUI(null);
//                        }
//
//                        // [START_EXCLUDE]
//                       // hideProgressDialog();
//                        // [END_EXCLUDE]
//                    }
//                });
//    }
//
//
//    // insert into database with google
//    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
//        // Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
//        // [START_EXCLUDE silent]
//        // showProgressDialog();
//        // [END_EXCLUDE]
//
//        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
//        auth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d("Tag", "signInWithCredential:success");
//                            // FirebaseUser user = mAuth.getCurrentUser();
//                            // updateUI(user);
//
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            // Log.w(TAG, "signInWithCredential:failure", task.getException());
//                            // Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
//                            // updateUI(null);
//
//                        }
//
//                        // [START_EXCLUDE]
//                        // hideProgressDialog();
//                        // [END_EXCLUDE]
//                    }
//                });
//    }
