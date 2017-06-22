package com.spring.dao.buyer;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.spring.vo.buyer.BuyerAppBannerVO;

@Repository
public class BuyerAppBannerDAOImpl implements BuyerAppBannerDAO {

	@Autowired
	private SqlSession sqlSession;
	
	static final String BUYER_APP_BANNER_MAPPER = "com.spring.mappers.BuyerAppBannerMapper.";
	
	@Override
	public void createBuyerAppBanner(BuyerAppBannerVO buyerAppBannerVO) throws Exception {

		sqlSession.insert(BUYER_APP_BANNER_MAPPER + "createBuyerAppBanner", buyerAppBannerVO);
	}
	@Override
	public int modifyBuyerAppBanner(BuyerAppBannerVO buyerAppBannerVO) throws Exception {
		
		return sqlSession.update(BUYER_APP_BANNER_MAPPER + "modifyBuyerAppBanner", buyerAppBannerVO);
	}
	@Override
	public int deleteBuyerAppBanner(int adId) throws Exception {

		return sqlSession.update(BUYER_APP_BANNER_MAPPER + "deleteBuyerAppBanner", adId);
	}
	@Override
	public int hasBuyerAppBanner(int adId) {
		
		return sqlSession.selectOne(BUYER_APP_BANNER_MAPPER + "hasBuyerAppBanner", adId);
	}
	@Override
	public BuyerAppBannerVO getBuyerAppBanner(int adId) {
		
		return sqlSession.selectOne(BUYER_APP_BANNER_MAPPER + "getBuyerAppBanner", adId);
	}
	@Override
	public List<BuyerAppBannerVO> getBuyerAppBanners(BuyerAppBannerVO buyerAppBannerVO) {
		
		return sqlSession.selectList(BUYER_APP_BANNER_MAPPER + "getBuyerAppBanners", buyerAppBannerVO);
	}
}