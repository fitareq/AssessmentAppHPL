package com.fitareq.assessmentapphpl.ui.main;

import android.content.Intent;
import android.os.Bundle;

import com.fitareq.assessmentapphpl.R;
import com.fitareq.assessmentapphpl.data.base.BaseActivity;
import com.fitareq.assessmentapphpl.databinding.ActivityMainBinding;
import com.fitareq.assessmentapphpl.ui.HistoryActivity;
import com.fitareq.assessmentapphpl.ui.NewTaskActivity;
import com.fitareq.assessmentapphpl.utils.AppConst;
import com.fitareq.assessmentapphpl.utils.SharedPref;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding binding;
    private String name, phoneNumber, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getUserInformation();
        binding.userName.setText(getString(R.string.user_name, name));
        binding.userPhone.setText(getString(R.string.user_phone, phoneNumber));
        binding.userEmail.setText(getString(R.string.user_email, email));

        binding.createNew.setOnClickListener(view -> {
            startActivity(new Intent(this, NewTaskActivity.class));
        });
        binding.history.setOnClickListener(view -> {
            startActivity(new Intent(this, HistoryActivity.class));
        });

    }

    private void getUserInformation() {
        SharedPref sharedPref = new SharedPref();
        phoneNumber = sharedPref.getValueFromPref(this, AppConst.KEY_USER_PHONE);
        email = sharedPref.getValueFromPref(this, AppConst.KEY_USER_EMAIL);
        name = sharedPref.getValueFromPref(this, AppConst.KEY_USER_NAME);
    }
}