package com.epam.rd.autotasks;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;



public class SortingTest {

    Sorting sorting;

    @Before
    public void setUp() throws Exception {
        sorting = new Sorting();
    }

    @Test(expected = NullPointerException.class)
    public void testNullCase(){
        sorting.sort(null);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testEmptyCase(){
        int[] array = new int[0];
        sorting.sort(array);
    }

    @Test
    public void testSingleElementArrayCase() {
      int[] array = { 1 };
      int[] expected = { 1 };
      sorting.sort(array);
      assertArrayEquals(expected, array);
    }

    @Test
    public void testSortedArraysCase() {
        int[] array = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        sorting.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testOtherCases() {
        int[] array = {3, 2, 3, 1, 2};
        int[] expected = {1, 2, 2, 3, 3};
        sorting.sort(array);
        assertArrayEquals(expected, array);
    }

}