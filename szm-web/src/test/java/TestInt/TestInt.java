package TestInt;


import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: songzhimao
 * Date: 15-9-2
 * Time: 下午4:15
 * To change this template use File | Settings | File Templates.
 */
public class TestInt {
    /**
     * 取最小的比n大的2的幂
     */
    @Test
    public void test1(){
        int num =10;
//        if((num&(num-1)) ==0 ) {
//             System.out.println(num);
//             return 1;
//        }
//        int c=0;
//        while(num>0)
//        {
//            c++;
//            num>>=1;
//        }
//        int temp = 1;
//        temp <<= c;
//        temp -= 1;
//        num |=temp;
//        num++;
        //取的长度
        //获得
       System.out.println(num);
       String bs = Integer.toBinaryString(num) ;
       int temp =  (bs.lastIndexOf("1") ==0) ? 1<<bs.length()-1:  1<<bs.length();
        System.out.println(temp);
        //return temp;
    }
}
