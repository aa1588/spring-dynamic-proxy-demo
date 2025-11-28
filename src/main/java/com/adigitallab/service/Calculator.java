package com.adigitallab.service;

import com.adigitallab.annotation.Timed;

public interface Calculator {
    
    @Timed
    int add(int a, int b);
    
    @Timed
    int multiply(int a, int b);
    
    int subtract(int a, int b);
}