package com.practice.miskaaassigment.viewholder;

import android.net.Uri;

import androidx.recyclerview.widget.RecyclerView;

import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.practice.api.model.Country;
import com.practice.miskaaassigment.R;
import com.practice.miskaaassigment.databinding.CountryLayoutBinding;
import com.practice.miskaaassigment.utility.ImageUtility;
import com.practice.miskaaassigment.utility.RecyclerViewItemClickListener;


public class CountryViewHolder extends RecyclerView.ViewHolder {
    private final CountryLayoutBinding countryLayoutBinding;
    private final RecyclerViewItemClickListener recyclerViewItemClickListener;

    public CountryViewHolder(RecyclerViewItemClickListener recyclerViewItemClickListener,
                             CountryLayoutBinding countryLayoutBinding) {
        super(countryLayoutBinding.getRoot());
        this.recyclerViewItemClickListener = recyclerViewItemClickListener;
        this.countryLayoutBinding = countryLayoutBinding;
    }

    public void bind(Country country) {
        countryLayoutBinding.tvCountryName.setText(country.getCountryName());
        ImageUtility.setImage(country.getCountryFlag(),
                countryLayoutBinding.countryImage,
                countryLayoutBinding.getRoot().getContext());
        countryLayoutBinding.getRoot().setOnClickListener((v) -> {
            recyclerViewItemClickListener.details(country.getCountryName());
        });
        countryLayoutBinding.delete.setOnClickListener((v) -> {
            recyclerViewItemClickListener.delete(country);
        });
    }
}
