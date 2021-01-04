package com.example.contadordevides.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.contadordevides.R;

public class MainFragment extends Fragment {
    private TextView counter1;
    private TextView counter2;

    private int life1;
    private int life2;
    private int poison1;
    private int poison2;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            life1 = savedInstanceState.getInt("vida1");
            life2 = savedInstanceState.getInt("vida2");
            poison1 = savedInstanceState.getInt("poison1");
            poison2 = savedInstanceState.getInt("poison2");
        } else {
            reset();
        }

        final View view = inflater.inflate(R.layout.main_fragment, container, false);

        Button p1poisonmore = view.findViewById(R.id.p1poisonmore);
        Button p1poisonless = view.findViewById(R.id.p1poisonless);

        Button p2poisonmore = view.findViewById(R.id.p2poisonmore);
        Button p2poisonless = view.findViewById(R.id.p2poisonless);

        ImageButton lifetwoone = view.findViewById(R.id.lifetwotoone);
        ImageButton lifeonetotwo = view.findViewById(R.id.lifeonetotwo);

        ImageButton p1lifemore = view.findViewById(R.id.p1lifemore);
        ImageButton p1lifeless = view.findViewById(R.id.p1lifeless);

        ImageButton p2lifemore = view.findViewById(R.id.p2lifemore);
        ImageButton p2lifeless = view.findViewById(R.id.p2lifeless);

        counter1 = view.findViewById(R.id.counter1);
        counter2 = view.findViewById(R.id.counter2);

        View.OnClickListener listener = new View.OnClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.lifeonetotwo:
                        life1--;
                        life2++;
                        break;
                    case R.id.lifetwotoone:
                        life1++;
                        life2--;
                        break;

                    case R.id.p1poisonmore:
                        poison1++;
                        break;

                    case R.id.p1poisonless:
                        poison1--;
                        break;

                    case R.id.p2poisonmore:
                        poison2++;
                        break;

                    case R.id.p2poisonless:
                        poison2--;
                        break;

                    case R.id.p1lifemore:
                        life1++;
                        break;

                    case R.id.p1lifeless:
                        life1--;
                        break;

                    case R.id.p2lifemore:
                        life2++;
                        break;

                    case R.id.p2lifeless:
                        life2--;
                        break;
                }
                updateView();
            }
        };

        updateView();

        p1poisonless.setOnClickListener(listener);
        p1poisonmore.setOnClickListener(listener);
        p2poisonmore.setOnClickListener(listener);
        p2poisonless.setOnClickListener(listener);
        lifetwoone.setOnClickListener(listener);
        lifeonetotwo.setOnClickListener(listener);
        p1lifemore.setOnClickListener(listener);
        p1lifeless.setOnClickListener(listener);
        p2lifemore.setOnClickListener(listener);
        p2lifeless.setOnClickListener(listener);
        counter1.setOnClickListener(listener);
        counter2.setOnClickListener(listener);

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("vida1", life1);
        outState.putInt("vida2", life2);
        outState.putInt("poison1", poison1);
        outState.putInt("poison2", poison2);
        super.onSaveInstanceState(outState);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ViewModelProviders.of(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

    private void reset() {
        poison1 = 0;
        poison2 = 0;
        life1 = 25;
        life2 = 25;
    }

    @SuppressLint("DefaultLocale")
    private void updateView() {
        counter1.setText(String.format("%d/%d", life1, poison1));
        counter2.setText(String.format("%d/%d", life2, poison2));
    }
}