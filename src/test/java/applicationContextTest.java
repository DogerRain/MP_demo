//import com.meizu.service.HomeService;
//import com.meizu.service.impl.HomeServiceImpl;
//import com.meizu.service.impl.TestServiceImpl;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import javax.annotation.Resource;
//
///**
// * @author huangyongwen
// * @date 2019/11/21
// * @Description
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:spring/application-context.xml")
//public class applicationContextTest {
//
//    @Autowired
//    HomeServiceImpl homeServiceImpl;
//
//    @Resource(name = "testServiceImpl")
//    TestServiceImpl testServiceImpl;
//
//    @Test
//    public void tets(){
//        homeServiceImpl.testIfPrintLog(new HomeService() {
//            @Override
//            public String testLog() {
//
//                System.out.println("!!");
//                return null;
//            }
//        });
//
//
//        testServiceImpl.test();
//
//    }
//
//}
