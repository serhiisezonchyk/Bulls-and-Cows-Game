package com.example.bullsandcows;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bullsandcows.fragments.MenuFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            MenuFragment menuFragment = new MenuFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, menuFragment)
                    .commit();
        }

    }

}
