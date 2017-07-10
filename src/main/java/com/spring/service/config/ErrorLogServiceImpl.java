package com.spring.service.config;

import static com.spring.util.Common.*;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.spring.dao.config.ErrorLogDAO;
import com.spring.dto.config.ErrorLog;
import com.spring.util.validation.ValidationResult;
import com.spring.util.validation.ValidationUtils;
import com.spring.vo.config.ErrorLogVO;

@Service("errorLogServiceImpl")
public class ErrorLogServiceImpl implements ErrorLogService {
	
	@Autowired
    private ErrorLogDAO errorLogDAO;
	//@Autowired
    //private OperationRecordService operationRecordService;
	@Autowired
    private MessageSource messageSource;

	@Transactional("transaction")
	@Override
	public Map<String, Object> createErrorLog(ErrorLog errorLog, Locale locale) throws Exception {
		
		String[] fieldNames = {"userType","deviceType","deviceId","brandName","osVersion","appVersion","error"};
		ValidationResult ValidResult = null;
		for (String fieldName: fieldNames) {

			ValidResult = ValidationUtils.validateProperty(errorLog, fieldName);
			if (ValidResult.isHasErrors()) {
				
				return output("1", null, 
						ValidResult.getErrorMsg().get(fieldName));
			}
		}
		
		errorLog.setProjectName(messageSource.getMessage("project_name", null, locale));
		
		errorLogDAO.createErrorLog(errorLog);
		
		return output("0", null, messageSource.getMessage("create_seccess", null, locale));
	}
	@Override
	public ErrorLog getErrorLogById(int errorLogId) {
		
		return errorLogDAO.getErrorLogById(errorLogId);
	}
	@Override
	public List<ErrorLogVO> getErrorLogs(ErrorLogVO errorLogVO) {
		
		return errorLogDAO.getErrorLogs(errorLogVO);
	}
}