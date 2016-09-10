package fastjson;

import com.alibaba.fastjson.JSON;
import com.lm.entity.UserEntity;
import org.junit.Test;
import classLoad.ClassLoadTest;

/**
 * Created with IntelliJ IDEA.
 * User: songzhimao
 * Date: 15-8-31
 * Time: 下午1:57
 * To change this template use File | Settings | File Templates.
 */
public class FastJsonTest {
    @Test
    public void fast(){
        ClassLoadTest ttt = new ClassLoadTest();
        UserEntity user = new UserEntity();
        user.setUserName("songzhimao");
        String str1 ="{\"name\":\"songzhimao\"}";
        String str3 ="{'name':'songzhimao'}";
        String str2 ="name";
        System.out.println(str1 = JSON.toJSONString(str1));
        System.out.println(str3 = JSON.toJSONString(str3));
        str1 = str1.substring(1);
        System.out.println(str1);
        System.out.println(str2 = JSON.toJSONString(str2));
        System.out.println(JSON.toJSONString(user));
    }
}
