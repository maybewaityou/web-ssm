package com.meepwn.test;

import com.meepwn.ssm.common.manager.RedisManager;
import com.meepwn.ssm.common.util.LogUtils;
import com.meepwn.ssm.enhance.factory.proxy.BeanProxyFactory;
import com.meepwn.ssm.entity.po.User;
import com.meepwn.ssm.service.UserService;
import com.meepwn.test.proxy.DaoTest;
import com.meepwn.test.proxy.UserDaoTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class TestMain {

    @Resource(name = "user")
    private User user;
    @Resource(name = "userService")
    private UserService userService;
    @Resource
    private RedisManager redisUtils;

    @Test
    public void testUser() {
        Logger logger = LoggerFactory.getLogger(TestMain.class);
        logger.debug("user: {}", user);
    }

    @Test
    public void testProxy() throws InstantiationException, IllegalAccessException {
        DaoTest daoTest = (DaoTest) BeanProxyFactory.newInstance(UserDaoTest.class, "proxy.UserDaoInterceptor");
        daoTest.save();
    }

    @Test
    public void testInsertUser() {
//        user.setAge(18);
        user.setUserName("赵六");
        userService.insertUser(user);
    }

    @Test
    public void testDeleteUser() {
        userService.deleteUser(4);
    }

    @Test
    public void testUpdateUser() {
        user.setId(1);
        user.setUserName("张三");
//        user.setAge(19);
        userService.updateUser(user);
    }

    @Test
    public void testGetUser() {
        User user = userService.getUser(3);
        System.out.println(user);
    }

    @Test
    public void testFindUsers() {
        List<User> users = userService.findUsers("张三");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindAllUsers() {
        List<User> users = userService.findAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testLog() {
        LogUtils.d("=====");
    }

    @Test
    public void testRedis() {
        redisUtils.set("name", "张三");
        System.out.println(redisUtils.get("name"));
    }

}
