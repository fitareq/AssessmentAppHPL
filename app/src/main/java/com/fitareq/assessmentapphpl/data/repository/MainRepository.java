package com.fitareq.assessmentapphpl.data.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.fitareq.assessmentapphpl.data.api.Api;
import com.fitareq.assessmentapphpl.data.api.ApiService;
import com.fitareq.assessmentapphpl.data.model.NewTaskBody;
import com.fitareq.assessmentapphpl.data.model.Resource;
import com.fitareq.assessmentapphpl.data.model.TaskHistory;
import com.fitareq.assessmentapphpl.data.model.TaskHistoryBody;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {
    private final ApiService apiService;
    private final MutableLiveData<Resource<String>> newTaskResponse = new MutableLiveData<>();

    public MainRepository() {
        apiService = Api.getInstance();
    }

    public MutableLiveData<Resource<String>> getNewTaskResponse() {
        return newTaskResponse;
    }

    public void addNewTask(NewTaskBody body) {
        newTaskResponse.postValue(Resource.loading());
        Call<ResponseBody> call = apiService.addNewTask(body);

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    try {
                        if (response.body() != null) {
                            String responseString = response.body().string();
                            newTaskResponse.postValue(Resource.success(responseString));
                        }
                    } catch (Exception e) {
                        newTaskResponse.postValue(Resource.error(e.getMessage()));
                    }
                } else {
                    try {
                        String responseString = response.errorBody().string();
                        newTaskResponse.postValue(Resource.error(responseString));
                    } catch (Exception e) {
                        newTaskResponse.postValue(Resource.error(e.getMessage()));
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                newTaskResponse.postValue(Resource.error(t.getMessage()));
            }
        });
    }


    private final MutableLiveData<Resource<List<TaskHistory>>> taskHistory = new MutableLiveData<>();

    public MutableLiveData<Resource<List<TaskHistory>>> getTaskHistory() {
        return taskHistory;
    }

    public void getAllHistory(TaskHistoryBody body) {
        taskHistory.postValue(Resource.loading());
        Call<ResponseBody> call = apiService.getHistory(body);

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    try {
                        String responseBodyString = response.body().string();
                        Gson gson = new Gson();
                        Type taskListType = new TypeToken<List<TaskHistory>>() {
                        }.getType();

                        List<TaskHistory> taskLists = gson.fromJson(responseBodyString, taskListType);
                        taskHistory.postValue(Resource.success(taskLists));
                    } catch (Exception e) {
                        taskHistory.postValue(Resource.error(e.getMessage()));
                    }
                } else {
                    try {
                        String responseString = response.errorBody().string();
                        taskHistory.postValue(Resource.error(responseString));
                    } catch (Exception e) {
                        taskHistory.postValue(Resource.error(e.getMessage()));
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                taskHistory.postValue(Resource.error(t.getMessage()));
            }
        });
    }
}
