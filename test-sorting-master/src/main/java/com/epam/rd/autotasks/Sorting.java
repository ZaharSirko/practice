package com.epam.rd.autotasks;

import java.util.Arrays;

public class Sorting {
    public void sort(int[] array){
        if(array == null){
            throw new NullPointerException("Array cannot be null");
        }
        if( array.length == 0){
            throw new ArrayIndexOutOfBoundsException("Array is null or empty");
        }
        Arrays.sort(array);
    }
}
