package com.fitareq.assessmentapphpl.data.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.fitareq.assessmentapphpl.data.api.Api;
import com.fitareq.assessmentapphpl.data.api.ApiService;
import com.fitareq.assessmentapphpl.data.model.RegistrationBody;
import com.fitareq.assessmentapphpl.data.model.Resource;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationRepository {
    private final ApiService apiService;

    private MutableLiveData<Resource<String>> registerResponse = new MutableLiveData<>();

    public RegistrationRepository() {
        apiService = Api.getInstance();
    }

    public MutableLiveData<Resource<String>> getRegisterResponse() {
        return registerResponse;
    }

    public void registerUser(RegistrationBody body) {
        registerResponse.postValue(Resource.loading());
        Call<ResponseBody> call = apiService.userRegistration(body);

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    try {
                        if (response.body() != null) {
                            String responseString = response.body().string();
                            registerResponse.postValue(Resource.success(responseString));
                        }
                    } catch (Exception e) {
                        registerResponse.postValue(Resource.error(e.getMessage()));
                    }
                } else {
                    try {
                        String responseString = response.errorBody().string();
                        registerResponse.postValue(Resource.error(responseString));
                    } catch (Exception e) {
                        registerResponse.postValue(Resource.error(e.getMessage()));
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                registerResponse.postValue(Resource.error(t.getMessage()));
            }
        });
    }

}
