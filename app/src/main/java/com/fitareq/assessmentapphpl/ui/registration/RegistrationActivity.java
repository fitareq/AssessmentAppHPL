package com.fitareq.assessmentapphpl.ui.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;

import com.fitareq.assessmentapphpl.R;
import com.fitareq.assessmentapphpl.databinding.ActivityRegistrationBinding;

public class RegistrationActivity extends AppCompatActivity {
    private ActivityRegistrationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String phone = getIntent().getStringExtra("phone");
        if (phone != null) {
            binding.phoneNumber.setText(phone);
        }
        binding.register.setOnClickListener(view -> {
            binding.userNameLay.setError(null);
            binding.emailLay.setError(null);

            String name = binding.userName.getText().toString();
            String email = binding.email.getText().toString();
            if (TextUtils.isEmpty(name)) {
                binding.userNameLay.setError("Enter Name");
            }

            if (TextUtils.isEmpty(email)) {
                binding.emailLay.setError("Enter Email");
            }
        });
    }
}