package com.fitareq.assessmentapphpl.ui.main;

import android.os.Bundle;

import com.fitareq.assessmentapphpl.data.base.BaseActivity;
import com.fitareq.assessmentapphpl.databinding.ActivityMainBinding;
import com.fitareq.assessmentapphpl.utils.AppConst;
import com.fitareq.assessmentapphpl.utils.SharedPref;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding binding;
    private String userName, phoneNumber, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getUserInformation();

    }

    private void getUserInformation() {
        SharedPref sharedPref = new SharedPref();
        phoneNumber = sharedPref.getValueFromPref(this, AppConst.KEY_USER_PHONE);
        email = sharedPref.getValueFromPref(this, AppConst.KEY_USER_EMAIL);
        userName = sharedPref.getValueFromPref(this, AppConst.KEY_USER_NAME);
    }
}