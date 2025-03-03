package com.epam.rd.autotasks;

import java.util.Arrays;

public class NullCarelessSortingImpl extends Sorting{
    @Override
    public void sort(int[] array) {
        if (array == null) {
            throw new RuntimeException("Unexpected exception: Array is null");
        }
        if( array.length == 0){
            throw new ArrayIndexOutOfBoundsException("Array cannot be empty");
        }
        Arrays.sort(array);
    }

}
