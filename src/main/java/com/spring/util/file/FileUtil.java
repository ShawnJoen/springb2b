package com.spring.util.file;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

	//Ĭ���ϴ��ļ�����·��
	final public static String DOCBASE = "D:/xampp/htdocs/java/upload/";
	//�ϴ��ļ�����Ŀ¼
	final public static String DATE_FOLDER_PATH = "/yyyy/MM/dd/";
	//�ϴ��ļ�����Ŀ¼
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
		            //�����ļ�·��
		            String URIDir = folderType + FileUtil.makeFileUploadDateDir();
				    //����ͼƬ����
				    String newFileName = URIDir + UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
				    //�������ļ�
				    File newFile = FileUtil.makeEmptyFile(newFileName);
				    //���ڴ��е�����д�����
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
					//�д���ͼ��ַɾ��
					/*if (!"".equals(uploadedFiles[index])) {
						
						ImageUtil.deleteFiles(uploadedFiles[index], fileURL);
					}*/
		            //�����ļ�·��
		            String URIDir = folderType + FileUtil.makeFileUploadDateDir();
				    //����ͼƬ����
				    String newFileName = URIDir + UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
				    //�������ļ�
				    File newFile = FileUtil.makeEmptyFile(newFileName);
				    //���ڴ��е�����д�����
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