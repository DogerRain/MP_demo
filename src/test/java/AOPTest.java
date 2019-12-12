import com.meizu.AOP.HelloWorld;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author huangyongwen
 * @date 2019/12/12
 * @Description
 */
public class AOPTest {
    @Test
    public void test(){
        ClassPathXmlApplicationContext  applicationContext = new ClassPathXmlApplicationContext("spring/application-context.xml");
        HelloWorld helloWorld =applicationContext.getBean(HelloWorld.class);
        helloWorld.sayHello();
    }
}
