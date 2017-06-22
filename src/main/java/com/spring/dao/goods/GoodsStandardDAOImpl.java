package com.spring.dao.goods;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.spring.dto.goods.GoodsStandard;
import com.spring.vo.goods.GoodsStandardVO;

@Repository
public class GoodsStandardDAOImpl implements GoodsStandardDAO {

	@Autowired
	private SqlSession sqlSession;
	
	static final String GOODS_STANDARD_MAPPER = "com.spring.mappers.GoodsStandardMapper.";

	@Override
	public void createGoodsStandard(GoodsStandardVO goodsStandardVO) throws Exception {

		sqlSession.insert(GOODS_STANDARD_MAPPER + "createGoodsStandard", goodsStandardVO);
	}
	@Override
	public int hasGoodsStandardById(int goodsStandardId) {
		
		return sqlSession.selectOne(GOODS_STANDARD_MAPPER + "hasGoodsStandardById", goodsStandardId);
	}
	@Override
	public GoodsStandardVO getGoodsStandardById(int goodsStandardId) {
		
		return sqlSession.selectOne(GOODS_STANDARD_MAPPER + "getGoodsStandardById", goodsStandardId);
	}
	@Override
	public GoodsStandard getGoodsBriefById(int goodsStandardId) {
		
		return sqlSession.selectOne(GOODS_STANDARD_MAPPER + "getGoodsBriefById", goodsStandardId);
	}
	@Override
	public List<GoodsStandardVO> getGoodsStandards(GoodsStandardVO goodsStandardVO) {
		
		return sqlSession.selectList(GOODS_STANDARD_MAPPER + "getGoodsStandards", goodsStandardVO);
	}
	@Override
	public int modifyGoodsStandard(GoodsStandardVO goodsStandardVO) throws Exception {
		
		return sqlSession.update(GOODS_STANDARD_MAPPER + "modifyGoodsStandard", goodsStandardVO);
	}
	@Override
	public int deleteGoodsStandard(GoodsStandardVO goodsStandardVO) throws Exception {
		
		return sqlSession.update(GOODS_STANDARD_MAPPER + "deleteGoodsStandard", goodsStandardVO);
	}
}