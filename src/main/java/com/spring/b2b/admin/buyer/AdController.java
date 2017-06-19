package com.spring.b2b.admin.buyer;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.github.pagehelper.PageInfo;
import com.spring.b2b.admin.EnvController;
import com.spring.dto.buyer.BuyerAppBanner;
import com.spring.util.file.FileUtil;
import com.spring.util.file.ImageUtil;
import com.spring.util.validation.ValidationResult;
import com.spring.util.validation.ValidationUtils;
import static com.spring.util.Common.*;

@Controller
@RequestMapping("/admin/buyer")
public class AdController extends EnvController {

	private final Logger logger = LoggerFactory.getLogger(AdController.class);
	
	@Autowired
    private MessageSource messageSource;
	
	/*
	 * 广告 列表
	 * */
	@RequestMapping(value = "/buyerAppBannerList.do", method = RequestMethod.GET)
	public String buyerAppBannerList(@RequestParam(value = "pageNum", required = false, defaultValue="1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue="20") Integer pageSize, 
            @ModelAttribute("buyerAppBanner") BuyerAppBanner buyerAppBanner, 
            ModelMap model) {

		super.setPageHelper(pageNum, pageSize);
		
		List<BuyerAppBanner> buyerAppBanners = adService.getBuyerAppBanners(buyerAppBanner);
		
		PageInfo<BuyerAppBanner> pageInfo = new PageInfo<>(buyerAppBanners);
		model.addAttribute("pageInfo", pageInfo);

		//搜索框绑定搜索字段
		model.addAttribute("buyerAppBanner", buyerAppBanner);
		
		return "admin/buyer/buyerAppBannerList";
	}
	/*
	 * 创建广告 from
	 * */
	@RequestMapping(value = "/createBuyerAppBanner.do", method = RequestMethod.GET)
	public String createBuyerAppBannerForm() {
		
		return "admin/buyer/createBuyerAppBanner";
	}
	/*
	 * 创建广告处理
	 * */
	@RequestMapping(value = "/createBuyerAppBanner.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> createAppAd(@ModelAttribute("buyerAppBanner") BuyerAppBanner buyerAppBanner, 
			BindingResult result,
			MultipartFile adImages[],
			Locale locale) throws Exception {

		if (result.hasErrors()) {

            return output("1", null, messageSource.getMessage("program_error", null, locale));
        }
		
		final ValidationResult ValidResult = ValidationUtils.validation(buyerAppBanner);
		if (ValidResult.isHasErrors()) {
			
			return output("1", null, ValidResult.getErrorMessage());
		}
		//查是否为图
		if (!ImageUtil.checkImageFiles(adImages)) {
			
			return output("1", null, messageSource.getMessage("select_image", null, locale));//请选择图片
		}
		//上传文件
		final StringBuilder imagePaths = FileUtil.uploadFiles("adImage", adImages, super.propertiesService.getProperty("image.baseUrl"));
		
		if (imagePaths.length() == 0) {
			
			return output("1", null, messageSource.getMessage("select_image", null, locale));//请选择图片
		}
		
		buyerAppBanner.setAdImage(imagePaths.toString());

		return adService.createBuyerAppBanner(buyerAppBanner, locale);
	}
	/*
	 * 广告 删除
	 * */
	@RequestMapping(value = "/deleteBuyerAppBanner.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteBuyerAppBanner(@RequestParam(value = "adId", required = false, defaultValue="0") Integer adId,  
			Locale locale) throws Exception {
		
		if (adId == 0) {

            return output("1", null, messageSource.getMessage("program_error", null, locale));
        }
		
		final BuyerAppBanner buyerAppBanner = adService.getBuyerAppBanner(adId);
		if (buyerAppBanner == null) {
			
			return output("1", null, messageSource.getMessage("program_error", null, locale));
		}
		
		if (buyerAppBanner.getIsDel() == 1) {
			
			return output("1", null, messageSource.getMessage("delete_seccess", null, locale));
		}
		//删除图片
		FileUtil.deleteFiles(buyerAppBanner.getAdImage(), super.propertiesService.getProperty("image.baseUrl"));
		
		return adService.deleteBuyerAppBanner(adId, locale);
	}
	/*
	 * 修改广告 from
	 * */
	@RequestMapping(value = "/modifyBuyerAppBanner.do", method = RequestMethod.GET)
	public String modifyBuyerAppBannerForm(@RequestParam(value = "adId", required = false, defaultValue="0") Integer adId, 
			ModelMap model) {
		
		final BuyerAppBanner buyerAppBanner = adService.getBuyerAppBanner(adId);
		model.addAttribute("buyerAppBanner", buyerAppBanner);
		
		return "admin/buyer/modifyBuyerAppBanner";
	}
	/*
	 * 修改广告处理
	 * */
	@RequestMapping(value = "/modifyBuyerAppBanner.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> modifyBuyerAppBanner(@ModelAttribute("buyerAppBanner") BuyerAppBanner buyerAppBanner, 
			BindingResult result,
			MultipartFile adImages[],
			@RequestParam(value = "adImagesUploaded[]", required = false) String[] adImagesUploaded,
			Locale locale) throws Exception {

		if (result.hasErrors()) {

            return output("1", null, messageSource.getMessage("program_error", null, locale));
        }
		
		final ValidationResult ValidResult = ValidationUtils.validation(buyerAppBanner);
		if (ValidResult.isHasErrors()) {
			
			return output("1", null, ValidResult.getErrorMessage());
		}
		//编辑上传文件
		final StringBuilder imagePaths = FileUtil.uploadAndDeleteFiles("adImage", adImages, adImagesUploaded, super.propertiesService.getProperty("image.baseUrl"));
		
		if (imagePaths.length() == 0) {
			
			return output("1", null, messageSource.getMessage("select_image", null, locale));//请选择图片
		}
		//删除之前已上传中取消的图link
		List<String> willDeleteImages = compareDifferentArray(imagePaths.toString().split("\\,"), buyerAppBanner.getAdImage().split("\\,"));
		//删除图片
		FileUtil.deleteFiles(StringUtils.join(willDeleteImages.toArray(), ","), super.propertiesService.getProperty("image.baseUrl"));
		
		buyerAppBanner.setAdImage(imagePaths.toString());

		return adService.modifyBuyerAppBanner(buyerAppBanner, locale);
	}
}