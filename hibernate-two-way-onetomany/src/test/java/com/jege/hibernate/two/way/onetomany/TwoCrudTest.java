package com.jege.hibernate.two.way.onetomany;

import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import com.jege.hibernate.util.HibernateUtils;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:双向多对一的处理
 */
public class TwoCrudTest {
  // 单个保存:一次性保存1个部门，保存3个jege
  // 在单向多对一保存的时候需要先保存一方，不是会出现多余的update语句，影响性能
  @Before
  public void before() throws Exception {
    Dept dept = new Dept();
    dept.setName("jege部门");

    // 传入dept的本质是处理数据库user表的dept_id外键
    User user1 = new User("jegea", dept);
    User user2 = new User("jegeb", dept);

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

  // 级联保存：一行save方法保存3条数据(必须建立2方的关系)
  // 修改Dept.hbm.xml
  // <set name="users" inverse="true" cascade="save-update">
  // 一次性保存：一个部门，在保存这个部门下面的2个jege
  @Test
  public void save() throws Exception {
    Dept dept = new Dept();
    dept.setName("jege部门");

    User user1 = new User("jege1");
    User user2 = new User("jege2");

    // 建立多方到一方的关系
    user1.setDept(dept);
    user2.setDept(dept);

    // 建立一方到多方的关系
    dept.getUsers().add(user1);
    dept.getUsers().add(user2);

    Session session = HibernateUtils.INSTANCE.getSession();
    session.beginTransaction();

    session.save(dept);// 持久化状态

    session.getTransaction().commit();
    session.close();
  }

  // 级联删除：把一方和多方同时删除:会出现3条delete语句
  // 修改Dept.hbm.xml<set name="users" inverse="true" cascade="all">
  @Test
  public void delete() throws Exception {
    Session session = HibernateUtils.INSTANCE.getSession();
    session.beginTransaction();
    session.delete(session.get(Dept.class, 1L));
    session.getTransaction().commit();
    session.close();
  }

  // 只删除一方，不希望删除一方包含的多方(可以先把多方的外键设置为null)
  // 方式1:修改Dept.hbm.xml<set name="users" inverse="true">
  @Test
  public void delete2() throws Exception {
    Session session = HibernateUtils.INSTANCE.getSession();
    session.beginTransaction();
    Dept dept = (Dept) session.get(Dept.class, 1L);// 持久化状态
    Set<User> users = dept.getUsers();// 持久化状态
    for (User user : users) {
      user.setDept(null);// 出现脏数据
    }
    session.delete(dept);
    session.getTransaction().commit();// 有事务，自动更新脏数据，发出update语句
    session.close();
  }

  // <set name="users" inverse="true">
  // 方式2:写类似dml的hql来删除
  @Test
  public void delete3() throws Exception {
    Long deptLong = 1L;
    Session session = HibernateUtils.INSTANCE.getSession();
    session.beginTransaction();

    // 1.先把多方的外键set null
    String hql = "update User set dept=null where dept.id=?";
    Query query = session.createQuery(hql);
    query.setLong(0, deptLong);
    System.out.println("受影响的行数：" + query.executeUpdate());

    // 2.删除一方
    hql = "delete from Dept where id=?";
    query = session.createQuery(hql);
    query.setLong(0, deptLong);
    System.out.println("受影响的行数：" + query.executeUpdate());

    session.getTransaction().commit();
    session.close();
  }

  // 方式1：在多方删除：先获取多方，然后删除多方
  @Test
  public void delete4() throws Exception {
    Session session = HibernateUtils.INSTANCE.getSession();
    session.beginTransaction();
    User user = (User) session.get(User.class, 1L);// 持久化状态
    session.delete(user);
    session.getTransaction().commit();
    session.close();
  }

  // 方式2：在一方删除多方：获取一方，通过一方来删除一条多方
  // 修改Dept.hbm.xml,实现级联删除解除关系的orphan儿子
  // <set name="users" inverse="true" cascade="delete-orphan">
  @Test
  public void delete5() throws Exception {
    Session session = HibernateUtils.INSTANCE.getSession();
    session.beginTransaction();
    Dept dept = (Dept) session.get(Dept.class, 1L);// 持久化状态
    User user = (User) session.get(User.class, 2L);// 持久化状态
    // 持久化状态dept.getUsers()
    dept.getUsers().remove(user);
    session.getTransaction().commit();// 有事务，自动脏数据更新
    session.close();
  }

}
