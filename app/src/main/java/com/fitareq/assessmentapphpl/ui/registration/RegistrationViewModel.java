package com.fitareq.assessmentapphpl.ui.registration;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.fitareq.assessmentapphpl.data.model.RegistrationBody;
import com.fitareq.assessmentapphpl.data.model.Resource;
import com.fitareq.assessmentapphpl.data.repository.RegistrationRepository;

public class RegistrationViewModel extends ViewModel {
    public LiveData<Resource<String>> registerResponse;
    private RegistrationRepository repository;
    public RegistrationViewModel() {
        repository = new RegistrationRepository();
        registerResponse = repository.getRegisterResponse();
    }
    public void registerUser(RegistrationBody body) {
        repository.registerUser(body);
    }
}
