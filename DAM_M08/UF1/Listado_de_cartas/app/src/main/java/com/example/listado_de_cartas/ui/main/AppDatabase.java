package com.example.listado_de_cartas.ui.main;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * https://carlesgm.gitlab.io/tutorials-m08/3-client-themovie-db/9-base-de-dades/4-definir-bd/
 */
@Database(entities = {Carta.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class, "db"
                    ).build();
        }
        return INSTANCE;
    }

    public abstract CartaDAO getCartaDAO();
}

