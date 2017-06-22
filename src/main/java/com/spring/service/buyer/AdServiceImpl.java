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
import com.spring.service.admin.OperationRecordService;
import com.spring.vo.buyer.BuyerAppBannerVO;

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
	public Map<String, Object> createBuyerAppBanner(BuyerAppBannerVO buyerAppBannerVO, Locale locale) throws Exception {

		buyerAppBannerDAO.createBuyerAppBanner(buyerAppBannerVO);
		
		operationRecordService.createOperationRecord("创建买家APP广告  标题：" + buyerAppBannerVO.getAdName());

		return output("0", null, messageSource.getMessage("create_seccess", null, locale));
	}
	@Transactional("transaction")
	@Override
	public Map<String, Object> modifyBuyerAppBanner(BuyerAppBannerVO buyerAppBannerVO, Locale locale) throws Exception {
		
		final int hasBuyerAppBanner = this.hasBuyerAppBanner(buyerAppBannerVO.getAdId());
        if(hasBuyerAppBanner == 0) {
        	return output("1", null, messageSource.getMessage("buyer_ad_cant_found", null, locale));
        }
		
		final int result = buyerAppBannerDAO.modifyBuyerAppBanner(buyerAppBannerVO);
		if (result == 0) {
			
			return output("1", null, messageSource.getMessage("modify_fail", null, locale));
		} else {
			
			operationRecordService.createOperationRecord("修改买家APP广告  标题：" + buyerAppBannerVO.getAdName());
			
			return output("0", null, messageSource.getMessage("modify_seccess", null, locale));
		}
	}
	@Transactional("transaction")
	@Override
	public Map<String, Object> deleteBuyerAppBanner(int adId, Locale locale) throws Exception {
		
		buyerAppBannerDAO.deleteBuyerAppBanner(adId);
		
		operationRecordService.createOperationRecord("删除买家APP广告  编号：" + adId);

		return output("0", null, messageSource.getMessage("delete_seccess", null, locale));
	}
	
	@Override
	public int hasBuyerAppBanner(int adId) {
		
		return buyerAppBannerDAO.hasBuyerAppBanner(adId);
	}
	@Override
	public BuyerAppBannerVO getBuyerAppBanner(int adId) {
		
		return buyerAppBannerDAO.getBuyerAppBanner(adId);
	}
	@Override
	public List<BuyerAppBannerVO> getBuyerAppBanners(BuyerAppBannerVO buyerAppBannerVO) {
		
		return buyerAppBannerDAO.getBuyerAppBanners(buyerAppBannerVO);
	}
}