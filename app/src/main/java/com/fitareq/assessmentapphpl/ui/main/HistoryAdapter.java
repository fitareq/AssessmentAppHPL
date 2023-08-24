package com.fitareq.assessmentapphpl.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fitareq.assessmentapphpl.data.model.TaskHistory;
import com.fitareq.assessmentapphpl.databinding.ItemHistoryBinding;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.mViewModel> {
    private List<TaskHistory> items;
    public HistoryAdapter(List<TaskHistory> items){
        this.items = items;
    }
    @NonNull
    @Override
    public mViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new mViewModel(ItemHistoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull mViewModel holder, int position) {
        TaskHistory current = items.get(position);
        String title = current.getTitle();
        String details = current.getDetails();
        String phone = current.getContactNumber();
        String date = current.getEntryDate();

        holder.binding.title.setText(title);
        holder.binding.details.setText(details);
        holder.binding.phone.setText(phone);
        holder.binding.date.setText(date);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class mViewModel extends RecyclerView.ViewHolder {
        ItemHistoryBinding binding;

        public mViewModel(@NonNull ItemHistoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
