package com.fitareq.assessmentapphpl.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.fitareq.assessmentapphpl.R;
import com.fitareq.assessmentapphpl.data.base.BaseActivity;
import com.fitareq.assessmentapphpl.data.model.TaskHistoryBody;
import com.fitareq.assessmentapphpl.databinding.ActivityHistoryBinding;
import com.fitareq.assessmentapphpl.databinding.ActivityMainBinding;
import com.fitareq.assessmentapphpl.ui.main.HistoryAdapter;
import com.fitareq.assessmentapphpl.ui.main.MainActivity;
import com.fitareq.assessmentapphpl.ui.main.MainViewModel;
import com.fitareq.assessmentapphpl.ui.registration.RegistrationActivity;
import com.fitareq.assessmentapphpl.utils.AppConst;
import com.fitareq.assessmentapphpl.utils.SharedPref;

public class HistoryActivity extends BaseActivity {
    private ActivityHistoryBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.taskHistory.observe(this, histories->{
            switch (histories.status) {
                case LOADING -> showLoadingScreen();
                case SUCCESS -> {
                    HistoryAdapter adapter = new HistoryAdapter(histories.data);
                    binding.history.setAdapter(adapter);
                    hideLoadingScreen();
                }
                case ERROR -> hideLoadingScreen();
            }
        });

        SharedPref sharedPref = new SharedPref();
        String phoneNumber = sharedPref.getValueFromPref(this, AppConst.KEY_USER_PHONE);

        TaskHistoryBody body = new TaskHistoryBody(phoneNumber);

        viewModel.getTaskHistory(body);
    }
}