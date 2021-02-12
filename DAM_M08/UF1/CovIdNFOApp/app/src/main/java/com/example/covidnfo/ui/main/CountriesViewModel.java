package com.example.covidnfo.ui.main;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class CountriesViewModel extends AndroidViewModel {
    private final Application app;
    private final AppDatabase appDatabase;
    private final CountryDAO countryDao;
    private LiveData<List<Country>> countries;

    public CountriesViewModel(Application application) {
        super(application);
        this.app = application;
        this.appDatabase = AppDatabase.getDatabase(
                this.getApplication());
        this.countryDao = appDatabase.getCountryDAO();
    }

    public LiveData<List<Country>> getCountries() {
        return countryDao.getCountries();
    }


    public void reload() {
        // do async operation to fetch users
        RefreshDataTask task = new RefreshDataTask();
        task.execute();
    }

    private class RefreshDataTask extends AsyncTask<Void, Void, ArrayList<Country>> {
        @Override
        protected ArrayList<Country> doInBackground(Void... voids) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(
                    app.getApplicationContext()
            );
            CountryAPI api = new CountryAPI();
            ArrayList<Country> result;
            result = api.getCountries();
            countryDao.deleteCountries();
            countryDao.addCountries(result);
            return null;
        }
    }
}

