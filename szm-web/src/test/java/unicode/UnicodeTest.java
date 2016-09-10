package unicode;

import org.junit.Test;
import sun.nio.cs.ext.GBK;

import java.nio.charset.Charset;

/**
 * Created with IntelliJ IDEA.
 * User: songzhimao
 * Date: 15-8-20
 * Time: ионГ9:38
 * To change this template use File | Settings | File Templates.
 */
public class UnicodeTest {
    @Test
    public void unicodeTest() throws Exception{
        String tem ="ру";
        char tem1 ='1';
        System.out.println(Integer.toHexString((int)tem1));
        System.out.println(Charset.defaultCharset().name());
        for(int i=0;i<tem.length();i++){
            tem = "\\u"+Integer.toHexString((int)tem.charAt(i));
            System.out.println("----"+tem);
        }
        System.out.println(new String(tem.getBytes("GBK")));
    }
}
