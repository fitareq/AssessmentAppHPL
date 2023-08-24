package com.fitareq.assessmentapphpl.ui.registration;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.fitareq.assessmentapphpl.data.base.BaseActivity;
import com.fitareq.assessmentapphpl.data.model.RegistrationBody;
import com.fitareq.assessmentapphpl.databinding.ActivityRegistrationBinding;
import com.fitareq.assessmentapphpl.ui.main.MainActivity;
import com.fitareq.assessmentapphpl.utils.AppConst;
import com.fitareq.assessmentapphpl.utils.SharedPref;

public class RegistrationActivity extends BaseActivity {
    private ActivityRegistrationBinding binding;
    private RegistrationViewModel viewModel;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(RegistrationViewModel.class);

        phone = getIntent().getStringExtra(AppConst.KEY_USER_PHONE);
        if (phone != null) {
            binding.phoneNumber.setText(phone);
        }
        binding.register.setOnClickListener(view -> userRegistration());

        viewModel.registerResponse.observe(this, resource -> {
            switch (resource.status) {
                case LOADING -> showLoadingScreen();
                case SUCCESS -> {
                    saveUserInformation();
                    hideLoadingScreen();
                    Toast.makeText(this, resource.data, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                    finish();
                }
                case ERROR -> {
                    Toast.makeText(this, resource.message, Toast.LENGTH_SHORT).show();
                    hideLoadingScreen();
                }
            }
        });
    }

    private void saveUserInformation() {
        SharedPref sharedPref = new SharedPref();
        sharedPref.saveValueToPref(this, AppConst.KEY_USER_PHONE, userInfo.getContactNumber());
        sharedPref.saveValueToPref(this, AppConst.KEY_USER_EMAIL, userInfo.getEmail());
        sharedPref.saveValueToPref(this, AppConst.KEY_USER_NAME, userInfo.getName());
        sharedPref.saveValueToPref(this, AppConst.KEY_USER_NAME, userInfo.getName());
        sharedPref.saveValueToPref(this, AppConst.IS_USER_LOGGED_IN, "true");
    }

    private RegistrationBody userInfo;

    private void userRegistration() {
        binding.userNameLay.setError(null);
        binding.emailLay.setError(null);

        String name = binding.userName.getText().toString();
        String email = binding.email.getText().toString();

        if (TextUtils.isEmpty(name)) {
            binding.userNameLay.setError("Enter Name");
            return;
        }

        if (TextUtils.isEmpty(email)) {
            binding.emailLay.setError("Enter Email");
            return;
        }

        userInfo = new RegistrationBody(name, email, phone);
        viewModel.registerUser(userInfo);
    }
}