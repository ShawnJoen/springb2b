package com.spring.b2b;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.spring.dao.admin.AdminUserDAO;

import javax.inject.Inject;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Before;
import org.junit.Test;

@RunWith(JUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/*.xml"})
public class AdminUserDAOTest {
	@Inject
	private AdminUserDAO dao;
	@Before
	public void setUp() {

		DOMConfigurator.configure("src/test/resources/log4j.xml");
	}
	@Test
	public void test() {
		
		System.out.println(dao);
	}
	@Test
	public void test2() throws Exception {
		
		System.out.println(dao.getTime());
	}
}
