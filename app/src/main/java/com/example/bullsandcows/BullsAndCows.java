package com.example.bullsandcows;

import java.util.ArrayList;

public class BullsAndCows {

    //Функція переторення символьного рядка в масив
    public static int[] getArrFromStr(String str, int length){
        int [] arr = new int [length];
        for(int i = 0;i<length;i++)
            arr[i] = Integer.parseInt(String.valueOf(str.charAt(i)));
        return arr;
    }

    //Функція реверсу масиву
    public static ArrayList<com.example.bullsandcows.Attemp> reverseArrayList(ArrayList<com.example.bullsandcows.Attemp> alist)
    {
        ArrayList<com.example.bullsandcows.Attemp> revArrayList = new ArrayList<>();
        for (int i = alist.size() - 1; i >= 0; i--) {
            revArrayList.add(alist.get(i));
        }
        return revArrayList;
    }


}
