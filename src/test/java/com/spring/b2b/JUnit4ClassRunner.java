package com.spring.b2b;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.runners.model.InitializationError;

public class JUnit4ClassRunner extends SpringJUnit4ClassRunner {
	static {  
		DOMConfigurator.configure("src/test/resources/log4j.xml");
	}
	  
	public JUnit4ClassRunner(Class<?> _class) throws InitializationError {  
		super(_class);  
	}  
}
