package classLoad;

/**
 * Created with IntelliJ IDEA.
 * User: songzhimao
 * Date: 15-9-11
 * Time: 上午10:20
 * To change this template use File | Settings | File Templates.
 */
public class ClassLoadTest {
    static {
        System.out.println(" it already is loaded ");
    }

    public ClassLoadTest() {
        System.out.println("ClassLoadTest ");
    }

    public static void main(String args[]){

    }
}
