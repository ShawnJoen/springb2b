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