import java.lang.reflect.Method;

/**
 * Project name(项目名称)：java并发编程_wait_notify
 * Package(包名): PACKAGE_NAME
 * Class(类名): test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/1
 * Time(创建时间)： 21:06
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class test
{
    public static void main(String[] args)
    {
        int i = 1;
        while (true)
        {
            try
            {
                Class<?> aClass = Class.forName("mao.t" + i + ".Test");
                for (Method method : aClass.getDeclaredMethods())
                {
                    if (method.getName().equals("main"))
                    {
                        System.out.println("---------t" + i + "-------------");
                        method.invoke(null, (Object) args);
                        System.out.println("--------------------------------");
                        System.out.println();
                    }
                }
                i++;
            }
            catch (Exception e)
            {
                //e.printStackTrace();
                break;
            }
        }
    }
}
