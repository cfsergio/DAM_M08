package com.example.listado_de_cartas.ui.main.ui.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.listado_de_cartas.R;
import com.example.listado_de_cartas.ui.main.Carta;
import com.example.listado_de_cartas.ui.main.SharedViewModel;


/**
 * https://carlesgm.gitlab.io/tutorials-m08/3-client-themovie-db/7-detall/4-carregar-movie-detall/
 */
public class DetalleCartaFragment extends Fragment {
    private View view;
    private DetalleCartaViewModel mViewModel;

    public static DetalleCartaFragment newInstance() {
        return new DetalleCartaFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.detalle_carta_fragment, container, false);
        Intent i = getActivity().getIntent();
        if (i != null) {
            Carta carta = (Carta) i.getSerializableExtra("Carta");
            if (carta != null) {
                updateUi(carta);
            }
        }

        SharedViewModel sharedModel = new ViewModelProvider(this).get(SharedViewModel.class);
        //ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        sharedModel.getSelected().observe(this, new Observer<Carta>() {
            @Override
            public void onChanged(@Nullable Carta carta) {
                updateUi(carta);
            }
        });

        return view;
    }

    @SuppressLint("SetTextI18n")
    private void updateUi(Carta carta) {
        Log.d("CARTA", carta.toString());
        TextView texto = view.findViewById(R.id.tv_texto);
        TextView nombre = view.findViewById(R.id.tv_nombre);
        TextView color = view.findViewById(R.id.tv_color);
        TextView rareza = view.findViewById(R.id.tv_rareza);
        TextView tipo = view.findViewById(R.id.tv_tipo);
        ImageView imagen = view.findViewById(R.id.iv_imagen);
        texto.setText(carta.getTexto());
        nombre.setText(carta.getNombre());
        tipo.setText(carta.getTipo());
        color.setText(carta.getColores());
        rareza.setText(carta.getRareza());
        Glide.with(getContext()).load(
                carta.getImagen()
        ).apply(new RequestOptions().override(1000, 500)).into(imagen);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetalleCartaViewModel.class);
        // TODO: Use the ViewModel
    }

}