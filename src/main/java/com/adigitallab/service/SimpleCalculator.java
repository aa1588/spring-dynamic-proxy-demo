package com.adigitallab.service;

public class SimpleCalculator implements Calculator {
    
    @Override
    public int add(int a, int b) {
        sleep(500);
        System.out.println("   [SimpleCalculator] Adding " + a + " + " + b);
        return a + b;
    }
    
    @Override
    public int multiply(int a, int b) {
        sleep(700);
        System.out.println("   [SimpleCalculator] Multiplying " + a + " × " + b);
        return a * b;
    }
    
    @Override
    public int subtract(int a, int b) {
        System.out.println("   [SimpleCalculator] Subtracting " + a + " - " + b);
        return a - b;
    }
    
    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}