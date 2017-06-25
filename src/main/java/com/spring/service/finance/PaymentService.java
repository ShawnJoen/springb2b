package com.spring.service.finance;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import com.spring.dto.finance.Payment;
import com.spring.vo.finance.PaymentVO;

public interface PaymentService {

	PaymentVO getPaymentByPaymentCode(String paymentCode);
	List<PaymentVO> getPayments(PaymentVO paymentVO);
	Map<String, Object> modifyPayment(Payment payment, Locale locale) throws Exception;
}