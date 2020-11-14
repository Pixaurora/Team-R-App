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


        public static class ItemCallback<Medicine>
        {


        }
        /*
         * Called to check whether two objects represent the same item.
         * <p>
         * For example, if your items have unique ids, this method should check their id equality.
         * <p>
         * Note: {@code null} items in the list are assumed to be the same as another {@code null}
         * item and are assumed to not be the same as a non-{@code null} item. This callback will
         * not be invoked for either of those cases.
         *
         * @param oldItem The item in the old list.
         * @param newItem The item in the new list.
         * @return True if the two items represent the same object or false if they are different.
         *
         * @see DiffUtil.Callback#areItemsTheSame(int, int)
         */
    }
}