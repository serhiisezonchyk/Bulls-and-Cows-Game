package com.example.bullsandcows;

import java.util.Arrays;
import java.util.Collections;

public class NumberRandomizer {

    private static short length;

    public NumberRandomizer(short length) {
        this.length = length;
    }

    public static Integer[] getRandNum(Integer num){
/*        Integer [] arr = new Integer[length];
        Random rand = new Random();
        for(int i = 10 - length; i < 9; i++){
            Integer randNum = rand.nextInt(i);
            if(!Arrays.asList(arr).contains(randNum)){
                Arrays.asList(arr).add(randNum);
            }else{
                Arrays.asList(arr).add(i);
            }
        }
        Collections.shuffle(Arrays.asList(arr));
        return arr;*/
        Integer [] arr = new Integer [10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        Collections.shuffle(Arrays.asList(arr));
        return Arrays.copyOfRange(arr,0,num);
    }
}
