package com.example.listado_de_cartas.ui.main;

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
public interface CartaDAO {
    @Query("SELECT * FROM carta")
    LiveData<List<Carta>> getCartas();

    @Insert
    void addCarta(Carta carta);

    @Insert
    void addCartas(List<Carta> cartas);

    @Delete
    void deleteCarta(Carta carta);

    @Query("DELETE FROM carta")
    void deleteCartas();
}

