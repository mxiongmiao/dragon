package com.maxm.dragon.test.utils;

import java.nio.charset.Charset;

import junit.framework.Assert;

import org.junit.Test;

import com.maxm.dragon.util.StringUtils;

public class TestStringUtils {

	@Test
	public void test() {
		String hello = "hello";
		String newStringUtf8 = StringUtils.newStringUtf8(hello.getBytes(Charset.forName("utf8")));
		Assert.assertEquals(hello, newStringUtf8);
	}

}
