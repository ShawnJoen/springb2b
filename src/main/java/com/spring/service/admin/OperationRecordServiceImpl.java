package com.spring.service.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.dao.admin.OperationRecordDAO;
import com.spring.dto.admin.OperationRecord;
import com.spring.util.Common;

@Service("operationRecordServiceImpl")
public class OperationRecordServiceImpl implements OperationRecordService {
	
	@Autowired
	private OperationRecordDAO operationRecordDAO;
	@Override
	public List<OperationRecord> getOperationRecords(OperationRecord operationRecord) {

		return operationRecordDAO.getOperationRecords(operationRecord);
	}
	@Override
	public void createOperationRecord(String content) throws Exception {
		
		final String username = Common.getLogInUsername();
		if (Common.isLogIn(username)) {
			
			operationRecordDAO.createOperationRecord(
					new OperationRecord(
						username, 
						content
					)
				);
		}
	}
}