package com.jege.hibernate.two.way.manytomany;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import com.jege.hibernate.two.way.manytomany.Role;
import com.jege.hibernate.two.way.manytomany.User;
import com.jege.hibernate.util.HibernateUtils;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:多对多的处理
 */
public class CrudTest {

  // 保存2个用户,保存3个角色(会出现5条insert语句)
  // 保存中间表:建立用户到角色关系jegeUser1(jegeRole1,jegeRole2),jegeUser2(jegeRole1,jegeRole2,jegeRole3)(会出现5条insert语句)
  // 一般保存的：需要写5个save方法
  @Before
  public void save() throws Exception {
    Session session = HibernateUtils.INSTANCE.getSession();
    session.beginTransaction();
    // 保存2个用户,保存3个角色(会出现5条insert语句)
    User jegeUser1 = new User("jegeUser1");
    session.save(jegeUser1);
    User jegeUser2 = new User("jegeUser2");
    session.save(jegeUser2);

    Role jegeRole1 = new Role("jegeRole1");
    session.save(jegeRole1);
    Role jegeRole2 = new Role("jegeRole2");
    session.save(jegeRole2);
    Role jegeRole3 = new Role("jegeRole3");
    session.save(jegeRole3);// (这里的5个save方法会出现5条insert语句)

    // 保存中间表:建立用户到角色关系jegeUser1(jegeRole1,jegeRole2),jegeUser2(jegeRole1,jegeRole2,jegeRole3)
    jegeUser1.getRoles().add(jegeRole1);
    jegeUser1.getRoles().add(jegeRole2);

    jegeUser2.getRoles().add(jegeRole1);
    jegeUser2.getRoles().add(jegeRole2);
    jegeUser2.getRoles().add(jegeRole3);
    jegeUser2.getRoles().add(jegeRole3);

    session.getTransaction().commit();// 事务里面，更新脏数据(会出现5条insert语句)
    session.close();
  }

  // 删除jegeUser1(由hibernate自动处理,流程是先删除中间表,在删除jegeUser1)
  @Test
  public void delete1() throws Exception {
    Session session = HibernateUtils.INSTANCE.getSession();
    User jegeUser = (User) session.get(User.class, 1L);

    session.beginTransaction();
    session.delete(jegeUser);
    session.getTransaction().commit();

    session.close();
  }

  // 删除jegeUser1的关联的角色(中间表),实现不能删除jegeUser1
  @Test
  public void delete2() throws Exception {
    Session session = HibernateUtils.INSTANCE.getSession();
    User jegeUser = (User) session.get(User.class, 1L);

    session.beginTransaction();
    jegeUser.getRoles().clear();
    session.getTransaction().commit();

    session.close();
  }

  // 删除jegeUser1的一个角色,实现不能删除jegeUser1
  @Test
  public void delete3() throws Exception {
    Session session = HibernateUtils.INSTANCE.getSession();
    User jegeUser = (User) session.get(User.class, 1L);
    Role jegeRole = (Role) session.get(Role.class, 1L);

    session.beginTransaction();
    jegeUser.getRoles().remove(jegeRole);
    session.getTransaction().commit();

    session.close();
  }

  // 修改角色:先删除jegeUser1.jegeRole2,在添加jegeUser1.jegeRole3
  // 关系jegeUser1(jegeRole1,jegeRole2)变为关系jegeUser1(jegeRole1,jegeRole3)
  @Test
  public void update() throws Exception {
    Session session = HibernateUtils.INSTANCE.getSession();
    User jegeUser = (User) session.get(User.class, 1L);
    Role jegeRole2 = (Role) session.get(Role.class, 2L);
    Role jegeRole3 = (Role) session.get(Role.class, 3L);

    session.beginTransaction();
    jegeUser.getRoles().remove(jegeRole2);
    jegeUser.getRoles().add(jegeRole3);
    session.getTransaction().commit();

    session.close();
  }

}
