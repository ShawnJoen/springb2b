package com.spring.util.file;

import static com.spring.util.Common.compareDifferentArray;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

	//默认上传文件物理路径
	final public static String DOCBASE = "D:/xampp/htdocs/java/upload/";
	//上传文件日期目录
	final public static String DATE_FOLDER_PATH = "/yyyy/MM/dd/";
	//上传文件日期目录
	//final public static int MAX_SIZE = 5242880;
	
	final public static File makeEmptyFile(String URIPath) {
		
		File newFile = new File(FileUtil.DOCBASE + URIPath);
	    
	    if (!newFile.getParentFile().exists()) {
	    	
	    	newFile.getParentFile().mkdirs();
		}
	    
	    return newFile;
	}
	
	final public static String makeFileUploadDateDir() {

		return new SimpleDateFormat(DATE_FOLDER_PATH).format(new Date());
	}
	
	final public static void deleteFiles(String files, String fileURL) {
		
		final String filePaths = files.replaceAll(fileURL, FileUtil.DOCBASE);
		Arrays.asList(filePaths.split("\\,"))
			.parallelStream()
			.forEach(filePath -> {
				/*File file = new File(filePath);
				if (file.exists()) {
					
					file.delete();
				}*/
				FileUtil.deleteFile(filePath);
			});
	}
	
	final public static void deleteFile(String filePath) {
		
		File file = new File(filePath);
		if (file.exists()) {
			
			file.delete();
		}
	}
	
	/*final public static void deleteFile(String filePath, String fileURL) {
		
	}*/
	
	final public static StringBuilder uploadFiles(String folderType, MultipartFile files[], String fileURL) throws Exception {
		
		final StringBuilder filePaths = new StringBuilder();
		for (MultipartFile file : files) {

			if (file != null) {

				String originalFilename = file.getOriginalFilename();
				if (!file.isEmpty()) {
		            //创建文件路径
		            String URIDir = folderType + FileUtil.makeFileUploadDateDir();
				    //设置图片名称
				    String newFileName = URIDir + UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
				    //创建空文件
				    File newFile = FileUtil.makeEmptyFile(newFileName);
				    //将内存中的数据写入磁盘
				    file.transferTo(newFile);
				    
				    if (filePaths.length() > 0) {
				    	filePaths.append(",");
				    }
				    
				    filePaths.append(fileURL + newFileName);
				}
			}
		}
		
		return filePaths;
	}
	
	final public static StringBuilder uploadAndDeleteFiles(String folderType, MultipartFile files[], String[] uploadedFiles, String fileURL) throws Exception {
		
		final StringBuilder filePaths = new StringBuilder();
		int index = 0;
		for (MultipartFile file : files) {

		    if (filePaths.length() > 0) {
		    	filePaths.append(",");
		    }
		    
			boolean isUploading = false;
			if (file != null) {

				String originalFilename = file.getOriginalFilename();
				if (!file.isEmpty()) {
					
					isUploading = true;
					//有存在图地址删除
					/*if (!"".equals(uploadedFiles[index])) {
						
						ImageUtil.deleteFiles(uploadedFiles[index], fileURL);
					}*/
		            //创建文件路径
		            String URIDir = folderType + FileUtil.makeFileUploadDateDir();
				    //设置图片名称
				    String newFileName = URIDir + UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
				    //创建空文件
				    File newFile = FileUtil.makeEmptyFile(newFileName);
				    //将内存中的数据写入磁盘
				    file.transferTo(newFile);
				    
				    filePaths.append(fileURL + newFileName);
				}
			}
			
			if (!isUploading && !"".equals(uploadedFiles[index])) {
				
				filePaths.append(uploadedFiles[index]);
			}
			
			index++;
		}
		
		return filePaths;
	}
	
	final public static Map<String, String> editorContentFilesUpload(final String folderType, String editorContent, final String fileURL) throws Exception {
		
		final Map<String, String> editorContentMap = new HashMap<>(); 
		//临时存储Url
		final String tempUrl = fileURL + "temp/";// (/upload/temp/)
		//新存储Url
		final String _folderType = folderType + "/";
		final String newUrl = fileURL + _folderType;// (/upload/config/)
		//临时存储Pattern
		final Pattern tempUrlPattern = Pattern.compile(tempUrl + "[^\"]+");
		//图片PathUrlPattern
		final Pattern newUrlPattern = Pattern.compile(newUrl + "[^\"]+");
		
		final StringBuffer filesUrlBuffer = new StringBuffer();
		
		if (editorContent.contains("/temp/")) {
			
			final Matcher matchers = tempUrlPattern.matcher(editorContent);  
	        while (matchers.find()) {  
	            //要新生成的文件Path
	            String newFilePath = matchers.group().replace(tempUrl, _folderType);// D:/xampp/htdocs/java/upload/config/2017/06/18/??.??
	            FileOutputStream fos = new FileOutputStream(FileUtil.makeEmptyFile(newFilePath));
	            //原文件Path
	            String oldFilePath = FileUtil.DOCBASE + matchers.group().replace(fileURL, "");// file::/xampp/htdocs/java/upload/temp/2017/06/18/??.??
	            FileSystemResource resource = new FileSystemResource(oldFilePath);
	            //resource 复制给 fos
	            FileCopyUtils.copy(resource.getInputStream(), fos);
	            //删除源文件
	            FileUtil.deleteFile(oldFilePath);
	            //要新生成的文件Url
	            String newFileUrl = matchers.group().replace(tempUrl, newUrl);
	            //改后 新生成的文件Path
	            editorContent = editorContent.replace(matchers.group(), newFileUrl);
	            
	            if (filesUrlBuffer.length() > 0) {
	            	
	            	filesUrlBuffer.append(",");
	            }
	            //此编辑器此次新上传的图Url(,)分割符存
	            filesUrlBuffer.append(newFileUrl);
	        }
		}
		
		final Matcher matchers = newUrlPattern.matcher(editorContent);  
        while (matchers.find()) {  
        	
        	if (filesUrlBuffer.length() > 0) {
            	
        		filesUrlBuffer.append(",");
            }
            //此编辑器之前已上传的图Url(,)分割符存
        	filesUrlBuffer.append(matchers.group());
        }
        
        editorContentMap.put("fileUrl", filesUrlBuffer.toString());
        editorContentMap.put("editorContent", editorContent);
        
		return editorContentMap;
	}
	/*
	 * 已有文件中删除此次去掉的文件
	 * */
	final public static void editorContentFileFilterDelete(final String folderType, String editorContent, final String fileURL, final String fileUrls) throws Exception {
		
		//新存储Url
		final String _folderType = folderType + "/";
		final String newUrl = fileURL + _folderType;// (/upload/config/)
		//图片PathUrlPattern
		final Pattern newUrlPattern = Pattern.compile(newUrl + "[^\"]+");
		
		final StringBuffer filesUrlBuffer = new StringBuffer();
		
		final Matcher matchers = newUrlPattern.matcher(editorContent);  
        while (matchers.find()) {  
            
            if (filesUrlBuffer.length() > 0) {
            	
            	filesUrlBuffer.append(",");
            }
            //此编辑器所有图Url(,)分割符存
            filesUrlBuffer.append(matchers.group());
        }
        //之前存过图时
		if (filesUrlBuffer.length() > 0) {
			
			/*String imageUrls = "";
			if (configValImages.containsKey(configCodes[index])) {
				
				imageUrls = configValImages.get(configCodes[index]);
			}*/
			//删除之前已上传中取消的图link
			List<String> willDeleteImages = compareDifferentArray(fileUrls.split("\\,"), filesUrlBuffer.toString().split("\\,"));
			//删除图片
			FileUtil.deleteFiles(StringUtils.join(willDeleteImages.toArray(), ","), fileURL);
		}
	}
}