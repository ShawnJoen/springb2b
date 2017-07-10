package com.spring.b2b.api.buyer;

import static com.spring.util.Common.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.spring.b2b.api.buyer.EnvController;
import com.spring.dto.config.ErrorLog;
import com.spring.vo.config.SiteConfigVO;
import com.spring.vo.finance.PaymentVO;

@Controller
@RequestMapping("/buyer")
public class AppController extends EnvController {

	private static final Logger logger = LoggerFactory.getLogger(AppController.class);

	@RequestMapping(value = "/initialize.do", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> initialize(@RequestParam(value = "deviceType", required = false, defaultValue="android") String deviceType,
			@RequestParam(value = "appVersion", required = false, defaultValue="") String appVersion,
			@RequestParam(value = "deviceId", required = false, defaultValue="") String deviceId,
			@RequestParam(value = "brandName", required = false, defaultValue="") String brandName,
			@RequestParam(value = "osVersion", required = false, defaultValue="") String osVersion,
			Locale locale) throws Exception {

		final Map<String, Object> resultDataMap = new HashMap<>();
		//前端oss文件上传
		final Map<String, String> fileUploadConfigs = new HashMap<>();
		fileUploadConfigs.put("host", "-host-");
		fileUploadConfigs.put("accessId", "-accessId-");
		fileUploadConfigs.put("accessKey", "-accessKey-");
		fileUploadConfigs.put("bucket", "-bucket-");
		//支付信息
		final List<PaymentVO> paymentVOs = paymentService.getPayments(
				new PaymentVO(1)
			);
		final List<Map<String, String>> paymentList = new ArrayList<>();
		for (PaymentVO paymentVO : paymentVOs) {
			
			/*Set<String> filterSet = new HashSet<>();
			filterSet.add("paymentConfig");
			filterSet.add("paymentStatus");
			filterSet.add("paymentType");
			filterSet.add("sort");
			paymentList.add(serializeAndFilterValueObject("paymentVO", paymentVO, filterSet));*/
			Map<String, String> _paymentVO = new HashMap<>();
			_paymentVO.put("paymentCode", paymentVO.getPaymentCode());
			_paymentVO.put("paymentName", paymentVO.getPaymentName());
			_paymentVO.put("paymentIcon", paymentVO.getPaymentIcon());
			_paymentVO.put("isDefault", paymentVO.getIsDefault());
			paymentList.add(_paymentVO);
		}
		resultDataMap.put("payments", paymentList);
		resultDataMap.put("fileUploadType", "oss");
		resultDataMap.put("fileUploadConfig", fileUploadConfigs);
		resultDataMap.put("serviceTel", "18621325460");
		
		if (Arrays.asList("ios", "android").contains(deviceType)) {

			List<SiteConfigVO> siteConfigVOs = siteConfigService.getSiteConfigs(
					new SiteConfigVO(1, "buyer", deviceType)
					);
			
			if ("android".equals(deviceType)) {
				
				for (SiteConfigVO siteConfigVO : siteConfigVOs) {
					
					switch (siteConfigVO.getConfigCode()) {
						case "buyer_android_version":
							resultDataMap.put("appVersion", siteConfigVO.getConfigVal());
							break;
						case "buyer_android_version_code":
							resultDataMap.put("appVersionCode", siteConfigVO.getConfigVal());
							break;
						case "buyer_android_upgrade_info":
							resultDataMap.put("upgradeInfo", siteConfigVO.getConfigVal());
							break;
						case "buyer_android_upgrade_force":
							resultDataMap.put("forceUpgrade", siteConfigVO.getConfigVal());
							break;
					}
				}
			} else
			{
				for (SiteConfigVO siteConfigVO : siteConfigVOs) {
					
					switch (siteConfigVO.getConfigCode()) {
						case "buyer_ios_app_version":
							resultDataMap.put("appVersion", siteConfigVO.getConfigVal());
							break;
						case "buyer_ios_upgrade_info":
							resultDataMap.put("upgradeInfo", siteConfigVO.getConfigVal());
							break;
						case "buyer_ios_upgrade_force":
							resultDataMap.put("forceUpgrade", siteConfigVO.getConfigVal());
							break;
					}
				}
			}
		}
		
		return output("0", resultDataMap, null);
	}
		
	@RequestMapping(value = "/error.do", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> error(@ModelAttribute("errorLog") ErrorLog errorLog,
			BindingResult result,
			Locale locale) throws Exception {

		if (result.hasErrors()) {

            return output("1", null, messageSource.getMessage("program_error", null, locale));
        }
		
		return errorLogService.createErrorLog(errorLog, locale);
	}
	
}