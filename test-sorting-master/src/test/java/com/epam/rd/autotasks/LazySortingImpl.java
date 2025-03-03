package com.epam.rd.autotasks;


import java.util.Arrays;


public class LazySortingImpl extends Sorting{

    @Override
    public void sort(int[] array) {
        if (array == null) {
            return;
        }
        if( array.length == 0){
            throw new ArrayIndexOutOfBoundsException("Array cannot be empty");
        }
        int mid = array.length / 2;
        int[] part = new int[mid];
        System.arraycopy(array, 0, part, 0, mid);
        Arrays.sort(part);
        System.arraycopy(part, 0, array, 0, mid);
    }

}
