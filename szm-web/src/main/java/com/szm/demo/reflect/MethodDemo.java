package com.szm.demo.reflect;

import com.BaseModel;
import com.alibaba.fastjson.JSON;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 查看一个方法参数的泛型
 * Created by songzhimao on 16-1-22.
 */
public class MethodDemo {
	public static void main(String[] args) throws NoSuchMethodException {
		System.out.println(111);
		Method m = MethodDemo.class.getDeclaredMethod("getBaseModel", new Class[]{});
		Type[] types = m.getGenericParameterTypes();
		Type type = m.getGenericReturnType();
		Class ss = (Class) ((ParameterizedTypeImpl) type).getActualTypeArguments()[0];
		System.out.println(111);
		MethodDemo m1 = new MethodDemo();
		Object o = null;
		try {
			 o = m.invoke(m1, null);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		String text = JSON.toJSONString(o);
		System.out.println(text);
		List objects = JSON.parseArray(text, ss);
//		List objects1 = JSON.parseArray(text, List.getClass());
		System.out.println(111);
	}

	public void testParamType(List<BaseModel> paramWrapper) {

	}

	class ParamWrapper<M> {
		List<M> models;

		public List<M> getModels() {
			return models;
		}

		public void setModels(List<M> models) {
			this.models = models;
		}
	}

	public List<BaseModel> getBaseModel() {

		List<BaseModel> list = new ArrayList<BaseModel>();
		BaseModel baseModel = new BaseModel();
		baseModel.setId(111);
		baseModel.setName("szm");
		list.add(baseModel);
		return list;
	}


}
