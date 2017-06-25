package com.spring.b2b.admin.finance;

import static com.spring.util.Common.output;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageInfo;
import com.spring.b2b.admin.EnvController;
import com.spring.dto.finance.Payment;
import com.spring.util.validation.ValidationResult;
import com.spring.util.validation.ValidationUtils;
import com.spring.vo.finance.PaymentVO;

@Controller
@RequestMapping("/admin/finance")
public class FinanceController extends EnvController {
	
	private final Logger logger = LoggerFactory.getLogger(FinanceController.class);
	
	@Autowired
    private MessageSource messageSource;
	
	/*
	 * 支付方式列表
	 * */
	@RequestMapping(value = "/paymentList.do", method = RequestMethod.GET)
	public String goodsStandardList(@RequestParam(value = "pageNum", required = false, defaultValue="1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue="20") Integer pageSize, 
            @ModelAttribute("payment") PaymentVO paymentVO, 
            ModelMap model) {

		super.setPageHelper(pageNum, pageSize);
		
		List<PaymentVO> paymentVOs = paymentService.getPayments(paymentVO);
		
		PageInfo<PaymentVO> pageInfo = new PageInfo<>(paymentVOs);
		model.addAttribute("pageInfo", pageInfo);
		//序号
		long sequence = pageInfo.getTotal() - (pageInfo.getPageNum() - 1) * pageInfo.getPageSize() + 1;
		model.addAttribute("sequence", sequence);
		//搜索框绑定搜索字段
		model.addAttribute("payment", paymentVO);
		
		return "admin/finance/paymentList";
	}
	/*
	 * 修改支付方式 from
	 * */
	@RequestMapping(value = "/modifyPayment.do", method = RequestMethod.GET)
	public String modifyPaymentForm(@RequestParam(value = "paymentCode", required = true) String paymentCode,
			ModelMap model) {
		
		PaymentVO paymentVO = paymentService.getPaymentByPaymentCode(paymentCode);
		model.addAttribute("payment", paymentVO);
		
		return "admin/finance/modifyPayment";
	}
	/*
	 * 修改支付方式 处理
	 * */
	@RequestMapping(value = "/modifyPayment.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> modifyPayment(@ModelAttribute("Payment") Payment payment, 
			BindingResult result,
			Locale locale) throws Exception {
		
		if (result.hasErrors()) {

            return output("1", null, messageSource.getMessage("program_error", null, locale));
        }
		
		final ValidationResult ValidResult = ValidationUtils.validation(payment);
		if (ValidResult.isHasErrors() || "".equals(payment.getPaymentCode())) {
			
			return output("1", null, ValidResult.getErrorMessage());
		}
		
		return paymentService.modifyPayment(payment, locale);
	}
}