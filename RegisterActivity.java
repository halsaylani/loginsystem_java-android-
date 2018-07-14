package com.example.halsaylani.loginsystem;

import android.app.IntentService;
import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {


    private FirebaseAuth auth;
    private DatabaseReference rf = FirebaseDatabase.getInstance().getReference();
    FirebaseUser userid;
    String id;
    private EditText nametext;
    private EditText emailtext;
    private EditText passwordtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();


        nametext = findViewById(R.id.nametext);
        emailtext = findViewById(R.id.emailtext);
        passwordtext = findViewById(R.id.passwordtext) ;




    }


    public void Register(View view) {
        CreateUser();

    }

    public void CreateUser(){

        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.indicator);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        auth.createUserWithEmailAndPassword(emailtext.getText().toString(), passwordtext.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            UserDatabse();
                            tosigninpage();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegisterActivity.this," faild ",Toast.LENGTH_LONG).show();


                        }

                        // ...
                    }
                });

    }


    public void UserDatabse(){
        userid = auth.getCurrentUser();
        id = String.valueOf(userid.getUid());

       // String id = String.valueOf(userid.getUid());
        rf.child("Users").child(id).child("Name").setValue(nametext.getText().toString());
        rf.child("Users").child(id).child("Email").setValue(emailtext.getText().toString());
        rf.child("Users").child(id).child("Password").setValue(passwordtext.getText().toString());

    }

    public void tosigninpage(){
        Intent intent = new Intent(this,SigninActivity.class);
        startActivity(intent);
    }
}
