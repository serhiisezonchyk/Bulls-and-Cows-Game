package com.example.bullsandcows;

import java.util.ArrayList;

public class BullsAndCows {

    public static Integer[] getArrFromStr(String str, Integer length){
        Integer [] arr = new Integer [length];
        for(int i = 0;i<length;i++)
            arr[i] = Integer.parseInt(String.valueOf(str.charAt(i)));
        return arr;
    }

    public static ArrayList<Attemp> reverseArrayList(ArrayList<Attemp> alist)
    {
        // Arraylist for storing reversed elements
        ArrayList<Attemp> revArrayList = new ArrayList<Attemp>();
        for (int i = alist.size() - 1; i >= 0; i--) {

            // Append the elements in reverse order
            revArrayList.add(alist.get(i));
        }

        // Return the reversed arraylist
        return revArrayList;
    }

    public static Integer getBulls(Integer[] userString, Integer[] generatedString, Integer lenght){
        Integer  result = 0;
        for (int i = 0 ;i < lenght;i++)
            if(userString[i] == generatedString[i])
                result++;
        return result;
    }
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
