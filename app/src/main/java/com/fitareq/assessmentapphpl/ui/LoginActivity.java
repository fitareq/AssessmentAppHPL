package com.fitareq.assessmentapphpl.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.fitareq.assessmentapphpl.R;
import com.fitareq.assessmentapphpl.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.next.setOnClickListener(view -> {
            binding.phoneNumberLay.setError(null);
            String phone = binding.phoneNumber.getText().toString();

            if (TextUtils.isEmpty(phone)) {
                binding.phoneNumberLay.setError("Enter phone number");
                return;
            }
            if (phone.length() < 11) {
                binding.phoneNumberLay.setError("Phone must be at least 11 digit");
                return;
            }
            if (phone.contains("+88")) {
                phone = phone.replace("+88", "");
            }

            startActivity(new Intent(this, OtpActivity.class).putExtra("phone", phone));
            this.finish();
        });

    }
}