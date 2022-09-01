package mao.t5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Project name(项目名称)：java并发编程_wait_notify
 * Package(包名): mao.t5
 * Class(类名): Test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/1
 * Time(创建时间)： 20:43
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
                if (!hasCigarette)
                {
                    log.debug("没烟，先歇会！");
                    try
                    {
                        room.wait(2000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                log.debug("有烟没？[{}]", hasCigarette);
                if (hasCigarette)
                {
                    log.debug("可以开始干活了");
                }
            }
        }, "小南").start();

        for (int i = 0; i < 5; i++)
        {
            new Thread(() ->
            {
                synchronized (room)
                {
                    log.debug("可以开始干活了");
                }
            }, "其它人").start();
        }


        Thread.sleep(1000);

        new Thread(() ->
        {
            synchronized (room)
            {
                hasCigarette = true;
                log.debug("烟到了噢！");
                room.notify();
            }
        }, "送烟的").start();

    }
}
