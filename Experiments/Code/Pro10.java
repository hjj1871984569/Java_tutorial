import java.io.*;
import java.util.*;
import java.util.Map.Entry;

import org.apache.commons.codec.digest.DigestUtils;

public class Pro10{
	static HashMap<String, ArrayList<String>> filesMD5= new HashMap<String, ArrayList<String>>();
	public static void main(String[] argv) {
		getFolderMethod("test");
        Set<String> keys= filesMD5.keySet();
        for(String key: keys) {
        	System.out.println(key + ":");
        	
        	ArrayList<String> filesArr = filesMD5.get(key);
        	
        	for(int i = 0; i < filesArr.size(); ++i) 
        	for(int j = i + 1; j < filesArr.size(); ++j) {
        		String fileName1 = filesArr.get(i);
        		String fileName2 = filesArr.get(j);
        		if(isSameFile(fileName1, fileName2))
        			System.out.println(fileName1 + " " + fileName2 +"相同'");
            }
        }
	}
	public static void getFolderMethod(String path) {
		File file = new File(path);
		if (file.exists()) {
			File[] files = file.listFiles();
			if (files != null) {
				for (File file2 : files) {
					if (file2.isDirectory()) {
						System.out.println("文件夹:" + file2.getAbsolutePath());
						getFolderMethod(file2.getAbsolutePath());
					} else {
						try{
							System.out.println("文件:" + file2.getAbsolutePath());	
							String md5 = DigestUtils.md5Hex(new FileInputStream(file2.getAbsolutePath()));
							if(filesMD5.containsKey(md5) == false) 
								filesMD5.put(md5, new ArrayList<String>());
							ArrayList<String> arr = filesMD5.get(md5);
							arr.add(file2.getAbsolutePath());
						}catch(IOException e){
							System.out.println("IO is Error:" + e.getMessage());
						}
					}
				}
			}
		} else {
			System.out.println("文件不存在!");
		}
	}
	public  static boolean isSameFile(String fileName1,String fileName2){  
		FileInputStream fis1 = null;  
		FileInputStream fis2 = null;  
		try {  
			fis1 = new FileInputStream(fileName1);
			fis2 = new FileInputStream(fileName2);  
			
			int len1 = fis1.available();
			int len2 = fis2.available();  
			if (len1 == len2) {
				byte[] data1 = new byte[len1];  
				byte[] data2 = new byte[len2];
				
				fis1.read(data1);  
				fis2.read(data2);  
				
				for (int i=0; i<len1; i++)
					if (data1[i] != data2[i])
	                    return false;
				return true;  
			} else {  
				return false;  
			}  
		} catch (FileNotFoundException e) {  
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();  
		} finally {
			if (fis1 != null) {  
				try {
					fis1.close();  
				} catch (IOException e) {  
					e.printStackTrace();  
				}  
			}  
			if (fis2 != null) {  
				try {
					fis2.close();
				} catch (IOException e) {
					e.printStackTrace();
				}  
			}  
		}  
		return false;  
	}  
}
