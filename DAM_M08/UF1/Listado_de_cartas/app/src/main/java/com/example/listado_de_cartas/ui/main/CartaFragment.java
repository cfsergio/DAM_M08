package com.example.listado_de_cartas.ui.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.listado_de_cartas.R;

import java.util.ArrayList;
import java.util.List;

public class CartaFragment extends Fragment {
    private ArrayList<Carta> items;
    private CartasAdapter adapter;
    private CartasViewModel model;
    private SharedViewModel sharedModel;
    private CartaViewModel mViewModel;

    public static CartaFragment newInstance() {
        return new CartaFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.carta_fragment, container, false);

        items = new ArrayList<>();
        ListView lvpelis = view.findViewById(R.id.lvcartas);

        adapter = new CartasAdapter(
                getContext(),
                R.layout.cartas_adapter,
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
        lvpelis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int i, long l) {
                Carta carta = (Carta) adapter.getItemAtPosition(i);

                if (!esTablet()) {
                    Intent intent = new Intent(getContext(), DetalleCartaActivity.class);
                    intent.putExtra("Carta", carta);
                    startActivity(intent);
                } else {
                    sharedModel.select(carta);
                }
            }
        });

        /**
         * https://carlesgm.gitlab.io/tutorials-m08/3-client-themovie-db/9-base-de-dades/5-viewmodel/
         */

        model = new ViewModelProvider(this).get(CartasViewModel.class);
        //ViewModelProviders.of(this).get(CartasViewModel.class);
        model.getCartas().observe(getViewLifecycleOwner(), cartas -> {
            adapter.clear();
            adapter.addAll(cartas);
        });
        return view;
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
        mViewModel = new ViewModelProvider(this).get(CartaViewModel.class);
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

    private class RefreshDataTask extends AsyncTask<Void, Void, ArrayList<Carta>> {
        @Override
        protected ArrayList<Carta> doInBackground(Void... voids) {
            CartasAPI api = new CartasAPI();
            ArrayList<Carta> result = api.getCartas();
            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<Carta> cartas) {
            adapter.clear();
            for (Carta carta : cartas) {
                adapter.add(carta);
            }
        }
    }
}

