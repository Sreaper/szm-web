package com.szm.demo.reflect;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 查看一个方法参数的泛型
 * Created by songzhimao on 16-1-22.
 */
public class MethodDemo {
    public static void main(String[] args) throws NoSuchMethodException {
        System.out.println(111);
        Method m = MethodDemo.class.getDeclaredMethod("testParamType", new Class[]{List.class});
        Type[] types = m.getGenericParameterTypes();
        System.out.println(111);
    }
    public void testParamType(List<BaseModel>  paramWrapper){

    }
    class ParamWrapper<M>{
        List<M> models;

        public List<M> getModels() {
            return models;
        }

        public void setModels(List<M> models) {
            this.models = models;
        }
    }
    class BaseModel implements Serializable{
        private long id;
        private String name;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
