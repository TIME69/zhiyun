package com.cnkeep.zhiyun.test;

import java.io.InputStream;

import org.junit.Test;

public class InputStreamTest {
	@Test
	public void classLoaderAsInputStreamTest(){
		InputStream stream = InputStreamTest.class.getClassLoader().getResourceAsStream("banner.txt");
		System.out.println(stream);
	}
}
