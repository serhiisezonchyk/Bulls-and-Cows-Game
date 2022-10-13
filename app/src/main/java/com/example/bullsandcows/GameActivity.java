package com.example.bullsandcows;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    public static final String ARG_VALUE = "ARG_VALUE";
    private ArrayList<Attemp> attempList = new ArrayList<>();
    private Integer attemp = 1;
    private Integer [] magic_nums;
    private Integer numCount;

    private EditText et;
    private ListView listView;
    private Button confBut;
    private Button restartBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        numCount = getIntent().getIntExtra(ARG_VALUE,4);
        TextView t = findViewById(R.id.textViewRes);
        t.setText("Guess " + numCount +" numbers:");
        et = findViewById(R.id.et);
        confBut = findViewById(R.id.confirmBut);
        restartBut = findViewById(R.id.restart_button);
        //Обмеження на введення кількості цифр
        et.setFilters(new InputFilter[] {new InputFilter.LengthFilter(numCount)});
        magic_nums = NumberRandomizer.getRandNum(numCount);
        findViewById(R.id.home_button).setOnClickListener(v-> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        //Обробник кнопки перевірки значень
        findViewById(R.id.confirmBut).setOnClickListener(v-> {
            if(et.getText().toString().length()==numCount) {
                Integer[] userString = BullsAndCows.getArrFromStr(String.valueOf(et.getText()), numCount);
                //Отримання биків
                Integer bulls = BullsAndCows.getBulls(userString, magic_nums, numCount);
                //Отримання корів
                Integer cows = BullsAndCows.getCows(userString, magic_nums, numCount);
                if (bulls == numCount) {
                    attempList.add(new Attemp(attemp, Integer.parseInt(String.valueOf(et.getText())), bulls, cows));
                    //Метод для відображення спиваючої підсказки
                    popup_result("You are guess the number! Great!");
                    et.setEnabled(false);
                    confBut.setEnabled(false);
                    et.setEnabled(false);
                } else {
                    attempList.add(new Attemp(attemp, Integer.parseInt(String.valueOf(et.getText())), bulls, cows));
                    popup_result(attempList.get(attempList.size() - 1).toString());
                }
                //Очищення поля вводу
                et.setText("");
                attemp++;
            }
        });
        findViewById(R.id.restart_button).setOnClickListener(v-> {
            if(!attempList.isEmpty()) {
                et.setEnabled(true);
                confBut.setEnabled(true);
                et.setEnabled(true);
                et.setText("");
                magic_nums = NumberRandomizer.getRandNum(numCount);
                attempList = new ArrayList<>();
                attemp = 1;
                et.setText("");
                ArrayAdapter<Attemp> arrayAdapter
                        = new ArrayAdapter<>((Context) this, android.R.layout.simple_list_item_1, attempList);
                listView.setAdapter(arrayAdapter);
                Toast toast = Toast.makeText(this,
                        "Number was updated", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void popup_result( String message){
        listView = findViewById(R.id.listOfAttemps);
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.toast_layout));
        //Зміна вмісту тексту
        TextView text = layout.findViewById(R.id.text);
        text.setText(message);
        //Створення сплиаючого вікна
        Toast toast = new Toast(this);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
        //Створення адаптера для масиву спроб
        ArrayAdapter<Attemp> arrayAdapter
                = new ArrayAdapter<>((Context) this, android.R.layout.simple_list_item_1 , BullsAndCows.reverseArrayList(attempList));
        //Заповнення listView
        listView.setAdapter(arrayAdapter);
    }
}