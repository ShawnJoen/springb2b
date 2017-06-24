package com.spring.b2b.admin.config;

import static com.spring.util.Common.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.spring.b2b.admin.EnvController;
import com.spring.service.config.SiteConfigService;
import com.spring.util.file.FileUtil;
import com.spring.vo.config.SiteConfigVO;

@Controller
@RequestMapping("/admin/config")
public class ConfigController extends EnvController {

	private final Logger logger = LoggerFactory.getLogger(ConfigController.class);
	
	@Autowired
	protected SiteConfigService siteConfigService;
	@Autowired
    private MessageSource messageSource;
	
	/*
	 * 买家app环境设置 列表
	 * */
	@RequestMapping(value = "/buyerAppConfigList.do", method = RequestMethod.GET)
	public String buyerAppConfigList(ModelMap model) {

		final SiteConfigVO siteConfigVO = new SiteConfigVO("buyer");
		
		List<SiteConfigVO> siteConfigVOs = siteConfigService.getSiteConfigs(siteConfigVO);

		model.addAttribute("siteConfigs", siteConfigVOs);
		
		return "admin/config/buyerAppConfigList";
	}
	
	/*
	 * 修改环境处理
	 * */
	@RequestMapping(value = "/modifySiteConfig.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> modifySiteConfig(@RequestParam(value = "configCode[]", required = true) String[] configCodes,
			@RequestParam(value = "configVal[]", required = true) String[] configVals,
			@RequestParam(value = "editType[]", required = true) String[] editTypes,
			Locale locale) throws Exception {

		final int configCodeCount = configCodes.length;
		if (configCodeCount == 0) {
			
			return output("1", null, messageSource.getMessage("program_error", null, locale));
		}
		
		final int configValCount = configVals.length;
		if (configCodeCount != configValCount) {
			
			return output("1", null, messageSource.getMessage("program_error", null, locale));
		}
		
		final List<SiteConfigVO> siteConfigVOs = new ArrayList<>();
		
		int index = 0;
		for (String code : configCodes) {
			
			if ("".equals(code)) {
				
				return output("1", null, messageSource.getMessage("program_error", null, locale));
			}
			
			if ("".equals(configVals[index])) {
			
				return output("1", null, messageSource.getMessage("not_empty_config_val", null, locale));//配置内容不能为空
			}
			
			index++;
		}
		//此次更新后要存储的图Url
		final Map<String, String> configValImages = new HashMap<>(); 
		index = 0;
		for (String type : editTypes) {
			
			if ("editor".equals(type)) {
				
		        //editer中temp图移动到正式目录
		        Map<String, String> editorContentMap = FileUtil.editorContentFilesUpload("config", configVals[index], super.propertiesService.getProperty("image.baseUrl"));
		        if (!"".equals(editorContentMap.get("fileUrl"))) {
		        	
		        	configValImages.put(configCodes[index], editorContentMap.get("fileUrl"));
		        }
		        //temp地址的文件移除转换正式地址
		        configVals[index] = editorContentMap.get("editorContent");
			}
			
			index++;
		}
		//此次上传的图temp目录移到正式目录后重新赋值
		index = 0;
		for (String code : configCodes) {
			
			siteConfigVOs.add(new SiteConfigVO(code, configVals[index]));
			
			index++;
		}
		//获取之前存的图Url
		final SiteConfigVO siteConfigVO = new SiteConfigVO("buyer");
		siteConfigVO.setEditType("editor");
		final List<SiteConfigVO> configValForCheckImages = siteConfigService.getSiteConfigs(siteConfigVO);
		
		index = 0;
		for (String type : editTypes) {
			
			if ("editor".equals(type)) {
				
				for (SiteConfigVO _siteConfigVO : configValForCheckImages) {
					
					if (configCodes[index].equals(_siteConfigVO.getConfigCode())) {
						
						String imageUrls = "";
						if (configValImages.containsKey(configCodes[index])) {
							
							imageUrls = configValImages.get(configCodes[index]);
						}
						//已有文件中删除此次去掉的文件
						FileUtil.editorContentFileFilterDelete("config", _siteConfigVO.getConfigVal(), super.propertiesService.getProperty("image.baseUrl"), imageUrls);
					}
				}
			}
			
			index++;
		}

		return siteConfigService.modifySiteConfig(siteConfigVOs, locale);
	}
	
	
	/*
	 * 基本环境设置 列表
	 * */
	@RequestMapping(value = "/systemConfigList.do", method = RequestMethod.GET)
	public String systemConfigList(ModelMap model) {

		final SiteConfigVO siteConfigVO = new SiteConfigVO("system");
		
		List<SiteConfigVO> siteConfigVOs = siteConfigService.getSiteConfigs(siteConfigVO);

		model.addAttribute("siteConfigs", siteConfigVOs);
		
		return "admin/config/systemConfigList";
	}
	
	/*
	 * 修改基本环境 处理
	 * */
	@RequestMapping(value = "/modifySystemConfig.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> modifySystemConfig(@RequestParam(value = "configCode[]", required = true) String[] configCodes,
			@RequestParam(value = "configVal[]", required = true) String[] configVals,
			@RequestParam(value = "editType[]", required = true) String[] editTypes,
			Locale locale) throws Exception {

		final int configCodeCount = configCodes.length;
		if (configCodeCount == 0) {
			
			return output("1", null, messageSource.getMessage("program_error", null, locale));
		}
		
		final int configValCount = configVals.length;
		if (configCodeCount != configValCount) {
			
			return output("1", null, messageSource.getMessage("program_error", null, locale));
		}
		
		final List<SiteConfigVO> siteConfigVOs = new ArrayList<>();
		
		int index = 0;
		for (String code : configCodes) {
			
			if ("".equals(code)) {
				
				return output("1", null, messageSource.getMessage("program_error", null, locale));
			}
			
			if ("".equals(configVals[index])) {
			
				return output("1", null, messageSource.getMessage("not_empty_config_val", null, locale));//配置内容不能为空
			}
			
			index++;
		}
		//此次更新后要存储的图Url
		final Map<String, String> configValImages = new HashMap<>(); 
		index = 0;
		for (String type : editTypes) {
			
			if ("editor".equals(type)) {
				
		        //editer中temp图移动到正式目录
		        Map<String, String> editorContentMap = FileUtil.editorContentFilesUpload("config", configVals[index], super.propertiesService.getProperty("image.baseUrl"));
		        if (!"".equals(editorContentMap.get("fileUrl"))) {
		        	
		        	configValImages.put(configCodes[index], editorContentMap.get("fileUrl"));
		        }
		        //temp地址的文件移除转换正式地址
		        configVals[index] = editorContentMap.get("editorContent");
			}
			
			index++;
		}
		//此次上传的图temp目录移到正式目录后重新赋值
		index = 0;
		for (String code : configCodes) {
			
			siteConfigVOs.add(new SiteConfigVO(code, configVals[index]));
			
			index++;
		}
		//获取之前存的图Url
		final SiteConfigVO siteConfigVO = new SiteConfigVO("system");
		siteConfigVO.setEditType("editor");
		final List<SiteConfigVO> configValForCheckImages = siteConfigService.getSiteConfigs(siteConfigVO);
		
		index = 0;
		for (String type : editTypes) {
			
			if ("editor".equals(type)) {
				
				for (SiteConfigVO _siteConfigVO : configValForCheckImages) {
					
					if (configCodes[index].equals(_siteConfigVO.getConfigCode())) {
						
						String imageUrls = "";
						if (configValImages.containsKey(configCodes[index])) {
							
							imageUrls = configValImages.get(configCodes[index]);
						}
						//已有文件中删除此次去掉的文件
						FileUtil.editorContentFileFilterDelete("config", _siteConfigVO.getConfigVal(), super.propertiesService.getProperty("image.baseUrl"), imageUrls);
					}
				}
			}
			
			index++;
		}

		return siteConfigService.modifySiteConfig(siteConfigVOs, locale);
	}
}