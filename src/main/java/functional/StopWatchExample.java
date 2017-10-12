package functional;


import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.concurrent.TimeUnit;

/**
 * Created by HenDiao on 2017/10/13.
 */
public class StopWatchExample {

    private  final  static Logger logger=  LoggerFactory.getLogger(StopWatchExample.class);
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

        logger.info("Start process orderNo [{}]",orderNo);
        Stopwatch stopwatch=Stopwatch.createStarted();
        //long currentTime=System.nanoTime();
        TimeUnit.MILLISECONDS.sleep(100);

        logger.info("The order [{}] process successfull and elapsed [{}] \n",orderNo,stopwatch.stop());
    }
}
