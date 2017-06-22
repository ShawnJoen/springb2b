package com.spring.dao.goods;

import java.util.List;
import com.spring.dto.goods.GoodsStandard;
import com.spring.vo.goods.GoodsStandardVO;

public interface GoodsStandardDAO {
	
	void createGoodsStandard(GoodsStandardVO goodsStandardVO) throws Exception;
	int hasGoodsStandardById(int goodsStandardId);
	GoodsStandard getGoodsBriefById(int goodsStandardId);
	List<GoodsStandardVO> getGoodsStandards(GoodsStandardVO goodsStandardVO);
	int modifyGoodsStandard(GoodsStandardVO goodsStandardVO) throws Exception;
	int deleteGoodsStandard(GoodsStandardVO goodsStandardVO) throws Exception;
	GoodsStandardVO getGoodsStandardById(int goodsStandardId);
}