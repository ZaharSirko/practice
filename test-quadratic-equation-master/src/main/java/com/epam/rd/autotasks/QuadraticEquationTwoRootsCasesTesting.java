package com.epam.rd.autotasks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class QuadraticEquationTwoRootsCasesTesting {
    protected QuadraticEquation quadraticEquation = new QuadraticEquation();

    private double a;
    private double b;
    private double c;
    private String expected;

    public QuadraticEquationTwoRootsCasesTesting(double a, double b, double c, String expected) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
                {1, -3, 2, "2.0 1.0"},
                {1, 5, 6, "-2.0 -3.0"},
                {2, -7, 3, "3.0 0.5"},
                {1, -5, 4, "4.0 1.0"}
        };
    }

    @Test
    public void testTwoRoots() {
        String result = quadraticEquation.solve(a, b, c);
        assertTrue(result.contains(expected.split(" ")[0]) && result.contains(expected.split(" ")[1]));
    }

}
