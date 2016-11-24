package com.jege.hibernate.j_compositeid2;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MainTest {
  Session session = null;

  @Before
  public void save() {
    session = HibernateUtils.getSession();
    session.beginTransaction();
    // 实例化主键类
    AirlineId id = new AirlineId();
    id.setStartCity("PEK");
    id.setEndCity("SHA");

    // 实体类
    AirlineBean bean = new AirlineBean();
    bean.setName("北京飞上海");
    bean.setId(id);

    session.save(bean);
  }

  @Test
  public void get() {
    // 主键
    AirlineId id = new AirlineId();
    id.setStartCity("PEK");
    id.setEndCity("SHA");

    AirlineBean target = (AirlineBean) session.get(AirlineBean.class, id);
    System.out.println(target.getName());

  }

  @After
  public void colse() {
    session.getTransaction().commit();
    session.close();
  }
}
