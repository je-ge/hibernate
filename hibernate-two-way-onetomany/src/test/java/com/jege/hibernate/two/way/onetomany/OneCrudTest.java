package com.jege.hibernate.two.way.onetomany;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import com.jege.hibernate.two.way.onetomany.Dept;
import com.jege.hibernate.two.way.onetomany.User;
import com.jege.hibernate.util.HibernateUtils;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:单向多对一的处理
 */
public class OneCrudTest {
  // 单个保存:一次性保存1个部门，保存3个员工
  // 在单向多对一保存的时候需要先保存一方，不是会出现多余的update语句，影响性能
  @Before
  public void before() throws Exception {
    Dept dept = new Dept();
    dept.setName("部门1");

    // 传入dept的本质是处理数据库user表的dept_id外键
    User user1 = new User("员工1", dept);
    User user2 = new User("员工2", dept);

    Session session = HibernateUtils.INSTANCE.getSession();
    session.beginTransaction();

    System.out.println("保存之前：" + dept);
    session.save(dept);// Hibernate会自动把保存后的主键放到当前对象的id里面
    System.out.println("保存之后：" + dept);
    session.save(user1);
    session.save(user2);

    session.getTransaction().commit();
    session.close();
  }

  @Test
  public void save() throws Exception {

  }
  
  @Test
  public void update() throws Exception {
    Session session = HibernateUtils.INSTANCE.getSession();
    User user = (User) session.get(User.class, 1L);
    user.setUsername("jege");
    
    session.beginTransaction();
    session.update(user);
    session.getTransaction().commit();
    session.close();
  }
  
  @Test
  public void delete() throws Exception {
    Session session = HibernateUtils.INSTANCE.getSession();
    User user = (User) session.get(User.class, 1L);
    
    session.beginTransaction();
    session.delete(user);
    session.getTransaction().commit();
    session.close();
  }

  @Test
  public void get() throws Exception {
    Session session = HibernateUtils.INSTANCE.getSession();
    User user = (User) session.get(User.class, 1L);
    System.out.println(user);
    System.out.println(user.getDept());
  }

}
