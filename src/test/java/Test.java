import java.util.Date;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/12/9
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        long time = new Date().getTime();
        Thread.sleep(3000);
        long time1 = new Date().getTime();
        System.out.println((int)(time - time1));
    }
}
