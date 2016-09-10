package com.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by songzhimao on 15-12-15.
 */
public class ReflectType {
//    public <T extends BaseModel> T updateSign(T model) {
//        if (model == null) {
//            return null;
//        }
//        model.setUpdateTime(new Date());
//        model.setUpdateBy(session.getUserId());
//        return model;
//    }
//
//    public <T extends BaseModel> List<T> updateSign(List<T> models) {
//        if (models == null || models.isEmpty()) {
//            return null;
//        }
//
//        for (T t : models) {
//            updateSign(t);
//        }
//        return models;
//    }
//
//    public  <T extends BaseModel> T addSign(T model) {
//        if (model == null) {
//            return null;
//        }
//        model.setCreateTime(new Date());
//        model.setUpdateTime(new Date());
//        model.setCreateBy(session.getUserId());
//        model.setUpdateBy(session.getUserId());
//        return model;
//    }
//
//    public <T extends BaseModel> List<T> addSign(List<T> models) {
//        if (models == null || models.isEmpty()) {
//            return null;
//        }
//
//        for (T t : models) {
//            addSign(t);
//        }
//        return models;
//    }
//
//    /**
//     * 判断一个类型是不是原始类型，包装类或string
//     *
//     * @param clazz
//     * @return
//     */
//    public boolean isPrimitiveOrStr(Class<?> clazz) {
//        String[] types = {"java.lang.Integer",
//                "java.lang.Double",
//                "java.lang.Float",
//                "java.lang.Long",
//                "java.lang.Short",
//                "java.lang.Byte",
//                "java.lang.Boolean",
//                "java.lang.Char",
//                "java.lang.String",
//                "java.util.Date",
//                "int", "double", "long", "short", "byte", "boolean", "char", "float"};
//        for (String type : types) {
//            if (clazz.getName().equals(type)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    protected <T extends BaseModel> T addSignRecurse(T model,Executor1 executor) throws Exception {
//        Class c = model.getClass();
//        Field[] fields = c.getDeclaredFields();
//        for (Field field : fields) {
//            if (executor.filter(field.getClass())) {
//                continue;
//            }
//            handler(model,field,executor);
//        }
//        return model;
//    }
//
//    protected <T extends BaseModel> void handler(T model, Field field,Executor1 executor) throws Exception {
//        Class c = model.getClass();
//        String methodName = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
//        Method method = c.getMethod("get" + methodName);
//        Object object = method.invoke(model);
//        if (object == null) {
//            return;
//        } else {
//            if (Collection.class.isAssignableFrom(object.getClass())) {
//                Collection collection = (Collection) object;
//                Iterator iterator = collection.iterator();
//                if (iterator.hasNext()) {
//                    executor.execute(iterator.next());
//                }
//            } else if (object.getClass().isArray()) {
//                Object[] objects = (Object[]) object;
//                for (Object o : objects) {
//                    executor.execute(o);
//                }
//            } else if (object.getClass().isEnum()) {
//                return;
//            } else {
//                executor.execute(object);
//            }
//        }
//    }
//    class  DefaultExecutor implements Executor1<BaseModel> {
//        @Override
//        public boolean filter(Class c) {
//            if (isPrimitiveOrStr(c)) {
//                return true;
//            }
//            return false;
//        }
//
//        @Override
//        public void execute(BaseModel baseModel) {
//            addSign(baseModel);
//        }
//    }
//
//    interface Executor1<M>{
//        boolean filter(Class<?> c);
//        void execute(M m);
//    }
}
