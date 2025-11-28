package com.adigitallab.service;

import com.adigitallab.annotation.Timed;

public class MathService {
    
    @Timed
    public double divide(double a, double b) {
        sleep(400);
        System.out.println("   [MathService] Dividing " + a + " ÷ " + b);
        return a / b;
    }
    
    @Timed
    public int power(int base, int exponent) {
        sleep(600);
        System.out.println("   [MathService] Calculating " + base + "^" + exponent);
        return (int) Math.pow(base, exponent);
    }
    
    public int abs(int number) {
        System.out.println("   [MathService] Getting absolute value of " + number);
        return Math.abs(number);
    }
    
    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}