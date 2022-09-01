package mao.t8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Project name(项目名称)：java并发编程_wait_notify
 * Package(包名): mao.t8
 * Class(类名): Test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/1
 * Time(创建时间)： 20:56
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Test
{
    /**
     * 房间
     */
    private static final Object room = new Object();

    /**
     * 是否有香烟
     */
    private static boolean hasCigarette = false;

    /**
     * 是否有外卖
     */
    private static boolean hasTakeout = false;

    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(Test.class);


    public static void main(String[] args) throws InterruptedException
    {
        new Thread(() ->
        {
            synchronized (room)
            {
                log.debug("有烟没？[{}]", hasCigarette);
                while (!hasCigarette)
                {
                    log.debug("没烟，先歇会！");
                    try
                    {
                        room.wait();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    log.debug("有烟没？[{}]", hasCigarette);
                }
                if (hasCigarette)
                {
                    log.debug("可以开始干活了");
                }
                else
                {
                    log.debug("没干成活...");
                }
            }
        }, "小南").start();

        new Thread(() ->
        {
            synchronized (room)
            {
                log.debug("外卖送到没？[{}]", hasTakeout);
                while (!hasTakeout)
                {
                    log.debug("没外卖，先歇会！");
                    try
                    {
                        room.wait();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    log.debug("外卖送到没？[{}]", hasTakeout);
                }
                if (hasTakeout)
                {
                    log.debug("可以开始干活了");
                }
                else
                {
                    log.debug("没干成活...");
                }
            }
        }, "小女").start();

        Thread.sleep(1000);

        new Thread(() ->
        {
            synchronized (room)
            {
                hasTakeout = true;
                log.debug("外卖到了噢！");
                room.notifyAll();
            }
        }, "送外卖的").start();

        Thread.sleep(2000);

        new Thread(() ->
        {
            synchronized (room)
            {
                hasCigarette = true;
                log.debug("烟到了噢！");
                room.notifyAll();
            }
        }, "送烟的").start();
    }
}
