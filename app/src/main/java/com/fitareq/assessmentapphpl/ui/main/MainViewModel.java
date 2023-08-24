package com.fitareq.assessmentapphpl.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.fitareq.assessmentapphpl.data.model.NewTaskBody;
import com.fitareq.assessmentapphpl.data.model.RegistrationBody;
import com.fitareq.assessmentapphpl.data.model.Resource;
import com.fitareq.assessmentapphpl.data.model.TaskHistory;
import com.fitareq.assessmentapphpl.data.model.TaskHistoryBody;
import com.fitareq.assessmentapphpl.data.repository.MainRepository;
import com.fitareq.assessmentapphpl.data.repository.RegistrationRepository;

import java.util.List;

public class MainViewModel extends ViewModel {
    public LiveData<Resource<String>> newTaskResponse;
    public LiveData<Resource<List<TaskHistory>>> taskHistory;
    private MainRepository repository;
    public MainViewModel() {
        repository = new MainRepository();
        newTaskResponse = repository.getNewTaskResponse();
        taskHistory = repository.getTaskHistory();
    }
    public void addNewTask(NewTaskBody body ) {
        repository.addNewTask(body);
    }
    public void getTaskHistory(TaskHistoryBody body) {
        repository.getAllHistory(body);
    }
}
