package functional;

import java.util.concurrent.TimeUnit;

/**
 * drools
 * Created by HenDiao on 2017/10/13.
 */
public class ElapsedExample {

    public static void main(String[] args) {
        try {
            process("4444");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * splunk  logstash
     * @param orderNo
     * @throws InterruptedException
     */
    private static  void process(String orderNo) throws InterruptedException {

        System.out.println("Start process orderNo = [" + orderNo + "]");

        long currentTime=System.nanoTime();
        TimeUnit.SECONDS.sleep(1);

        System.out.printf("The order %s process successfull and elapsed %s \n",orderNo,(System.nanoTime()-currentTime));
    }

}
