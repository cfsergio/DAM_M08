package com.example.covidnfo.ui.main;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.covidnfo.R;
import com.leo.simplearcloader.SimpleArcLoader;

import java.util.ArrayList;

public class CountryFragment extends Fragment {
    private ArrayList<Country> items;
    private CountriesAdapter adapter;
    //private CartasViewModel model;
    private SharedViewModel sharedModel;
    private CountryViewModel mViewModel;
    EditText edtSearch;
    SimpleArcLoader simpleArcLoader;

    public static CountryFragment newInstance() {
        return new CountryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.country_fragment, container, false);

        items = new ArrayList<>();
        ListView lvpelis = view.findViewById(R.id.lvcartas);

        edtSearch = view.findViewById(R.id.pt_buscador);

        adapter = new CountriesAdapter(
                getContext(),
                R.layout.countries_adapter,
                items
        );

        sharedModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        //new ViewModelProvider(this).get(SharedViewModel.class);

        lvpelis.setAdapter(adapter);

        refresh();

        /**
         * https://carlesgm.gitlab.io/tutorials-m08/3-client-themovie-db/7-detall/3-capturar-clicks-listview/
         */
        //lvpelis.setOnItemClickListener((adapter, fragment, i, l) -> {
        simpleArcLoader = view.findViewById(R.id.loader);
        simpleArcLoader.start();
        lvpelis.setOnItemClickListener((adapter, fragment, i, l) -> {
            Country country = (Country) adapter.getItemAtPosition(i);

            Intent intent = new Intent(getContext(), DetalleCountryActivity.class);
            intent.putExtra("country", country);
            startActivity(intent);
            simpleArcLoader.stop();
            simpleArcLoader.setVisibility(View.GONE);
        });

        return view;




        /*
        lvpelis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int i, long l) {
                Country country = (Country) adapter.getItemAtPosition(i);

                if (!esTablet()) {
                    Intent intent = new Intent(getContext(), DetalleCartaActivity.class);
                    intent.putExtra("Country",  country);
                    startActivity(intent);
                } else {
                    sharedModel.select(country);
                }
            }
        });
        return view;*/



        /**
         * https://carlesgm.gitlab.io/tutorials-m08/3-client-themovie-db/9-base-de-dades/5-viewmodel/
         */
/*
        model = new ViewModelProvider(this).get(CartasViewModel.class);
        //ViewModelProviders.of(this).get(CartasViewModel.class);
        model.getCartas().observe(getViewLifecycleOwner(), cartas -> {
            adapter.clear();
            adapter.addAll(cartas);
        });*/
//        return view;
/*
        model.getCartas().observe(getViewLifecycleOwner(), new Observer<List<Carta>>() {
            @Override
            public void onChanged(@Nullable List<Carta> cartas) {
                adapter.clear();
                adapter.addAll(cartas);
            }
        });
        return view;*/
    }

    boolean esTablet() {
        return getResources().getBoolean(R.bool.tablet);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CountryViewModel.class);
        // TODO: Use the ViewModel
    }

    /**
     * https://carlesgm.gitlab.io/tutorials-m08/3-client-themovie-db/4-carregar-dades-internet/7-menu-refresh/
     *
     * @param menu
     * @param inflater
     */
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_cartas, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            refresh();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void refresh() {
        RefreshDataTask task = new RefreshDataTask();
        task.execute();
        //model.reload();
    }

    private class RefreshDataTask extends AsyncTask<Void, Void, ArrayList<Country>> {
        @Override
        protected ArrayList<Country> doInBackground(Void... voids) {
            CountryAPI api = new CountryAPI();
            ArrayList<Country> result = api.getCountries();
            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<Country> countries) {
            adapter.clear();
            for (Country country : countries) {
                adapter.add(country);
            }
        }
    }
}

