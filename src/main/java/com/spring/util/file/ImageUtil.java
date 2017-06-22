package com.spring.util.file;

import java.util.Arrays;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public class ImageUtil {
	
	//可上传图片种类
	final public static List<String> IMAGE_FILE_CONTENT_TYPES = Arrays.asList("image/jpeg", "image/gif", "image/png");

	final public static boolean checkImageFiles(MultipartFile images[]) {
		
		for (MultipartFile image : images) {
			
			if (!ImageUtil.isImageFile(image)) {
				
				return false;
			}
		}
		
		return true;
	}
	
	final public static boolean isImageFile(MultipartFile image) {

		if (ImageUtil.IMAGE_FILE_CONTENT_TYPES.contains(image.getContentType())) {
			
			return true;
		}
		
		return false;
	}
}