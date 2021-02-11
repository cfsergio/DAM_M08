package com.example.covidnfo.ui.main;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * https://carlesgm.gitlab.io/tutorials-m08/3-client-themovie-db/4-carregar-dades-internet/4-urls/
 */
public class CountryAPI {
    private final String BASE_URL = "https://corona.lmao.ninja/";

    ArrayList<Country> getCountries() {
        Uri bulrUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("v2")
                .appendPath("countries")
                .build();
        String url = bulrUri.toString();
        return doCall(url);
    }

    private ArrayList<Country> doCall(String url) {
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
     *
     * @param jsonResponse
     * @return
     */
    private ArrayList<Country> processJson(String jsonResponse) {
        ArrayList<Country> countries = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(jsonResponse);

            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Country country = new Country();

                    country.setCountry(jsonObject.getString("country"));
                    country.setCases(jsonObject.getString("cases"));
                    country.setTodayCases(jsonObject.getString("todayCases"));
                    country.setDeaths(jsonObject.getString("deaths"));
                    country.setTodayDeaths(jsonObject.getString("todayDeaths"));
                    country.setRecovered(jsonObject.getString("recovered"));
                    country.setTodayRecovered(jsonObject.getString("todayRecovered"));
                    country.setActive(jsonObject.getString("active"));
                    country.setCritical(jsonObject.getString("critical"));
                    country.setTests(jsonObject.getString("tests"));
                    country.setPopulation(jsonObject.getString("population"));

                    JSONObject object = jsonObject.getJSONObject("countryInfo");
                    country.setFlag(object.getString("flag"));

                    countries.add(country);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.out.println("Revisa los datos. El pais no pudo ser agregado.");
            System.out.println(e);
        }
        return countries;
    }
}
