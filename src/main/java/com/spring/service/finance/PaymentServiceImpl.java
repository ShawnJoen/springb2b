package com.spring.service.finance;

import static com.spring.util.Common.*;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.spring.dao.finance.PaymentDAO;
import com.spring.dto.finance.Payment;
import com.spring.service.admin.OperationRecordService;
import com.spring.vo.finance.PaymentVO;

@Service("paymentServiceImpl")
public class PaymentServiceImpl implements PaymentService {

	@Autowired
    private PaymentDAO paymentDAO;
	@Autowired
    private OperationRecordService operationRecordService;
	@Autowired
    private MessageSource messageSource;
	
	@Override
	public PaymentVO getPaymentByPaymentCode(String paymentCode) {

		return paymentDAO.getPaymentByPaymentCode(paymentCode);
	}
	
	@Override
	public List<PaymentVO> getPayments(PaymentVO paymentVO) {
		
		return paymentDAO.getPayments(paymentVO);
	}
	
	@Transactional("transaction")
	@Override
	public Map<String, Object> modifyPayment(Payment payment, Locale locale) throws Exception {

		final int result = paymentDAO.modifyPayment(payment);
		if (result == 0) {
			
			return output("1", null, messageSource.getMessage("modify_fail", null, locale));
		} else {
			
			operationRecordService.createOperationRecord("修改支付账号信息  支付编号：" + payment.getPaymentCode());
			
			return output("0", null, messageSource.getMessage("modify_seccess", null, locale));
		}
	}
}