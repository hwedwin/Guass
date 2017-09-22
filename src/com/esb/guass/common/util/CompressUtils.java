package com.esb.guass.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class CompressUtils {
	
	 public static void compressFileByGZ(String inFileName) {
	        String outFileName = inFileName + ".gz";
	        FileInputStream in = null;
	        try {
	            in = new FileInputStream(new File(inFileName));
	        }catch (FileNotFoundException e) {
	        	LogUtils.error("文件没有找到", e);
	        }
	        
	        GZIPOutputStream out = null;
	        try {
	            out = new GZIPOutputStream(new FileOutputStream(outFileName));
	        }catch (IOException e) {
	        	LogUtils.error("文件无法生成", e);
	        }
	        byte[] buf = new byte[16 * 1024];
	        int len = 0;
	        try {
	            while ((len = in.read()) > 0) {
	                out.write(buf, 0, len);
	            }
	            in.close();
	            out.flush();
	            out.close();
	        }catch (IOException e) {
	        	LogUtils.error("文件压缩失败，"+inFileName, e);
	        }
	    }
}