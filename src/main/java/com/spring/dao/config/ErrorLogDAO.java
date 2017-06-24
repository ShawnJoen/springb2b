package com.spring.dao.config;

import java.util.List;
import com.spring.dto.config.ErrorLog;
import com.spring.vo.config.ErrorLogVO;

public interface ErrorLogDAO {
	void createErrorLog(ErrorLog errorLog) throws Exception;
	ErrorLog getErrorLogById(int errorLogId);
	List<ErrorLogVO> getErrorLogs(ErrorLogVO errorLogVO);
}