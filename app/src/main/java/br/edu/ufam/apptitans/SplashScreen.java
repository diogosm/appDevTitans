package br.edu.ufam.apptitans;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;

public class SplashScreen extends AppCompatActivity {

    private static final int SPLASH_TIMEOUT = 1200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //seta modo dia sempre
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        ImageView imageView = findViewById(R.id.logoSplashScreen);
        try{
            GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.logogif);
            imageView.setImageDrawable(gifDrawable);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIMEOUT);
    }
}