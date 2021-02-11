package com.example.covidnfo.ui.main;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * https://carlesgm.gitlab.io/tutorials-m08/3-client-themovie-db/9-base-de-dades/3-definir-dao/
 */

@Dao
public interface CountryDAO {
    @Query("SELECT * FROM Country")
    LiveData<List<Country>> getCountries();

    @Insert
    void addCountry(Country country);

    @Insert
    void addCountries(List<Country> countries);

    @Delete
    void deleteCarta(Country country);

    @Query("DELETE FROM Country")
    void deleteCountries();
}

