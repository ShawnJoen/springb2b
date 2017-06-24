package com.spring.service.config;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import com.spring.dto.config.ErrorLog;
import com.spring.vo.config.ErrorLogVO;

public interface ErrorLogService {
	/*
	 * 插入错误日志
	 * */
	Map<String, Object> createErrorLog(ErrorLog errorLog, Locale locale) throws Exception;
	/*
	 * 获取错误日志
	 * */
	ErrorLog getErrorLogById(int errorLogId);
	/*
	 * 获取错误日志列表
	 * */
	List<ErrorLogVO> getErrorLogs(ErrorLogVO errorLogVO);
}