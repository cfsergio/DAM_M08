package com.example.listado_de_cartas.ui.main;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class CartasViewModel extends AndroidViewModel {
    private final Application app;
    private final AppDatabase appDatabase;
    private final CartaDAO cartaDao;
    private LiveData<List<Carta>> cartas;

    public CartasViewModel(Application application) {
        super(application);
        this.app = application;
        this.appDatabase = AppDatabase.getDatabase(
                this.getApplication());
        this.cartaDao = appDatabase.getCartaDAO();
    }

    public LiveData<List<Carta>> getCartas() {
        return cartaDao.getCartas();
    }


    public void reload() {
        // do async operation to fetch users
        RefreshDataTask task = new RefreshDataTask();
        task.execute();
    }

    private class RefreshDataTask extends AsyncTask<Void, Void, ArrayList<Carta>> {
        @Override
        protected ArrayList<Carta> doInBackground(Void... voids) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(
                    app.getApplicationContext()
            );
            CartasAPI api = new CartasAPI();
            ArrayList<Carta> result;
            result = api.getCartas();
            cartaDao.deleteCartas();
            cartaDao.addCartas(result);
            return null;
        }
    }
}

