package com.spring.service.admin;

import java.util.List;
import com.spring.dto.admin.OperationRecord;

public interface OperationRecordService {
	/*
	 * ����������¼
	 * */
	void createOperationRecord(String content) throws Exception;
	/*
	 * ������¼�б�
	 * */
	List<OperationRecord> getOperationRecords(OperationRecord operationRecord);
}