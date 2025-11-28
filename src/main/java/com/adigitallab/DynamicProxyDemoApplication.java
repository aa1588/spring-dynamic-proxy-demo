package com.adigitallab;

import com.adigitallab.proxy.CglibProxy;
import com.adigitallab.proxy.JdkProxy;
import com.adigitallab.service.Calculator;
import com.adigitallab.service.MathService;
import com.adigitallab.service.SimpleCalculator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.proxy.Enhancer;

import static java.lang.reflect.Proxy.newProxyInstance;

@SpringBootApplication
public class DynamicProxyDemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DynamicProxyDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//============
		// JDK Proxy: Needs Interface (Here we have Calculator as an interface, doesn't work with class.)
		//          : Used to create proxy of a class that implements certain interface
		//===========

		System.out.println("\n=== JDK DYNAMIC PROXY ===\n");

		// Create a proxy object of class that implements Calculator interface
		Calculator calc = (Calculator) newProxyInstance(
				Calculator.class.getClassLoader(),
				new Class<?>[]{Calculator.class},
				new JdkProxy(new SimpleCalculator())
		);

		System.out.println(calc.add(10, 5));
		System.out.println(calc.multiply(4, 7));
		System.out.println(calc.subtract(20, 3));


		//============
		// CGLIB Proxy: No Interface Needed (works with class.)
		//          : Used to create proxy of a class
		//===========
		System.out.println("\n=== CGLIB PROXY ===\n");

		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(MathService.class);
		enhancer.setCallback(new CglibProxy());
		MathService math = (MathService) enhancer.create();

		math.divide(100, 5);
		math.power(2, 8);
		math.abs(-99);

	}
}
