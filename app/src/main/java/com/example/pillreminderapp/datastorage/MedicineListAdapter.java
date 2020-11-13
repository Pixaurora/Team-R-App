package com.example.pillreminderapp.datastorage;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class MedicineListAdapter extends ListAdapter<Medicine, MedicineViewHolder>
{

    public MedicineListAdapter(@NonNull DiffUtil.ItemCallback<Medicine> diffCallback) {
        super(diffCallback);
    }

    @Override
    public MedicineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return MedicineViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(MedicineViewHolder holder, int position) {
        Medicine current = getItem(position);
        holder.bind(current.getMedicine());
    }

    public static class MedicineDiff extends DiffUtil.ItemCallback<Medicine> {

        @Override
        public boolean areItemsTheSame(@NonNull Medicine oldItem, @NonNull Medicine newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Medicine oldItem, @NonNull Medicine newItem) {
            return oldItem.getMedicine().equals(newItem.getMedicine());
        }
    }
}