package com.fitareq.assessmentapphpl.data.api;

import com.fitareq.assessmentapphpl.data.model.NewTaskBody;
import com.fitareq.assessmentapphpl.data.model.RegistrationBody;
import com.fitareq.assessmentapphpl.data.model.TaskHistory;
import com.fitareq.assessmentapphpl.data.model.TaskHistoryBody;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("Registration")
    Call<ResponseBody> userRegistration(@Body RegistrationBody body);

    @POST("TaskEntry")
    Call<ResponseBody> addNewTask(@Body NewTaskBody body);

    @POST("TaskData")
    Call<ResponseBody> getHistory(@Body TaskHistoryBody body);
}
