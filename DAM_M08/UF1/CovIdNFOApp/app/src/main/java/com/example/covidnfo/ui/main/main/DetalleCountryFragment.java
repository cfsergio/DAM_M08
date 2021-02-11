package com.example.covidnfo.ui.main.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.covidnfo.R;
import com.example.covidnfo.ui.main.Country;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.models.BarModel;


/**
 * https://carlesgm.gitlab.io/tutorials-m08/3-client-themovie-db/7-detall/4-carregar-movie-detall/
 */
public class DetalleCountryFragment extends Fragment {
    private View view;
    private DetalleCountryViewModel mViewModel;
    private TextView tvCountry,tvCases,tvRecovered,tvCritical,tvActive,tvTodayCases,tvTotalDeaths,tvTodayDeaths;
    private BarChart mBarChart;

    public static DetalleCountryFragment newInstance() {
        return new DetalleCountryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.detalle_country_fragment, container, false);

        Intent i = getActivity().getIntent();

        if (i != null) {
            Country country = (Country) i.getSerializableExtra("country");

            if (country != null) {
                updateUi(country);
            }
        }



        return view;
    }

/*
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.detalle_country_fragment, container, false);
        Intent i = getActivity().getIntent();
        if (i != null) {
            Country country = (Country) i.getSerializableExtra("Carta");
            if (country != null) {
                updateUi(country);
            }
        }

        SharedViewModel sharedModel = new ViewModelProvider(this).get(SharedViewModel.class);
        //ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        sharedModel.getSelected().observe(this, new Observer<Country>() {
            @Override
            public void onChanged(@Nullable Country country) {
                updateUi(country);
            }
        });

        return view;
    }*/

    @SuppressLint("SetTextI18n")
    private void updateUi(Country country) {
        TextView countryName = view.findViewById(R.id.tvCountry);
        TextView cases = view.findViewById(R.id.tvCases);
        TextView todayCases = view.findViewById(R.id.tvTodayCases);
        TextView deaths = view.findViewById(R.id.tvDeaths);
        TextView todayDeaths = view.findViewById(R.id.tvTodayDeaths);
        TextView recovered = view.findViewById(R.id.tvRecovered);
        TextView active = view.findViewById(R.id.tvActive);
        TextView critical = view.findViewById(R.id.tvCritical);
        TextView population = view.findViewById(R.id.tvPopulation);

        mBarChart = view.findViewById(R.id.barchart);

        countryName.setText(country.getCountry());
        cases.setText(country.getCases());
        todayCases.setText(country.getTodayCases());
        deaths.setText(country.getDeaths());
        todayDeaths.setText(country.getTodayDeaths());
        recovered.setText(country.getRecovered());
        active.setText(country.getActive());
        critical.setText(country.getCritical());
        population.setText(country.getPopulation());

        //mBarChart.addBar(new BarModel("Population", Integer.parseInt(country.getPopulation()), Color.parseColor("#04FFE8")));
        mBarChart.addBar(new BarModel("Cases", Integer.parseInt(country.getCases()), Color.parseColor("#FFA726")));
        mBarChart.addBar(new BarModel("Active", Integer.parseInt(country.getActive()), Color.parseColor("#29B6F6")));
        mBarChart.addBar(new BarModel("Recovered", Integer.parseInt(country.getRecovered()), Color.parseColor("#66BB6A")));
        mBarChart.addBar(new BarModel("Deaths", Integer.parseInt(country.getDeaths()), Color.parseColor("#EF5350")));
        mBarChart.startAnimation();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetalleCountryViewModel.class);
        // TODO: Use the ViewModel
    }

}