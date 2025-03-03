package com.epam.rd.autotasks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class QuadraticEquationNoRootsCasesTesting {

    protected QuadraticEquation quadraticEquation = new QuadraticEquation();

    private double a;
    private double b;
    private double c;

    public QuadraticEquationNoRootsCasesTesting(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
                {1, 2, 3},
                {1, 0, 1},
                {2, 2, 5},
                {1, -1, 5}
        };
    }

    @Test
    public void testNoRootsCase() {
        assertEquals("no roots", quadraticEquation.solve(a, b, c));
    }

}
