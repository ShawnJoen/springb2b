package com.spring.b2b.admin.goods;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.spring.b2b.admin.EnvController;

@Controller
@RequestMapping("/admin/goods")
public class GoodsController extends EnvController {

	private final Logger logger = LoggerFactory.getLogger(GoodsController.class);
	
	@Autowired
    private MessageSource messageSource;

}