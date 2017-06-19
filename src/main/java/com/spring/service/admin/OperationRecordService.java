package com.spring.service.admin;

import java.util.List;
import com.spring.dto.admin.OperationRecord;

public interface OperationRecordService {
	/*
	 * 创建操作记录
	 * */
	void createOperationRecord(String content) throws Exception;
	/*
	 * 操作记录列表
	 * */
	List<OperationRecord> getOperationRecords(OperationRecord operationRecord);
}