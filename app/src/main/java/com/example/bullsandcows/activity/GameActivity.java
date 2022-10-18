package com.example.bullsandcows.activity;

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

import com.example.bullsandcows.obs.App;
import com.example.bullsandcows.obs.TaskListener;
import com.example.bullsandcows.services.AsyncService;
import com.example.bullsandcows.Attemp;
import com.example.bullsandcows.BullsAndCows;
import com.example.bullsandcows.R;
import com.example.bullsandcows.threads.LooperThreadTask;

import java.util.ArrayList;


public class GameActivity extends AppCompatActivity implements TaskListener {

    public static final String ARG_VALUE = "ARG_VALUE";
    private ArrayList<Attemp> attempList = new ArrayList<>();
    private Integer attemp = 1;
    private int[] magic_nums;
    private Integer numCount;
    private Integer bulls;
    private Integer cows;

    private EditText et;
    private ListView listView;
    private Button confBut;
    private Button restartBut;
    App app;
    @Override
    protected void onStart() {
        super.onStart();
        app.addListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        app.removeListener(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        app = (App)getApplication();
        numCount = getIntent().getIntExtra(ARG_VALUE,4);

        TextView t = findViewById(R.id.textViewRes);
        t.setText("Guess " + numCount +" numbers:");
        et = findViewById(R.id.et);
        confBut = findViewById(R.id.confirmBut);
        restartBut = findViewById(R.id.restart_button);
        //Обмеження на введення кількості цифр
        et.setFilters(new InputFilter[] {new InputFilter.LengthFilter(numCount)});
        launchService(AsyncService.ACTION_GET_RAND_NUM, numCount);
        findViewById(R.id.home_button).setOnClickListener(v-> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        //Обробник кнопки перевірки значень
        findViewById(R.id.confirmBut).setOnClickListener(v-> {
            if(et.getText().toString().length()==numCount) {
                launchService(AsyncService.ACTION_GET_BULLS, numCount, BullsAndCows.getArrFromStr(String.valueOf(et.getText()), numCount), magic_nums);
                launchService(AsyncService.ACTION_GET_COWS, numCount, BullsAndCows.getArrFromStr(String.valueOf(et.getText()), numCount), magic_nums);
            }
        });

        findViewById(R.id.restart_button).setOnClickListener(v-> {
            if(!attempList.isEmpty()) {
                et.setEnabled(true);
                confBut.setEnabled(true);
                et.setEnabled(true);
                et.setText("");

                launchService(AsyncService.ACTION_GET_RAND_NUM, numCount);

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

    private void launchService(String action,Integer length, int [] userStr, int[] randStr) {
        Intent intent = new Intent(this, AsyncService.class);
        intent.setAction(action);

        intent.putExtra(AsyncService.LENGTH, length)
                .putExtra(AsyncService.USER_NUM,  userStr)
                .putExtra(AsyncService.RAND_NUM,randStr);
        startService(intent);
    }
    private void launchService(String action, Integer length) {
        Intent intent = new Intent(this, AsyncService.class);
        intent.setAction(action);
        intent.putExtra(AsyncService.LENGTH, length);
        startService(intent);
    }

    @Override
    public void getRandNum(int[] randNum) {
        magic_nums = randNum;
    }

    @Override
    public void getBulls(int bulls) {
        this.bulls = bulls;
    }

    @Override
    public void getCows(int cows) {
        this.cows = cows;
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

}