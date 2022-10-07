package com.example.bullsandcows.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bullsandcows.R;

public class DevFragment extends Fragment {

    public static DevFragment newInstance(){
        DevFragment fragment = new DevFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.home_button).setOnClickListener(v-> {
            MenuFragment menuFragment = MenuFragment.newInstance();
            menuFragment.setTargetFragment(null, 0);
            getFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragmentContainer, menuFragment)
                    .commit();
        });
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dev, container, false);
    }
}
