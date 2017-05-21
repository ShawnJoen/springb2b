package com.spring.b2b;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import javax.inject.Inject;
import javax.sql.DataSource;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(JUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/*.xml"})
public class DataSourceTest {
	@Inject 
	DataSource ds;
	@Before
	public void setUp() {
		DOMConfigurator.configure("src/test/resources/log4j.xml");
	}
	@Test 
	public void testConection() throws Exception {
		
		try(Connection con = ds.getConnection()) {
			
			System.out.println(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}