package com.epam.rd.autotasks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class QuadraticEquationSingleRootCasesTesting {
    protected QuadraticEquation quadraticEquation = new QuadraticEquation();

    private double a;
    private double b;
    private double c;
    private double expected;

    public QuadraticEquationSingleRootCasesTesting(double a, double b, double c, double expected) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.expected = expected;
    }
    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
                {1, -2, 1, 1.0},
                {4, -4, 1, 0.5},
                {9, -12, 4, 0.6666666666666666},
                {16, -8, 1, 0.25}
        };
    }

    @Test
    public void testSingleRoot() {
        assertEquals(String.valueOf(expected), quadraticEquation.solve(a, b, c));
    }

}