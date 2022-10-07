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
        //перевірка на перший запуск
        if(savedInstanceState == null){
            //Створення фрагменту, що буде запускатися при першому запуску
            MenuFragment menuFragment = new MenuFragment();
            getSupportFragmentManager().beginTransaction()//Починаємо транзакцію
                    .add(R.id.fragmentContainer, menuFragment)//Додаємо потрібний фрагмент
                    .commit();//Підтверджуємо
        }
    }
}
