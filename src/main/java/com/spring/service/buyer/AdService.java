package com.spring.service.buyer;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import com.spring.vo.buyer.BuyerAppBannerVO;

public interface AdService {
	Map<String, Object> createBuyerAppBanner(BuyerAppBannerVO buyerAppBannerVO, Locale locale) throws Exception;
	Map<String, Object> modifyBuyerAppBanner(BuyerAppBannerVO buyerAppBannerVO, Locale locale) throws Exception;
	Map<String, Object> deleteBuyerAppBanner(int adId, Locale locale) throws Exception;
	int hasBuyerAppBanner(int adId);
	BuyerAppBannerVO getBuyerAppBanner(int adId);
	List<BuyerAppBannerVO> getBuyerAppBanners(BuyerAppBannerVO buyerAppBannerVO);
}