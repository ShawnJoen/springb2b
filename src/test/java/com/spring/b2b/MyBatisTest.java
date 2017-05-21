package com.spring.b2b;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

@RunWith(JUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/*.xml"})
public class MyBatisTest {
	@Inject 
	private SqlSessionFactory sqlFactory;
	@Test 
	public void testFactory() {
		System.out.println(sqlFactory);
	}
	@Test 
	public void testSession() throws Exception {
		try(SqlSession session = sqlFactory.openSession()) {
			System.out.println(session);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
