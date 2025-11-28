package com.adigitallab.proxy;

import com.adigitallab.annotation.Timed;
import com.adigitallab.service.Calculator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JdkProxy implements InvocationHandler {

    private final Object target;
    
    public JdkProxy(Calculator target) {
        this.target = target;
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        
        if (method.isAnnotationPresent(Timed.class)) {
            System.out.println("\n[JDK PROXY] Intercepted: " + method.getName());
            long start = System.currentTimeMillis();
            
            Object result = method.invoke(target, args);
            
            long duration = System.currentTimeMillis() - start;
            System.out.println("[JDK PROXY] Completed in " + duration + "ms\n");
            
            return result;
        }
        
        System.out.println("\n[JDK PROXY] No timing for: " + method.getName());
        return method.invoke(target, args);
    }
}