package com.example.pro_samsung;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

//https://maxfad.ru/programmer/android/283-splashscreen-v-android-pishem-zastavku.html

public class Logo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logo);
        int SPLASH_DISPLAY_LENGHT = 1500;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(Logo.this, MainActivity.class);
                Logo.this.startActivity(mainIntent);
                Logo.this.finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

}