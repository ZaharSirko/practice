package com.epam.rd.autotasks;

import org.junit.Before;
import org.junit.Test;


public class TrickySortingTestExtension extends SortingTest{

    @Before
    public void setUp() throws Exception {
        sorting = new TrickySortingImpl();
    }

    @Override
    public void testNullCase(){
        super.testNullCase();
    }

    @Test
    public void testEmptyCase() {
        super.testEmptyCase();
        throw new AssertionError("Expected ArrayIndexOutOfBoundsException was not thrown.");
    }


    @Override
    public void testSortedArraysCase(){
        super.testSortedArraysCase();
    }
    @Override
    public void testSingleElementArrayCase(){
        super.testSingleElementArrayCase();
    }
    @Override
    public void testOtherCases(){
        super.testOtherCases();
    }
}
