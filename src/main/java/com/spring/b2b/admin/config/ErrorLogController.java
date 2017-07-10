package com.spring.b2b.admin.config;

import static com.spring.util.Common.output;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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
import com.github.pagehelper.PageInfo;
import com.spring.b2b.admin.EnvController;
import com.spring.dto.config.ErrorLog;
import com.spring.util.validation.ValidationResult;
import com.spring.util.validation.ValidationUtils;
import com.spring.vo.config.ErrorLogVO;

@Controller
@RequestMapping("/admin/config")
public class ErrorLogController extends EnvController {

	private final Logger logger = LoggerFactory.getLogger(ErrorLogController.class);
	
	@Autowired
    private MessageSource messageSource;
	
	/*
	 * 创建商品库商品 from
	 * */
	@RequestMapping(value = "/createErrorLog.do", method = RequestMethod.GET)
	public String createErrorLogForm(ModelMap model) {

		model.addAttribute("errorLog", new ErrorLog());
		
		return "admin/config/createErrorLog";
	}
	/*
	 * 创建商品库商品处理
	 * */
	@RequestMapping(value = "/createErrorLog.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> createErrorLog(@ModelAttribute("errorLog") ErrorLog errorLog,
			BindingResult result,
			Locale locale) throws Exception {

		if (result.hasErrors()) {

            return output("1", null, messageSource.getMessage("program_error", null, locale));
        }
		
		final ValidationResult ValidResult = ValidationUtils.validation(errorLog);
		if (ValidResult.isHasErrors()) {
			
			return output("1", null, ValidResult.getErrorMessage());
		}
		
		return errorLogService.createErrorLog(errorLog, locale);
	}
	/*
	 * 获取商品库商品列表
	 * */
	@RequestMapping(value = "/errorLogList.do", method = RequestMethod.GET)
	public String errorLogList(@RequestParam(value = "pageNum", required = false, defaultValue="1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue="20") Integer pageSize, 
            @ModelAttribute("errorLogVO") ErrorLogVO errorLogVO, 
            ModelMap model) {

		super.setPageHelper(pageNum, pageSize);
		
		List<ErrorLogVO> errorLogVOs = errorLogService.getErrorLogs(errorLogVO);
		
		PageInfo<ErrorLogVO> pageInfo = new PageInfo<>(errorLogVOs);
		model.addAttribute("pageInfo", pageInfo);
		//序号
		long sequence = pageInfo.getTotal() - (pageInfo.getPageNum() - 1) * pageInfo.getPageSize() + 1;
		model.addAttribute("sequence", sequence);
		//搜索框绑定搜索字段
		model.addAttribute("errorLog", errorLogVO);
		
		return "admin/config/errorLogList";
	}
	/*
	 * 创建商品库商品 from
	 * */
	@RequestMapping(value = "/viewErrorLog.do", method = RequestMethod.GET)
	public String viewErrorLog(@RequestParam(value = "id", required = true) Integer id, 
			ModelMap model) {

		final ErrorLog errorLog = errorLogService.getErrorLogById(id);
		model.addAttribute("errorLog", errorLog);
		
		return "admin/config/viewErrorLog";
	}
}