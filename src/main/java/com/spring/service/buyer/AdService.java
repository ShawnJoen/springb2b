package com.spring.service.buyer;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import com.spring.dto.buyer.BuyerAppBanner;

public interface AdService {
	Map<String, Object> createBuyerAppBanner(BuyerAppBanner buyerAppBanner, Locale locale) throws Exception;
	Map<String, Object> modifyBuyerAppBanner(BuyerAppBanner buyerAppBanner, Locale locale) throws Exception;
	Map<String, Object> deleteBuyerAppBanner(int adId, Locale locale) throws Exception;
	int hasBuyerAppBanner(int adId);
	BuyerAppBanner getBuyerAppBanner(int adId);
	List<BuyerAppBanner> getBuyerAppBanners(BuyerAppBanner buyerAppBanner);
}