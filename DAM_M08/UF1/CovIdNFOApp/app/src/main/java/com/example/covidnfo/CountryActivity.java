package com.example.covidnfo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.covidnfo.ui.main.CountryFragment;

public class CountryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, CountryFragment.newInstance())
                    .commitNow();
        }

    }
}