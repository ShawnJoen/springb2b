package com.spring.b2b.admin.goods;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.multipart.MultipartFile;
import com.github.pagehelper.PageInfo;
import com.spring.b2b.admin.EnvController;
import com.spring.dto.goods.GoodsStandard;
import com.spring.util.file.FileUtil;
import com.spring.util.file.ImageUtil;
import com.spring.util.validation.ValidationResult;
import com.spring.util.validation.ValidationUtils;
import com.spring.vo.goods.GoodsStandardVO;
import static com.spring.util.Common.*;

@Controller
@RequestMapping("/admin/goods")
public class GoodsStandardController extends EnvController {

	private final Logger logger = LoggerFactory.getLogger(GoodsStandardController.class);
	
	@Autowired
    private MessageSource messageSource;
	
	/*
	 * 创建商品库商品 from
	 * */
	@RequestMapping(value = "/createGoodsStandard.do", method = RequestMethod.GET)
	public String createGoodsStandardForm(ModelMap model) {

		model.addAttribute("goodsStandard", new GoodsStandardVO());
		
		return "admin/goods/createGoodsStandard";
	}
	/*
	 * 创建商品库商品处理
	 * */
	@RequestMapping(value = "/createGoodsStandard.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> createGoodsStandard(@ModelAttribute("goodsStandard") GoodsStandardVO goodsStandardVO, 
			BindingResult result,
			MultipartFile _goodsImages[],
			Locale locale) throws Exception {

		if (result.hasErrors()) {

            return output("1", null, messageSource.getMessage("program_error", null, locale));
        }
		
		final ValidationResult ValidResult = ValidationUtils.validation(goodsStandardVO);
		if (ValidResult.isHasErrors()) {
			
			return output("1", null, ValidResult.getErrorMessage());
		}
		//查是否为图
		if (!ImageUtil.checkImageFiles(_goodsImages)) {
			
			return output("1", null, messageSource.getMessage("select_image", null, locale));//请选择图片
		}
		//上传文件(商品图)
		final StringBuilder imagePaths = FileUtil.uploadFiles("goods", _goodsImages, super.propertiesService.getProperty("image.baseUrl"));
		
		if (imagePaths.length() == 0) {
			
			return output("1", null, messageSource.getMessage("select_image", null, locale));//请选择图片
		}
		
		goodsStandardVO.setGoodsImages(imagePaths.toString());
		//editer中temp图移动到正式目录
		final Map<String, String> editorContentMap = FileUtil.editorContentFilesUpload("goods", goodsStandardVO.getGoodsBrief(), super.propertiesService.getProperty("image.baseUrl"));
		
		goodsStandardVO.setGoodsBrief(editorContentMap.get("editorContent"));
		
		return goodsStandardService.createGoodsStandard(goodsStandardVO, locale);
	}
	/*
	 * 获取商品库商品列表
	 * */
	@RequestMapping(value = "/goodsStandardList.do", method = RequestMethod.GET)
	public String goodsStandardList(@RequestParam(value = "pageNum", required = false, defaultValue="1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue="20") Integer pageSize, 
            @ModelAttribute("goodsStandard") GoodsStandardVO goodsStandardVO, 
            ModelMap model) {

		super.setPageHelper(pageNum, pageSize);
		
		List<GoodsStandardVO> goodsStandardVOs = goodsStandardService.getGoodsStandards(goodsStandardVO);
		
		PageInfo<GoodsStandardVO> pageInfo = new PageInfo<>(goodsStandardVOs);
		model.addAttribute("pageInfo", pageInfo);
		//序号
		long sequence = pageInfo.getTotal() - (pageInfo.getPageNum() - 1) * pageInfo.getPageSize() + 1;
		model.addAttribute("sequence", sequence);
		//搜索框绑定搜索字段
		model.addAttribute("goodsStandard", goodsStandardVO);
		
		return "admin/goods/goodsStandardList";
	}
	/*
	 * 商品库商品删除
	 * */
	@RequestMapping(value = "/deleteGoodsStandard.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteGoodsStandard(@ModelAttribute("goodsStandard") GoodsStandardVO goodsStandardVO, 
			BindingResult result,
			Locale locale) throws Exception {
		
		if (result.hasErrors()) {

            return output("1", null, messageSource.getMessage("program_error", null, locale));
        }
		
		return goodsStandardService.deleteGoodsStandard(goodsStandardVO, locale);
	}
	/*
	 * 修改商品库商品 from
	 * */
	@RequestMapping(value = "/modifyGoodsStandard.do", method = RequestMethod.GET)
	public String modifyGoodsStandardForm(@RequestParam(value = "goodsStandardId", required = true) Integer goodsStandardId,
			ModelMap model) {
		
		GoodsStandardVO goodsStandardVO = goodsStandardService.getGoodsStandardById(goodsStandardId);
		model.addAttribute("goodsStandard", goodsStandardVO);
		
		return "admin/goods/modifyGoodsStandard";
	}
	/*
	 * 修改商品库商品处理
	 * */
	@RequestMapping(value = "/modifyGoodsStandard.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> modifyGoodsStandard(@ModelAttribute("goodsStandard") GoodsStandardVO goodsStandardVO, 
			BindingResult result,
			MultipartFile _goodsImages[],
			@RequestParam(value = "_goodsImagesUploaded[]", required = false) String[] _goodsImagesUploaded,
			Locale locale) throws Exception {
		
		if (result.hasErrors()) {

            return output("1", null, messageSource.getMessage("program_error", null, locale));
        }
		
		final ValidationResult ValidResult = ValidationUtils.validation(goodsStandardVO);
		if (ValidResult.isHasErrors() || "".equals(goodsStandardVO.getGoodsStandardId())) {
			
			return output("1", null, ValidResult.getErrorMessage());
		}
		
		final GoodsStandard goodsStandard = goodsStandardService.getGoodsBriefById(Integer.parseInt(goodsStandardVO.getGoodsStandardId()));
		if (goodsStandard == null) {
			
			return output("1", null, ValidResult.getErrorMessage());
		}
		
		//编辑上传文件
		final StringBuilder imagePaths = FileUtil.uploadAndDeleteFiles("goods", _goodsImages, _goodsImagesUploaded, super.propertiesService.getProperty("image.baseUrl"));
		
		if (imagePaths.length() == 0) {
			
			return output("1", null, messageSource.getMessage("select_image", null, locale));//请选择图片
		}
		//删除之前已上传中取消的图link
		final List<String> willDeleteImages = compareDifferentArray(imagePaths.toString().split("\\,"), goodsStandardVO.getGoodsImages().split("\\,"));
		//删除图片
		FileUtil.deleteFiles(StringUtils.join(willDeleteImages.toArray(), ","), super.propertiesService.getProperty("image.baseUrl"));
		
		goodsStandardVO.setGoodsImages(imagePaths.toString());

		//editer中temp图移动到正式目录
		final Map<String, String> editorContentMap = FileUtil.editorContentFilesUpload("goods", goodsStandardVO.getGoodsBrief(), super.propertiesService.getProperty("image.baseUrl"));
        //temp地址的文件移除转换正式地址
		goodsStandardVO.setGoodsBrief(editorContentMap.get("editorContent"));
        //已有文件中删除此次去掉的文件
		FileUtil.editorContentFileFilterDelete("goods", goodsStandard.getGoodsBrief(), super.propertiesService.getProperty("image.baseUrl"), editorContentMap.get("fileUrl"));
		
		return goodsStandardService.modifyGoodsStandard(goodsStandardVO, locale);
	}
}