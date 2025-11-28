package com.adigitallab.proxy;

import com.adigitallab.annotation.Timed;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {
    
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
            throws Throwable {
        
        if (method.isAnnotationPresent(Timed.class)) {
            System.out.println("\n[CGLIB PROXY] Intercepted: " + method.getName());
            long start = System.currentTimeMillis();
            
            Object result = proxy.invokeSuper(obj, args);
            
            long duration = System.currentTimeMillis() - start;
            System.out.println("[CGLIB PROXY] Completed in " + duration + "ms\n");
            
            return result;
        }
        
        System.out.println("\n[CGLIB PROXY] No timing for: " + method.getName());
        return proxy.invokeSuper(obj, args);
    }
}