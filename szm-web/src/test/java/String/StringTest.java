package String;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: songzhimao
 * Date: 15-8-31
 * Time: 下午3:22
 * To change this template use File | Settings | File Templates.
 */
public class StringTest {
    @Test
    public void testSplit(){
        String temp ="1/206/207:7";
        String[] tmpPerCids = temp.split("\\|");
        System.out.println("---------------");
    }
}
