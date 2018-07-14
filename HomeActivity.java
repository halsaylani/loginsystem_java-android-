package com.example.halsaylani.loginsystem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drlayout;
    FirebaseAuth.AuthStateListener authListener;
    FirebaseAuth auth;

    @Override
    protected void onStart() {
        super.onStart();

       // auth.addAuthStateListener(authListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        auth = FirebaseAuth.getInstance();

        drlayout = findViewById(R.id.drawer_layout);

        NavigationView navview = findViewById(R.id.navview);
        navview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if ( item.getItemId() == R.id.nav_camera){
                    drlayout.closeDrawers();
                    System.out.print("pressed///////////////////////////");
                    signinpage();


                    return true;
                }

            // Toast.makeText(this, item.getTitle() + "presed" ,Toast.LENGTH_LONG).show();


                // close drawer when item is tapped


                return false;
            }
        });


    }


    public void logout (View view){
        auth.signOut();
        signinpage();


    }

    //to register activity
    public void signinpage(){
        Intent intent = new Intent(this, SigninActivity.class);
        startActivity(intent);
    }
}
