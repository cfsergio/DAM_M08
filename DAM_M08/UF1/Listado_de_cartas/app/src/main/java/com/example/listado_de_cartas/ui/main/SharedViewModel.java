package com.example.listado_de_cartas.ui.main;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<Carta> selected = new MutableLiveData<Carta>();

    public void select(Carta carta) {
        selected.setValue(carta);
    }

    public LiveData<Carta> getSelected() {
        return selected;
    }
}
