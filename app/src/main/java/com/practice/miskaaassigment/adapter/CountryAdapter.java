package com.practice.miskaaassigment.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.practice.api.model.Country;
import com.practice.miskaaassigment.databinding.CountryLayoutBinding;
import com.practice.miskaaassigment.utility.RecyclerViewItemClickListener;
import com.practice.miskaaassigment.viewholder.CountryViewHolder;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryViewHolder> {
    private final AsyncListDiffer<Country> asyncListDiffer =
            new AsyncListDiffer<>(this, new CountryDiffUtil());
    private final RecyclerViewItemClickListener recyclerViewItemClickListener;
    public CountryAdapter(RecyclerViewItemClickListener recyclerViewItemClickListener){
        this.recyclerViewItemClickListener = recyclerViewItemClickListener;
    }
    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CountryLayoutBinding countryLayoutBinding = CountryLayoutBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new CountryViewHolder(recyclerViewItemClickListener,countryLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.bind(asyncListDiffer.getCurrentList().get(position));
    }

    @Override
    public int getItemCount() {
        return asyncListDiffer.getCurrentList().size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void submitList(List<Country> countries) {
        asyncListDiffer.submitList(countries);
    }

    private static class CountryDiffUtil extends DiffUtil.ItemCallback<Country> {

        @Override
        public boolean areItemsTheSame(@NonNull Country oldItem, @NonNull Country newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Country oldItem, @NonNull Country newItem) {
            return oldItem.equals(newItem);
        }
    }

}
