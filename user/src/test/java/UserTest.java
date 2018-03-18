import com.larryluk.user.UserApplication;
import com.larryluk.user.bean.User;
import com.larryluk.user.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @auther larryluk
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
public class UserTest {
    Logger logger = LoggerFactory.getLogger(User.class);
    @Resource
    private UserDao userDao;

    @Test
    public void testUser() {
        User user = userDao.selectByPrimaryKey(1);
        logger.info(user.toString());
    }
}
