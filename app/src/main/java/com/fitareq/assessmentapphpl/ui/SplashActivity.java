package com.fitareq.assessmentapphpl.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.fitareq.assessmentapphpl.R;
import com.fitareq.assessmentapphpl.ui.main.MainActivity;
import com.fitareq.assessmentapphpl.utils.AppConst;
import com.fitareq.assessmentapphpl.utils.SharedPref;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler(getMainLooper());
        SharedPref sharedPref = new SharedPref();
        String isLogged = sharedPref.getValueFromPref(this, AppConst.IS_USER_LOGGED_IN);

        handler.postDelayed(() -> {
            if (isLogged == null || isLogged.equals("false")){
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }else {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
            finish();
        },2000);
    }


}