package com.example.listado_de_cartas.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.listado_de_cartas.R;
import com.example.listado_de_cartas.ui.main.ui.main.DetalleCartaFragment;

public class DetalleCartaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_carta_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, DetalleCartaFragment.newInstance())
                    .commitNow();
        }
    }
}