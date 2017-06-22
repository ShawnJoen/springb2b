package com.spring.dao.goods;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GoodsDAOImpl implements GoodsDAO {

	@Autowired
	private SqlSession sqlSession;
	
	static final String GOODS_MAPPER = "com.spring.mappers.GoodsMapper.";
	
	
}