package com.spring.dao.admin;

import java.util.List;
import com.spring.dto.admin.OperationRecord;
import com.spring.vo.admin.OperationRecordVO;

public interface OperationRecordDAO {
	void createOperationRecord(OperationRecord operationRecord) throws Exception;
	List<OperationRecordVO> getOperationRecords(OperationRecordVO operationRecordVO);
}