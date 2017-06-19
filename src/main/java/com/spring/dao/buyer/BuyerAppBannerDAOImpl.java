package com.spring.dao.buyer;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.spring.dto.buyer.BuyerAppBanner;

@Repository
public class BuyerAppBannerDAOImpl implements BuyerAppBannerDAO {

	@Autowired
	private SqlSession sqlSession;
	
	static final String BUYER_APP_BANNER_MAPPER = "com.spring.mappers.BuyerAppBannerMapper.";
	
	@Override
	public void createBuyerAppBanner(BuyerAppBanner buyerAppBanner) throws Exception {

		sqlSession.insert(BUYER_APP_BANNER_MAPPER + "createBuyerAppBanner", buyerAppBanner);
	}
	@Override
	public int modifyBuyerAppBanner(BuyerAppBanner buyerAppBanner) throws Exception {
		
		return sqlSession.update(BUYER_APP_BANNER_MAPPER + "modifyBuyerAppBanner", buyerAppBanner);
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
	public BuyerAppBanner getBuyerAppBanner(int adId) {
		
		return sqlSession.selectOne(BUYER_APP_BANNER_MAPPER + "getBuyerAppBanner", adId);
	}
	@Override
	public List<BuyerAppBanner> getBuyerAppBanners(BuyerAppBanner buyerAppBanner) {
		
		return sqlSession.selectList(BUYER_APP_BANNER_MAPPER + "getBuyerAppBanners", buyerAppBanner);
	}
}