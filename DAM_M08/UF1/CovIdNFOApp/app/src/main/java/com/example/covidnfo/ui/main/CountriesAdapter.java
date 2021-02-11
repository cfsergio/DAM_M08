package com.example.covidnfo.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.covidnfo.R;

import java.util.List;

/**
 * https://carlesgm.gitlab.io/tutorials-m08/3-client-themovie-db/6-personalitzar-dades-listview/1-adapter-personalitzat/
 */
public class CountriesAdapter extends ArrayAdapter<Country> {
    private List<Country> countriesList;

    public CountriesAdapter(Context context, int resource, List<Country> objects){
        super(context,resource,objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        Country country = getItem(position);

        if (convertView  == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView  = inflater.inflate(R.layout.countries_adapter, parent, false);
        }

        TextView nombre = convertView.findViewById(R.id.tvCountryName);
        // TextView tipo = convertView.findViewById(R.id.tv_tipo);
        ImageView imagen = convertView.findViewById(R.id.ivFlag);

        assert country != null;
        nombre.setText(country.getCountry());
        System.out.println("- - - - - - " + country.getFlag());

        Glide.with(getContext()).load(country.getFlag()).apply(new RequestOptions().override(80,55)).into(imagen);

        return  convertView;
    }

}
