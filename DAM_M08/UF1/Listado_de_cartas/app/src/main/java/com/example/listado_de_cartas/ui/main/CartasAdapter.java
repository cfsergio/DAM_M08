package com.example.listado_de_cartas.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.listado_de_cartas.R;

import java.util.List;

/**
 * https://carlesgm.gitlab.io/tutorials-m08/3-client-themovie-db/6-personalitzar-dades-listview/1-adapter-personalitzat/
 */
public class CartasAdapter extends ArrayAdapter<Carta> {
    public CartasAdapter(Context context, int resource, List<Carta> objects){
        super(context,resource,objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        Carta carta = getItem(position);

        if (convertView  == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView  = inflater.inflate(R.layout.cartas_adapter, parent, false);
        }

        TextView nombre= convertView.findViewById(R.id.tv_nombre);
        TextView tipo = convertView.findViewById(R.id.tv_tipo);
        ImageView imagen = convertView.findViewById(R.id.img_carta);

        assert carta != null;
        nombre.setText(carta.getNombre());
        tipo.setText(carta.getTipo());
        System.out.println("- - - - - - " + carta.getImagen());

        Glide.with(getContext()).load(carta.getImagen()).apply(new RequestOptions().override(825,225)).into(imagen);

        return  convertView;
    }
}
