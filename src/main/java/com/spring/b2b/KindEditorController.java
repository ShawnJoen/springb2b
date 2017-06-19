package com.spring.b2b;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.spring.util.file.FileUtil;

@Controller
@RequestMapping("kindeditor")
public class KindEditorController extends BaseController {

	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)  
	@ResponseBody  
	public Map<String, Object> fileUpload(HttpServletRequest request, HttpServletResponse response, 
			MultipartFile imgFile[]) throws Exception {
	
		response.setHeader("X-Frame-Options", "SAMEORIGIN");
	 
		if (!ServletFileUpload.isMultipartContent(request)) {
		
			return getError("��ѡ���ļ���");
		}
	 
		String dirName = request.getParameter("dir");
		if (dirName == null) {
			   
			dirName = "image";
		}
		//���������ϴ����ļ���չ��  
		final Map<String, String> extMap = new HashMap<>();
		extMap.put("image", "gif,jpg,jpeg,png");//bmp
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,xml,txt,zip,rar,gz,bz2");
		if (!extMap.containsKey(dirName)) {
			   
			return getError("Ŀ¼������ȷ��");
		}
		
		final String[] fileExts = extMap.get(dirName).split(",");
		for (MultipartFile file : imgFile) {
			   
			String originalFilename = file.getOriginalFilename();
			if (!file.isEmpty()) {
					   
				String fileExt = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
				if (!Arrays.<String>asList(fileExts).contains(fileExt)) {
					   
					return getError("�ϴ��ļ���չ���ǲ��������չ����\nֻ����" + extMap.get(dirName) + "��ʽ��");  
				}  
			}
		}
		//�ϴ��ļ�
		final StringBuilder filePaths = FileUtil.uploadFiles("temp", imgFile, super.propertiesService.getProperty("image.baseUrl"));
		
		if (filePaths.length() == 0) {
		
			return getError("��ѡ���ļ���");
		}

		Map<String, Object> succMap = new HashMap<>();
		succMap.put("error", 0);
		succMap.put("url", filePaths.toString());
		return succMap;
	}
   
	private Map<String, Object> getError(String errorMsg) {
		Map<String, Object> errorMap = new HashMap<String, Object>();
		errorMap.put("error", 1);
		errorMap.put("message", errorMsg);
		return errorMap;
	}  
}