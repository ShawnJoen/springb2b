package com.spring.dao.buyer;

import java.util.List;
import com.spring.vo.buyer.BuyerAppBannerVO;

public interface BuyerAppBannerDAO {
	void createBuyerAppBanner(BuyerAppBannerVO buyerAppBannerVO) throws Exception;
	int modifyBuyerAppBanner(BuyerAppBannerVO buyerAppBannerVO) throws Exception;
	int deleteBuyerAppBanner(int adId) throws Exception;
	int hasBuyerAppBanner(int adId);
	BuyerAppBannerVO getBuyerAppBanner(int adId);
	List<BuyerAppBannerVO> getBuyerAppBanners(BuyerAppBannerVO buyerAppBannerVO);
}