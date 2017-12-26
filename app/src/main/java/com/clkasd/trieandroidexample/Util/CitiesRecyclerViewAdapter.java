package com.clkasd.trieandroidexample.Util;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clkasd.trieandroidexample.Model.City;
import com.clkasd.trieandroidexample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aykutcelik on 21.12.2017.
 */

public class CitiesRecyclerViewAdapter extends RecyclerView.Adapter<CitiesRecyclerViewAdapter.ViewHolder> {

    CityClickedListener cityClickedListener;
    List<City> citiesList = new ArrayList<>();

    public CitiesRecyclerViewAdapter(List<City> citiesList, CityClickedListener listener) {
        cityClickedListener = listener;
        this.citiesList.addAll(citiesList);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.item_city, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final City city = citiesList.get(position);
        holder.cityName.setText(city.getName() + "," + city.getCountry());
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cityClickedListener != null)
                    cityClickedListener.onCityClicked(city);
            }
        });
    }

    @Override
    public int getItemCount() {
        return citiesList.size();
    }

    public void setItemList(List<City> itemList) {
        this.citiesList = itemList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView cityName;
        RelativeLayout itemLayout;

        public ViewHolder(View v) {
            super(v);
            cityName = itemView.findViewById(R.id.cityName);
            itemLayout = itemView.findViewById(R.id.item_layout);
        }
    }

    public interface CityClickedListener {
        void onCityClicked(City city);
    }
}