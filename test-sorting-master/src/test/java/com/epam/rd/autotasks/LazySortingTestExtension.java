package com.epam.rd.autotasks;

import org.junit.Before;

public class LazySortingTestExtension extends SortingTest{


    @Before
    public void setUp() {
        sorting = new LazySortingImpl();
    }

    @Override
    public void testNullCase(){
        super.testNullCase();
    }

    @Override
    public void testEmptyCase(){
        super.testEmptyCase();
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
