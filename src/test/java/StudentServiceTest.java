import com.bdqn.huanxun.pojo.Student;
import com.bdqn.huanxun.service.StudentService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 佳 on 2018/1/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class StudentServiceTest {
    @Test
    public void queryStudentByNameAndAgencyAndAge() throws Exception {
        PageInfo<Student> pageInfo = studentService.queryStudentByNameAndAgencyAndAge(1, 3, "", "",10, null);
        if(pageInfo != null){
            List<Student> list = pageInfo.getList();
            for (Student student : list) {
                System.out.println(student);
            }
        }
    }

    @Resource
    private StudentService studentService;
    @Test
    public void queryStudent() throws Exception {
        PageInfo<Student> pageInfo = studentService.queryStudent(1, 2);
        if (pageInfo != null) {
            List<Student> list = pageInfo.getList();
            for (Student student : list) {
                System.out.println(student);
            }
        }
    }

}