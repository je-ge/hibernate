package com.jege.hibernate.single.table;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import com.jege.hibernate.util.HibernateUtils;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description: crud测试类
 */
public class CrudTest {

  // 在每个@Test之前自动执行，先保存数据
  @Before
  public void before() throws Exception {
    User user = new User();
    user.setUsername("jege");
    user.setPassword("1272434821");

    Session session = HibernateUtils.INSTANCE.getSession();
    session.beginTransaction();
    session.save(user);
    session.getTransaction().commit();
    session.close();
  }

  @Test
  public void save() throws Exception {

  }

  @Test
  public void update() throws Exception {
    Session session = HibernateUtils.INSTANCE.getSession();
    session.beginTransaction();

    User user = (User) session.get(User.class, 1L);
    user.setUsername("JavaEEGe");
    user.setPassword("1272434821@qq.com");

    session.update(user);
    session.getTransaction().commit();
    session.close();
  }

  @Test
  public void delete() throws Exception {

    Session session = HibernateUtils.INSTANCE.getSession();
    session.beginTransaction();

    User user = (User) session.get(User.class, 1L);
    if (user != null) {
      session.delete(user);
    }

    session.getTransaction().commit();
    session.close();
  }

  @Test
  public void get() throws Exception {
    Session session = HibernateUtils.INSTANCE.getSession();
    User user = (User) session.get(User.class, 1L);
    System.out.println(user);
    session.close();
  }

  @Test
  public void getAll() throws Exception {
    Session session = HibernateUtils.INSTANCE.getSession();
    Query query = session.createQuery("select u from User u");
    List<User> list = query.list();
    for (User user : list) {
      System.out.println(user);
    }
    session.close();
  }

}
