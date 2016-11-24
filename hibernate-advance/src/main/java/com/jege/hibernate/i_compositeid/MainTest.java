package com.jege.hibernate.i_compositeid;

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
    Airline airline = new Airline();
    airline.setStartCity("PEK");
    airline.setEndCity("SHA");
    airline.setName("北京飞上海");
    session.save(airline);
  }

  @Test
  public void get() {
    Airline id = new Airline();
    id.setStartCity("PEK");
    id.setEndCity("SHA");

    Airline target = (Airline) session.get(Airline.class, id);
    System.out.println(target.getName());

  }

  @After
  public void colse() {
    session.getTransaction().commit();
    session.close();
  }
}
