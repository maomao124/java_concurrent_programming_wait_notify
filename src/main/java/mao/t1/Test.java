package mao.t1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Project name(项目名称)：java并发编程_wait_notify
 * Package(包名): mao.t1
 * Class(类名): Test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/1
 * Time(创建时间)： 20:18
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Test
{
    /**
     * 锁
     */
    private final static Object lock = new Object();

    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(Test.class);


    public static void main(String[] args) throws InterruptedException
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                synchronized (lock)
                {
                    log.debug("执行t1....");
                    try
                    {
                        //等待
                        lock.wait();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    log.debug("t1被唤醒....");
                }
            }
        }, "t1").start();

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                synchronized (lock)
                {
                    log.debug("执行t2....");
                    try
                    {
                        lock.wait();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    log.debug("t2被唤醒....");
                }
            }
        }, "t2").start();


        Thread.sleep(300);
        log.debug("唤醒 obj 上其它线程");
        synchronized (lock)
        {
            //唤醒
            lock.notify();
        }
    }
}
