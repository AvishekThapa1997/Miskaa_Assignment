package com.practice.miskaaassigment.utility;

import com.practice.api.model.Country;

public interface RecyclerViewItemClickListener {
    void delete(Country country);
    void details(String name);
}
