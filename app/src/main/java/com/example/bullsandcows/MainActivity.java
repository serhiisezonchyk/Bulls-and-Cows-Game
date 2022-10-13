package com.example.bullsandcows;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private int hard_level = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = findViewById(R.id.levelSpinner);
        //Програмування обробника події натискання на спінер
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                hard_level = 0;
                switch (selectedItemPosition){
                    case 0: hard_level +=4;break;
                    case 1: hard_level +=5;break;
                    case 2: hard_level +=6;break;

                }
                //Створення спливаючої підсказки
                Toast toast = Toast.makeText(getApplicationContext(),
                        "You must guess "+ hard_level+" numbers", Toast.LENGTH_SHORT);
                toast.show();
            }
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        findViewById(R.id.devInfoButton).setOnClickListener(v->{
            Intent intent = new Intent(this,DevActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.startButton).setOnClickListener(v->{
            //Завантаження фрагменту з ігровим полем
            Intent intent = new Intent(this,GameActivity.class);
            intent.putExtra(GameActivity.ARG_VALUE, hard_level);
            startActivity(intent);
        });

        //Вихід з додатку
        findViewById(R.id.exitButton).setOnClickListener(v->{
            finishAffinity();
        });
    }
}
