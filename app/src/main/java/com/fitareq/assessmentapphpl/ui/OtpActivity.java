package com.fitareq.assessmentapphpl.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.fitareq.assessmentapphpl.R;
import com.fitareq.assessmentapphpl.databinding.ActivityOtpBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthOptions;

public class OtpActivity extends AppCompatActivity {
    private ActivityOtpBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        String phone = getIntent().getStringExtra("phone");

        if (phone != null) {
            sendOtp(phone);
        }
    }

    private void sendOtp(String phone) {
        PhoneAuthOptions phoneAuthOptions = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phone)
                .build();
    }
}