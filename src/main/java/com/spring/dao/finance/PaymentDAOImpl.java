package com.spring.dao.finance;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.spring.dto.finance.Payment;
import com.spring.vo.finance.PaymentVO;

@Repository
public class PaymentDAOImpl implements PaymentDAO {

	@Autowired
	private SqlSession sqlSession;
	
	static final String PAYMENT_MAPPER = "com.spring.mappers.PaymentMapper.";
	
	@Override
	public PaymentVO getPaymentByPaymentCode(String paymentCode) {
		
		return sqlSession.selectOne(PAYMENT_MAPPER + "getPaymentByPaymentCode", paymentCode);
	}
	@Override
	public List<PaymentVO> getPayments(PaymentVO paymentVO) {
		
		return sqlSession.selectList(PAYMENT_MAPPER + "getPayments", paymentVO);
	}
	@Override
	public int modifyPayment(Payment payment) throws Exception {
		
		return sqlSession.update(PAYMENT_MAPPER + "modifyPayment", payment);
	}
}