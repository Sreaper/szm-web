package com.fastjson;

import com.BaseModel;
import com.alibaba.fastjson.JSON;
import com.szm.demo.reflect.MethodDemo;
import org.junit.Test;

/**
 * Created by szm on 2018/1/20.
 */
public class FastJsonTest {
	@Test
	public void testFastJson() {
		JSON.parseArray("[{\"id\":111,\"name\":\"szm\"}]", BaseModel.class);
	}
}
