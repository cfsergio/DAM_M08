package com.example.listado_de_cartas.ui.main;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * https://carlesgm.gitlab.io/tutorials-m08/3-client-themovie-db/4-carregar-dades-internet/4-urls/
 */
public class CartasAPI {
    private final String BASE_URL ="https://api.magicthegathering.io/";

    ArrayList<Carta> getCartas(){
        Uri bulrUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("v1")
                .appendPath("cards")
                .build();
        String url = bulrUri.toString();
        return doCall(url);
    }

    private ArrayList<Carta>  doCall(String url) {
        try {
            String JsonResponse = HttpUtils.get(url);
            return processJson(JsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * https://carlesgm.gitlab.io/tutorials-m08/3-client-themovie-db/4-carregar-dades-internet/8-parsejar-json/
     * @param jsonResponse
     * @return
     */
    private ArrayList<Carta> processJson(String jsonResponse) {
        ArrayList<Carta> Cartas = new ArrayList<>();
        try {
            JSONObject data = new JSONObject(jsonResponse);
            JSONArray jsonCartas = data.getJSONArray("cards");
            for (int i = 0; i <jsonCartas.length() ; i++) {
                JSONObject jsoncarta = jsonCartas.getJSONObject(i);
                Carta carta = new Carta();
                try {
                    carta.setNombre(jsoncarta.getString("name"));
                    carta.setColores(jsoncarta.getString("colors"));
                    carta.setTipo(jsoncarta.getString("type"));
                    carta.setTexto(jsoncarta.getString("text"));
                    carta.setImagen(jsoncarta.getString("imageUrl"));
                    carta.setRareza(jsoncarta.getString("rarity"));
                    Cartas.add(carta);
                }catch (Exception e){
                    System.out.println("Revisa los datos. La carta no pudo ser agregada.");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Cartas;
    }
}
