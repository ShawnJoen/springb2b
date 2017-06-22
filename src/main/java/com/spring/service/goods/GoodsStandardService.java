package com.spring.service.goods;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import com.spring.dto.goods.GoodsStandard;
import com.spring.vo.goods.GoodsStandardVO;

public interface GoodsStandardService {
	/*
	 * 创建商品库商品
	 * */
	Map<String, Object> createGoodsStandard(GoodsStandardVO goodsStandardVO, Locale locale) throws Exception;
	/*
	 * 查询指定商品库商品是否存在
	 * */
	int hasGoodsStandardById(int goodsStandardId);
	/*
	 * 获取商品库商品信息
	 * */
	GoodsStandardVO getGoodsStandardById(int goodsStandardId);
	/*
	 * 获取商品库商品详情
	 * */
	GoodsStandard getGoodsBriefById(int goodsStandardId);
	/*
	 * 获取商品库商品列表
	 * */
	List<GoodsStandardVO> getGoodsStandards(GoodsStandardVO goodsStandardVO);
	/*
	 * 删除商品库商品
	 * */
	Map<String, Object> deleteGoodsStandard(GoodsStandardVO goodsStandardVO, Locale locale) throws Exception;
	/*
	 * 修改商品库商品
	 * */
	Map<String, Object> modifyGoodsStandard(GoodsStandardVO goodsStandardVO, Locale locale) throws Exception;
}