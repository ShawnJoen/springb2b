package com.spring.b2b;

import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.DriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

public class MysqlConnectTest {
	
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/spring_b2b?characterEncoding=UTF-8&serverTimezone=PRC";
	private static final String USER = "root";
	private static final String PW = "";
	
	//static final Logger logger = LogManager.getLogger(Logger.class.getName());
	private static final Logger logger = LoggerFactory.getLogger(MysqlConnectTest.class);
		
	@Before
	public void setUp() throws Exception {

		DOMConfigurator.configure("src/test/resources/log4j.xml");
		//logger.info("MysqlConnectTest setUp");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws Exception {
		Class.forName(DRIVER);
		try(Connection con = DriverManager.getConnection(URL,USER,PW)) {
			
			System.out.println(con);
		} catch(Exception e) {
			e.printStackTrace();
		}
		//isDebugEnabled(), isInfoEnabled()
		logger.trace("TRACE: " );
        logger.debug("DEBUG: " );
        logger.info("INFO: " );//yes
        logger.warn("WARN: " );//yes
        logger.error("ERROR: " );//yes
	}
}
