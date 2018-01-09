import com.bdqn.huanxun.dao.LessonTypeMapper;
import com.bdqn.huanxun.pojo.LessonType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lenovo on 2018/1/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class LessonTypeServiceTest {
    @Test
    public void queryAddLessonType() throws Exception {
        LessonType lessonType = new LessonType();
        lessonType.setTimePerLesson(45);
        lessonType.setLessonPrice(250);
        lessonType.setLessonArea("新东方英语");
        lessonType.setLessonDesc("新东方英语45分钟");
        int n = lessonTypeMapper.queryAddLessonType(lessonType);
        System.out.println(n);
    }

    @Test
    public void updateLessonDescAndLessonPriceAndTimePerLessonById() throws Exception {
        LessonType lessonType = new LessonType();
        lessonType.setLessonPrice(2500);
        lessonType.setLessonTypeID(1);
        int n = lessonTypeMapper.updateLessonDescAndLessonPriceAndTimePerLessonById(lessonType);
        System.out.println(n);
    }

    @Test
    public void queryAllLessonTypeBylessonArea() throws Exception {
        LessonType lessonType = new LessonType();
    /*    lessonType.setLessonArea("欧美");*/
        lessonType.setLessonPrice(350);
        List<LessonType> list = lessonTypeMapper.queryAllLessonTypeByLessonType(lessonType);
        for (LessonType type : list) {
            System.out.println(type);
        }
    }

    @Resource
    private LessonTypeMapper lessonTypeMapper;
    @Test
    public void queryAllLessonType() throws Exception {
        List<LessonType> list = lessonTypeMapper.queryAllLessonType();
        for (LessonType type : list) {
            System.out.println(type);
        }
    }

}