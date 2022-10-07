package com.example.bullsandcows;

import java.util.ArrayList;

public class BullsAndCows {

    //Функція переторення символьного рядка в масив
    public static Integer[] getArrFromStr(String str, Integer length){
        Integer [] arr = new Integer [length];
        for(int i = 0;i<length;i++)
            arr[i] = Integer.parseInt(String.valueOf(str.charAt(i)));
        return arr;
    }

    //Функція реверсу масиву
    public static ArrayList<Attemp> reverseArrayList(ArrayList<Attemp> alist)
    {
        ArrayList<Attemp> revArrayList = new ArrayList<Attemp>();
        for (int i = alist.size() - 1; i >= 0; i--) {
            revArrayList.add(alist.get(i));
        }
        return revArrayList;
    }

    //Отримання точного співпадання позицій
    public static Integer getBulls(Integer[] userString, Integer[] generatedString, Integer lenght){
        Integer  result = 0;
        for (int i = 0 ;i < lenght;i++)
            if(userString[i] == generatedString[i])
                result++;
        return result;
    }

    //Отримання числа входжень
    public static Integer getCows(Integer[] userString, Integer[] generatedString, Integer lenght){
        Integer result = 0;
        for (int i = 0 ;i < lenght;i++)
            for (int j = 0;j<lenght;j++)
                if(i!=j)
                    if(userString[i]==generatedString[j])
                        result++;
        return result;
    }

}
