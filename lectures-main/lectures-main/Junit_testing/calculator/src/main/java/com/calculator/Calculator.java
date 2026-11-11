package com.calculator;

/**
 * Hello world!
 *
 */
public class Calculator
{

    private int value;

    public Calculator() {
        this.value = 0;
    }

    public void add(int number) {
        this.value = this.value + number;
    }

    //add a method with incorrect logic
    public void subtract(int number) {
        this.value = this.value + number;
    }

    public int getValue() {
        return this.value;
    }

}
