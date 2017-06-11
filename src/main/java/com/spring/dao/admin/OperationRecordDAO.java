package com.spring.dao.admin;

import java.util.List;

import com.spring.dto.admin.OperationRecord;

public interface OperationRecordDAO {
	void createOperationRecord(OperationRecord operationRecord) throws Exception;
	List<OperationRecord> getOperationRecords(OperationRecord operationRecord);
}