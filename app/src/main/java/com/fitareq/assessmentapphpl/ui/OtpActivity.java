package com.fitareq.assessmentapphpl.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.fitareq.assessmentapphpl.R;
import com.fitareq.assessmentapphpl.data.base.BaseActivity;
import com.fitareq.assessmentapphpl.databinding.ActivityOtpBinding;
import com.fitareq.assessmentapphpl.ui.registration.RegistrationActivity;
import com.fitareq.assessmentapphpl.utils.AppConst;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OtpActivity extends BaseActivity {
    private ActivityOtpBinding binding;
    private FirebaseAuth mAuth;
    private String verificationId;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        phone = getIntent().getStringExtra(AppConst.KEY_USER_PHONE);
        binding.message.setText(getString(R.string.otp_send_message, phone));
        if (phone != null) {
            sendOtp(phone);
        }

        binding.continueBtn.setOnClickListener(view -> {
            String otpCode = binding.otpView.getText().toString();
            if (!otpCode.isEmpty()) {
                binding.continueBtn.setEnabled(false);
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, otpCode);
                signInWithCredential(credential);
            } else {
                Toast.makeText(this, "Please enter otp!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendOtp(String phone) {
        showLoadingScreen();
        PhoneAuthOptions phoneAuthOptions = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phone)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(callbacks)
                .build();

        PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            if (phoneAuthCredential.getSmsCode() != null) {
                binding.otpView.setText(phoneAuthCredential.getSmsCode());
            }
            binding.continueBtn.setEnabled(false);
            Toast.makeText(OtpActivity.this, "Phone verified successfully.", Toast.LENGTH_SHORT).show();
            startActivity(
                    new Intent(OtpActivity.this, RegistrationActivity.class)
                            .putExtra(AppConst.KEY_USER_PHONE, phone)
            );
            finish();
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(OtpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
            hideLoadingScreen();
        }
    };

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Intent i = new Intent(OtpActivity.this, RegistrationActivity.class);
                        i.putExtra(AppConst.KEY_USER_PHONE, phone);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(OtpActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        binding.continueBtn.setEnabled(true);
                    }
                });
    }


}