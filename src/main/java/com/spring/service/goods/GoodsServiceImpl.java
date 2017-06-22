package com.spring.service.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service("goodsServiceImpl")
public class GoodsServiceImpl implements GoodsService {

	@Autowired
    private MessageSource messageSource;
	
	
}