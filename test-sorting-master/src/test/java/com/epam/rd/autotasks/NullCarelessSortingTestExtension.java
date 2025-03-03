package com.epam.rd.autotasks;

import org.junit.Before;

public class NullCarelessSortingTestExtension extends SortingTest{

    @Before
    public void setUp() throws Exception {
        sorting = new NullCarelessSortingImpl();
    }

    @Override
    public void testNullCase() {
        try {
            super.testNullCase();
        } catch (RuntimeException e) {
            throw e;
        }
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
