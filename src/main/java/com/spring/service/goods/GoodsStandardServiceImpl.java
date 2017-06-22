package com.spring.service.goods;

import static com.spring.util.Common.*;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.spring.dao.goods.GoodsStandardDAO;
import com.spring.dto.goods.GoodsStandard;
import com.spring.service.admin.OperationRecordService;
import com.spring.vo.goods.GoodsStandardVO;

@Service("goodsStandardServiceImpl")
public class GoodsStandardServiceImpl implements GoodsStandardService {

	@Autowired
    private GoodsStandardDAO goodsStandardDAO;
	@Autowired
    private OperationRecordService operationRecordService;
	@Autowired
    private MessageSource messageSource;

	@Transactional("transaction")
	@Override
	public Map<String, Object> createGoodsStandard(GoodsStandardVO goodsStandardVO, Locale locale) throws Exception {

		goodsStandardDAO.createGoodsStandard(goodsStandardVO);
		
		operationRecordService.createOperationRecord("创建商品库商品 商品名称：" + goodsStandardVO.getGoodsName());

		return output("0", null, messageSource.getMessage("create_seccess", null, locale));
	}
	@Override
	public int hasGoodsStandardById(int goodsStandardId) {

		return goodsStandardDAO.hasGoodsStandardById(goodsStandardId);
	}
	@Override
	public GoodsStandardVO getGoodsStandardById(int goodsStandardId) {

		return goodsStandardDAO.getGoodsStandardById(goodsStandardId);
	}
	@Override
	public GoodsStandard getGoodsBriefById(int goodsStandardId) {

		return goodsStandardDAO.getGoodsBriefById(goodsStandardId);
	}
	@Override
	public List<GoodsStandardVO> getGoodsStandards(GoodsStandardVO goodsStandardVO) {
		
		return goodsStandardDAO.getGoodsStandards(goodsStandardVO);
	}
	@Transactional("transaction")
	@Override
	public Map<String, Object> deleteGoodsStandard(GoodsStandardVO goodsStandardVO, Locale locale) throws Exception {
		
		final String timeStamps = getTimeStamps();
		goodsStandardVO.setUpdateTime(timeStamps);
		
		final int result = goodsStandardDAO.deleteGoodsStandard(goodsStandardVO);
		if (result == 0) {
			
			return output("1", null, messageSource.getMessage("delete_fail", null, locale));
		} else {

			operationRecordService.createOperationRecord("删除商品库商品 商品编号：" + goodsStandardVO.getGoodsStandardId());
			
			return output("0", null, messageSource.getMessage("delete_seccess", null, locale));
		}
	}
	@Transactional("transaction")
	@Override
	public Map<String, Object> modifyGoodsStandard(GoodsStandardVO goodsStandardVO, Locale locale) throws Exception {

        final String timeStamps = getTimeStamps();
		goodsStandardVO.setUpdateTime(timeStamps);
		
		final int result = goodsStandardDAO.modifyGoodsStandard(goodsStandardVO);
		if (result == 0) {
			
			return output("1", null, messageSource.getMessage("modify_fail", null, locale));
		} else {
			
			operationRecordService.createOperationRecord("修改商品库商品信息  商品编号：" + goodsStandardVO.getGoodsStandardId());
			
			return output("0", null, messageSource.getMessage("modify_seccess", null, locale));
		}
	}
}