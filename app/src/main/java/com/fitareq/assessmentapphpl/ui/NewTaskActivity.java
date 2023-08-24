package com.fitareq.assessmentapphpl.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.fitareq.assessmentapphpl.R;
import com.fitareq.assessmentapphpl.data.base.BaseActivity;
import com.fitareq.assessmentapphpl.data.model.NewTaskBody;
import com.fitareq.assessmentapphpl.databinding.ActivityHistoryBinding;
import com.fitareq.assessmentapphpl.databinding.ActivityNewTaskBinding;
import com.fitareq.assessmentapphpl.ui.main.HistoryAdapter;
import com.fitareq.assessmentapphpl.ui.main.MainViewModel;
import com.fitareq.assessmentapphpl.utils.AppConst;
import com.fitareq.assessmentapphpl.utils.SharedPref;

public class NewTaskActivity extends BaseActivity {
    private ActivityNewTaskBinding binding;
    private MainViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        binding.create.setOnClickListener(view -> {
            String title = binding.title.getText().toString();
            String description = binding.description.getText().toString();
            SharedPref sharedPref = new SharedPref();
            String phoneNumber = sharedPref.getValueFromPref(this, AppConst.KEY_USER_PHONE);

            NewTaskBody body = new NewTaskBody(title, description, phoneNumber);
            viewModel.addNewTask(body);
        });

        viewModel.newTaskResponse.observe(this, newTask->{
            switch (newTask.status) {
                case LOADING -> showLoadingScreen();
                case SUCCESS -> {
                    Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
                    finish();
                    hideLoadingScreen();
                }
                case ERROR -> {
                    Toast.makeText(this, newTask.message, Toast.LENGTH_SHORT).show();
                    hideLoadingScreen();
                }
            }
        });
    }
}