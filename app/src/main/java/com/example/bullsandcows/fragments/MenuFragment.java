package com.example.bullsandcows.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bullsandcows.R;

public class MenuFragment extends Fragment {
    public static MenuFragment newInstance(){
        MenuFragment fragment = new MenuFragment();
        return fragment;
    }
    private int hard_level = 0;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Spinner spinner = view.findViewById(R.id.levelSpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                hard_level = 0;
                switch (selectedItemPosition){
                    case 0: hard_level +=4;break;
                    case 1: hard_level +=5;break;
                    case 2: hard_level +=6;break;

                }
                Toast toast = Toast.makeText(view.getContext().getApplicationContext(),
                        "You must guess "+ hard_level+" numbers", Toast.LENGTH_SHORT);
                toast.show();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        view.findViewById(R.id.devInfoButton).setOnClickListener(v->{
            DevFragment devFragment = DevFragment.newInstance();
            devFragment.setTargetFragment(this, 0);
            getFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragmentContainer,devFragment)
                    .commit();
        });

        view.findViewById(R.id.startButton).setOnClickListener(v->{
            GameFieldFragment gameFragment = GameFieldFragment.newInstance(hard_level);
            gameFragment.setTargetFragment(this, 0);
            getFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragmentContainer,gameFragment)
                    .commit();
        });

        view.findViewById(R.id.exitButton).setOnClickListener(v->{
            getActivity().finishAffinity();
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }
}
