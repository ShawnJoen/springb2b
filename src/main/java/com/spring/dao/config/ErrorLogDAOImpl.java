package com.spring.dao.config;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.spring.dto.config.ErrorLog;
import com.spring.vo.config.ErrorLogVO;

@Repository
public class ErrorLogDAOImpl implements ErrorLogDAO {

	@Autowired
	private SqlSession sqlSession;
	
	static final String ERROR_LOG_MAPPER = "com.spring.mappers.ErrorLogMapper.";
	
	@Override
	public void createErrorLog(ErrorLog errorLog) throws Exception {
		
		sqlSession.insert(ERROR_LOG_MAPPER + "createErrorLog", errorLog);
	}
	@Override
	public ErrorLog getErrorLogById(int errorLogId) {

		return sqlSession.selectOne(ERROR_LOG_MAPPER + "getErrorLogById", errorLogId);
	}
	@Override
	public List<ErrorLogVO> getErrorLogs(ErrorLogVO errorLogVO) {
		
		return sqlSession.selectList(ERROR_LOG_MAPPER + "getErrorLogs", errorLogVO);
	}
}