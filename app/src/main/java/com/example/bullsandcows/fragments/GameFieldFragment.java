package com.example.bullsandcows.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.InputFilter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bullsandcows.Attemp;
import com.example.bullsandcows.BullsAndCows;
import com.example.bullsandcows.NumberRandomizer;
import com.example.bullsandcows.R;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameFieldFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameFieldFragment extends Fragment {
    private  static final String ARG_VALUE = "VALUE";
    private ArrayList<Attemp> attempList = new ArrayList<>();
    private Integer attemp = 1;
    private Integer [] magic_nums;
    private Integer numCount;

    private EditText et;
    private ListView listView;
    public static GameFieldFragment newInstance(Integer numCount){
        Bundle args = new Bundle();
        args.putInt(ARG_VALUE, numCount);
        GameFieldFragment fragment = new GameFieldFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        numCount = getArguments().getInt(ARG_VALUE);
        TextView t = view.findViewById(R.id.textViewRes);
        t.setText("Guess " + numCount +" numbers:");

        et = view.findViewById(R.id.et);
        et.setFilters(new InputFilter[] {new InputFilter.LengthFilter(numCount)});

        magic_nums = NumberRandomizer.getRandNum(numCount);

        view.findViewById(R.id.home_button).setOnClickListener(v-> {
            MenuFragment menuFragment = MenuFragment.newInstance();
            menuFragment.setTargetFragment(null, 0);
            getFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragmentContainer, menuFragment)
                    .commit();
        });

        view.findViewById(R.id.confirmBut).setOnClickListener(v-> {
            Integer [] userString = BullsAndCows.getArrFromStr(String.valueOf(et.getText()),numCount);
            Integer bulls = BullsAndCows.getBulls(userString,magic_nums, numCount);
            Integer cows = BullsAndCows.getCows(userString,magic_nums, numCount);
            if(bulls == numCount){
                attempList.add(new Attemp(attemp,Integer.parseInt(String.valueOf(et.getText())),bulls,cows));
                popup_result(view, "You are guess the number! Great!");
            }else {
                attempList.add(new Attemp(attemp,Integer.parseInt(String.valueOf(et.getText())),bulls,cows));
                popup_result(view, attempList.get(attempList.size()-1).toString());
            }
            attemp++;
        });

        view.findViewById(R.id.restart_button).setOnClickListener(v-> {
            magic_nums = NumberRandomizer.getRandNum(numCount);
            attempList = new ArrayList<>();
            attemp = 1;
            et.setText("");
            ArrayAdapter<Attemp> arrayAdapter
                    = new ArrayAdapter<>((Context) getActivity(), android.R.layout.simple_list_item_1 , attempList);
            listView.setAdapter(arrayAdapter);
            Toast toast = Toast.makeText(view.getContext().getApplicationContext(),
                    "Number was updated", Toast.LENGTH_SHORT);
            toast.show();
        });
    };

    private void popup_result(@NonNull View view, String message){

        listView = view.findViewById(R.id.listOfAttemps);

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, view.findViewById(R.id.toast_layout));

        TextView text = layout.findViewById(R.id.text);
        text.setText(message);

        Toast toast = new Toast(view.getContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
        ArrayAdapter<Attemp> arrayAdapter
                = new ArrayAdapter<>((Context) getActivity(), android.R.layout.simple_list_item_1 , BullsAndCows.reverseArrayList(attempList));

        listView.setAdapter(arrayAdapter);

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game_field, container, false);
    }

}