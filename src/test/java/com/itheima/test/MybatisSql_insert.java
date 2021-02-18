package com.itheima.test;

import Dao.IUserDao;
import com.chen.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisSql_insert {
    private InputStream in;
    private SqlSession session;
    private IUserDao userDao;
@Before//用于在测试方法之前执行
    public void init() throws IOException {
        //1.读取配置文件
        in = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2.创建一个sqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂生产一个sqlSession对象
        session = factory.openSession();
        //4.使用sqlSession创建Dao接口的代理对象
        userDao = session.getMapper(IUserDao.class);
    }
@After//用于在测试方法之后执行
    public void destory() throws IOException {
        //6.释放资源
        session.close();
        in.close();
    }
    @Test
    public void findAll() throws Exception {

        //5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }

    }
    @Test
    public void saveUser() throws Exception {
      User user=new User();
      user.setUsername("陈庆旭01");
      user.setAddress("背景是河南");
      user.setBirthday(new Date());
      user.setSex("男");
      //5.使用代理对象执行方法
        userDao.saveUser(user);
        //事务提交
        session.commit();
    }
    @Test
    public void upDateUser() throws Exception {
        User user=new User();
        user.setId(41);
        user.setUsername("陈庆旭01");
        user.setAddress("背景是河南");
        user.setBirthday(new Date());
        user.setSex("男");
        //5.使用代理对象执行方法
        userDao.upDateUser(user);
        session.commit();
    }
    @Test
    public void deleteUser() throws Exception {
        //5.使用代理对象执行方法
        userDao.deleteUser(43);
        session.commit();
    }
}
