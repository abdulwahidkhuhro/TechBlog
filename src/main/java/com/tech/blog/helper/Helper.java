package com.tech.blog.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class Helper {

	public static boolean deleteFile(String path) {
		
		boolean isDeleted = false;
		
		try{
			File file = new File(path);
			isDeleted = file.delete();
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		return isDeleted;
	}
	
	public static boolean saveFile(InputStream inputStream, String path) {
		
		boolean isSaved = false;
		
		try {
			byte[] bytes = new byte[inputStream.available()];
			
			inputStream.read(bytes);
			
			FileOutputStream fos = new FileOutputStream(path);
			
			fos.write(bytes);
			
			fos.flush();
			
			fos.close();
			
			isSaved = true;
			
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		return isSaved;
	}
}
