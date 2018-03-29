package com.cnkeep.zhiyun.web.common.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FileUtil {
	public static void  writeFile(File file,byte[] data) throws IOException{
		FileUtils.writeByteArrayToFile(file, data);
	}
	
}
