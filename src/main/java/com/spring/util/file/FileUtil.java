package com.spring.util.file;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

	//默认上传文件物理路径
	final public static String DOCBASE = "D:/xampp/htdocs/java/upload/";
	//上传文件日期目录
	final public static String DATE_FOLDER_PATH = "/yyyy/MM/dd/";
	//上传文件日期目录
	//final public static int MAX_SIZE = 5242880;
	
	public static File makeEmptyFile(String URIPath) {
		
		File newFile = new File(FileUtil.DOCBASE + URIPath);
	    
	    if (!newFile.getParentFile().exists()) {
	    	
	    	newFile.getParentFile().mkdirs();
		}
	    
	    return newFile;
	}
	
	public static String makeFileUploadDateDir() {

		return new SimpleDateFormat(DATE_FOLDER_PATH).format(new Date());
	}
	
	public static void deleteFiles(String files, String fileURL) {
		
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
	
	public static void deleteFile(String filePath) {
		
		File file = new File(filePath);
		if (file.exists()) {
			
			file.delete();
		}
	}
	
	public static void deleteFile(String filePath, String fileURL) {
		
	}
	
	public static StringBuilder uploadFiles(String folderType, MultipartFile files[], String fileURL) throws Exception {
		
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
	
	public static StringBuilder uploadAndDeleteFiles(String folderType, MultipartFile files[], String[] uploadedFiles, String fileURL) throws Exception {
		
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
}