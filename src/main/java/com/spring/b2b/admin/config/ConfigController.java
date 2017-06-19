package com.spring.b2b.admin.config;

import static com.spring.util.Common.*;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.spring.b2b.admin.EnvController;
import com.spring.dto.config.SiteConfig;
import com.spring.service.config.SiteConfigService;
import com.spring.util.file.FileUtil;

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

		final SiteConfig siteConfig = new SiteConfig("buyer");
		
		List<SiteConfig> siteConfigs = siteConfigService.getSiteConfigs(siteConfig);

		model.addAttribute("siteConfigs", siteConfigs);
		
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
		
		final List<SiteConfig> siteConfigs = new ArrayList<>();
		
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
		//临时存储Url
		final String docbase = FileUtil.DOCBASE;
		final String baseUrl = super.propertiesService.getProperty("image.baseUrl");
		//临时存储Url
		final String tempUrl = baseUrl + "temp/";// (/upload/temp/)
		//新存储Url
		final String newUrl = baseUrl + "config/";// (/upload/config/)
		//临时存储Pattern
		final Pattern tempUrlPattern = Pattern.compile(tempUrl + "[^\"]+");
		//图片PathUrlPattern
		final Pattern newUrlPattern = Pattern.compile(newUrl + "[^\"]+");
		index = 0;
		for (String type : editTypes) {
			
			if ("editor".equals(type)) {
				
				StringBuffer imagesUrlBuffer = new StringBuffer();
				
				if (configVals[index].contains("/temp/")) {
					
			        Matcher matchers = tempUrlPattern.matcher(configVals[index]);  
			        while (matchers.find()) {  
			            //要新生成的文件Path
			            String newFilePath = matchers.group().replace(tempUrl, "config/");// D:/xampp/htdocs/java/upload/config/2017/06/18/??.??
			            FileOutputStream fos = new FileOutputStream(FileUtil.makeEmptyFile(newFilePath));
			            //原文件Path
			            String oldFilePath = docbase + matchers.group().replace(baseUrl, "");// file::/xampp/htdocs/java/upload/temp/2017/06/18/??.??
			            FileSystemResource resource = new FileSystemResource(oldFilePath);
			            //resource 复制给 fos
			            FileCopyUtils.copy(resource.getInputStream(), fos);
			            //删除源文件
			            FileUtil.deleteFile(oldFilePath);
			            //要新生成的文件Url
			            String newFileUrl = matchers.group().replace(tempUrl, newUrl);
			            //改后 新生成的文件Path
			            configVals[index] = configVals[index].replace(matchers.group(), newFileUrl);
			            
			            if (imagesUrlBuffer.length() > 0) {
			            	
			            	imagesUrlBuffer.append(",");
			            }
			            //此编辑器此次新上传的图Url(,)分割符存
			            imagesUrlBuffer.append(newFileUrl);
			        }
				}
				
				Matcher matchers = newUrlPattern.matcher(configVals[index]);  
		        while (matchers.find()) {  
		        	
		        	if (imagesUrlBuffer.length() > 0) {
		            	
		            	imagesUrlBuffer.append(",");
		            }
		            //此编辑器之前已上传的图Url(,)分割符存
		            imagesUrlBuffer.append(matchers.group());
		        }
				
		        if (imagesUrlBuffer.length() > 0) {
		        	
		        	//存此编辑器所有图Url
		        	configValImages.put(configCodes[index], imagesUrlBuffer.toString());
	            }
			}
			
			index++;
		}
		//此次上传的图temp目录移到正式目录后重新赋值
		index = 0;
		for (String code : configCodes) {
			
			siteConfigs.add(new SiteConfig(code, configVals[index]));
			
			index++;
		}
		//获取之前存的图Url
		final SiteConfig siteConfig = new SiteConfig("buyer");
		siteConfig.setEditType("editor");
		final List<SiteConfig> configValForCheckImages = siteConfigService.getSiteConfigs(siteConfig);
		
		index = 0;
		for (String type : editTypes) {
			
			if ("editor".equals(type)) {
				
				for (SiteConfig _siteConfig : configValForCheckImages) {
					
					if (configCodes[index].equals(_siteConfig.getConfigCode())) {

						StringBuffer imagesUrlBuffer = new StringBuffer();
						
				        Matcher matchers = newUrlPattern.matcher(_siteConfig.getConfigVal());  
				        while (matchers.find()) {  
				            
				            if (imagesUrlBuffer.length() > 0) {
				            	
				            	imagesUrlBuffer.append(",");
				            }
				            //此编辑器所有图Url(,)分割符存
				            imagesUrlBuffer.append(matchers.group());
				        }
				        //之前存过图时
						if (imagesUrlBuffer.length() > 0) {
							
							String imageUrls = "";
							if (configValImages.containsKey(configCodes[index])) {
								
								imageUrls = configValImages.get(configCodes[index]);
							}
							//删除之前已上传中取消的图link
							List<String> willDeleteImages = compareDifferentArray(imageUrls.split("\\,"), imagesUrlBuffer.toString().split("\\,"));
							//删除图片
							FileUtil.deleteFiles(StringUtils.join(willDeleteImages.toArray(), ","), baseUrl);
						}
					}
				}
			}
			
			index++;
		}

		return siteConfigService.modifySiteConfig(siteConfigs, locale);
	}
}