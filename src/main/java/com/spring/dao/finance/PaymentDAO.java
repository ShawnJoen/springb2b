package com.spring.dao.finance;

import java.util.List;
import com.spring.dto.finance.Payment;
import com.spring.vo.finance.PaymentVO;

public interface PaymentDAO {

	PaymentVO getPaymentByPaymentCode(String paymentCode);
	List<PaymentVO> getPayments(PaymentVO paymentVO);
	int modifyPayment(Payment payment) throws Exception;
}