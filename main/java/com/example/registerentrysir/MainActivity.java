package com.example.registerentrysir;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.registerentrysir.fragment.CategoryFragment;
import com.example.registerentrysir.fragment.IdentifiedLoginFragment;
import com.example.registerentrysir.fragment.LoginFragment;
import com.example.registerentrysir.fragment.SplashScreen;
import com.example.registerentrysir.other.UtilityHelper;

public class MainActivity extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;


            final Fragment fragment = new SplashScreen();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.Frame_Layout, fragment).commit();


       /* Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences =getSharedPreferences("UserInfo",
                        Context.MODE_PRIVATE);

                if (sharedPreferences.contains("username")){
                    Fragment fragment=new SplashScreen();
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.Frame_Layout,fragment)
                            .commit();
                }
                else{
                    Fragment fragment1=new LoginFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.Frame_Layout,fragment1)
                            .commit();
                }
               *//* if (sharedPreferences.contains("UserName")) {
                    setFragment(new RegisterFormFragment());
                }
                else {
                    setFragment(new LoginFragment());
                }*//*
            }
        },2000);*/


        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                UtilityHelper helper = new UtilityHelper();
                Fragment fragment;
                if (helper.checkUser(context)) {
                    fragment = new LoginFragment();
                } else {
                    fragment = new CategoryFragment();
                }

                getSupportFragmentManager().beginTransaction().
                        replace(R.id.Frame_Layout, fragment).commit();

            }
        }, 2000);
    }


    public void shareText(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String shareBodyText = "Your sharing message goes here";
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject/Title");
        intent.putExtra(Intent.EXTRA_TEXT, shareBodyText);
        startActivity(Intent.createChooser(intent, "Choose sharing method"));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                intent.setType("text/plain");
               /* intent.setPackage("com.whatsapp");
                intent.putExtra(Intent.EXTRA_TEXT,"The text you wanted to share");
                startActivity(intent);*/
                String shareBodyText = "https://www.youtube.com/watch?v=IAD5G4ET3Ng using this app install it";
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject here");
                intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
                startActivity(Intent.createChooser(intent, "Sharing Option"));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
