package com.epam.rd.autotasks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class QuadraticEquationZeroACasesTesting {
    protected QuadraticEquation quadraticEquation = new QuadraticEquation();
    private double a;
    private double b;
    private double c;

    public QuadraticEquationZeroACasesTesting(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
                {0, 2, 1},
                {0, -3, 2},
                {0, 1, -1},
                {0, 0, 0}
        };
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroAThrowsException() {
        quadraticEquation.solve(a, b, c);
    }
}
