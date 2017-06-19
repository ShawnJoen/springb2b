package com.spring.dao.buyer;

import java.util.List;
import com.spring.dto.buyer.BuyerAppBanner;

public interface BuyerAppBannerDAO {
	void createBuyerAppBanner(BuyerAppBanner buyerAppBanner) throws Exception;
	int modifyBuyerAppBanner(BuyerAppBanner buyerAppBanner) throws Exception;
	int deleteBuyerAppBanner(int adId) throws Exception;
	int hasBuyerAppBanner(int adId);
	BuyerAppBanner getBuyerAppBanner(int adId);
	List<BuyerAppBanner> getBuyerAppBanners(BuyerAppBanner buyerAppBanner);
}