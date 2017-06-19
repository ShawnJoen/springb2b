package com.spring.service.buyer;

import static com.spring.util.Common.output;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.spring.dao.buyer.BuyerAppBannerDAO;
import com.spring.dto.buyer.BuyerAppBanner;
import com.spring.service.admin.OperationRecordService;

@Service("adServiceImpl")
public class AdServiceImpl implements AdService {

	@Autowired
    private BuyerAppBannerDAO buyerAppBannerDAO;
	@Autowired
    private OperationRecordService operationRecordService;
	@Autowired
    private MessageSource messageSource;
	
	@Transactional("transaction")
	@Override
	public Map<String, Object> createBuyerAppBanner(BuyerAppBanner buyerAppBanner, Locale locale) throws Exception {

		buyerAppBannerDAO.createBuyerAppBanner(buyerAppBanner);
		
		operationRecordService.createOperationRecord("�������APP���  ���⣺" + buyerAppBanner.getAdName());

		return output("0", null, messageSource.getMessage("create_seccess", null, locale));
	}
	@Override
	public Map<String, Object> modifyBuyerAppBanner(BuyerAppBanner buyerAppBanner, Locale locale) throws Exception {
		
		final int hasBuyerAppBanner = this.hasBuyerAppBanner(buyerAppBanner.getAdId());
        if(hasBuyerAppBanner == 0) {
        	return output("1", null, messageSource.getMessage("buyer_ad_cant_found", null, locale));
        }
		
		final int result = buyerAppBannerDAO.modifyBuyerAppBanner(buyerAppBanner);
		if (result == 0) {
			
			return output("1", null, messageSource.getMessage("modify_fail", null, locale));
		} else {
			
			operationRecordService.createOperationRecord("�޸����APP���  ���⣺" + buyerAppBanner.getAdName());
			
			return output("0", null, messageSource.getMessage("modify_seccess", null, locale));
		}
	}
	@Override
	public Map<String, Object> deleteBuyerAppBanner(int adId, Locale locale) throws Exception {
		
		buyerAppBannerDAO.deleteBuyerAppBanner(adId);
		
		operationRecordService.createOperationRecord("ɾ�����APP���  ��ţ�" + adId);

		return output("0", null, messageSource.getMessage("delete_seccess", null, locale));
	}
	
	@Override
	public int hasBuyerAppBanner(int adId) {
		
		return buyerAppBannerDAO.hasBuyerAppBanner(adId);
	}
	@Override
	public BuyerAppBanner getBuyerAppBanner(int adId) {
		
		return buyerAppBannerDAO.getBuyerAppBanner(adId);
	}
	@Override
	public List<BuyerAppBanner> getBuyerAppBanners(BuyerAppBanner buyerAppBanner) {
		
		return buyerAppBannerDAO.getBuyerAppBanners(buyerAppBanner);
	}
}