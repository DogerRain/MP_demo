import java.util.ArrayList;
        import java.util.List;

/**
 * @author huangyongwen
 * @date 2019/11/21
 * @Description
 */
public class ListTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("1");
        list.add("2");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

}
