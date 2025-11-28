# Dynamic Proxy Demo (JDK Proxy vs CGLIB)

This project shows how Java can wrap your objects at runtime and intercept method calls without modifying your original classes. This idea is used heavily in Spring AOP features such as @Transactional and @Cacheable.

We use two kinds of proxies:
- JDK Dynamic Proxy
- CGLIB Proxy

Both intercept methods annotated with `@Timed` in this example.

## JDK Dynamic Proxy (works with interfaces)

JDK Proxy requires an interface. It does not extend your concrete class.  
Instead, it creates a new class at runtime that implements the same interface and forwards calls through an InvocationHandler.

```
Calculator (interface)
       ↑
Calculator$Proxy123     ← generated at runtime
       ↑
calc object
       ↓
JdkProxy.invoke()
       ↓
SimpleCalculator (real class)
```

Important:  
To detect `@Timed`, the annotation must be placed on the **interface methods**.  
JDK Proxy reads annotations from the interface, not from the class.

## CGLIB Proxy (works with classes)

CGLIB does not require an interface. It generates a subclass of your target class and overrides methods so that it can intercept them.

```
MathService (your class)
       ↑
MathService$$EnhancerByCGLIB$$abcd1234   ← generated subclass
       ↑
math object
       ↓
CglibProxy.intercept()
       ↓
super.method() inside MathService
```

## Summary 

- JDK Proxy  
  Creates a class that implements your interface. Requires an interface.  
  Reads annotations from the interface.

- CGLIB Proxy  
  Creates a subclass of your class. No interface required.  
  Reads annotations from the class.

Both allow you to intercept and decorate method calls without modifying your original code.
