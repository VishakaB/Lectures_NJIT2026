package com.calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * Unit test for simple App.
 */
public class CaculatorTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    @DisplayName("Initialvalue")
    public void calculatorInitialValueZero() {
        Calculator calculator = new Calculator();
        assertTrue(calculator.getValue()==0);
    }

    @Test
    public void valueFiveWhenFiveAdded() {
        Calculator calculator = new Calculator();
        calculator.add(5);
        assertTrue(calculator.getValue()==5);
    }

     @Test
    public void valueMinusTwoWhenTwoSubstracted() {
        Calculator calculator = new Calculator();
        calculator.subtract(2);
        assertTrue(calculator.getValue()==-2);
    }



}
