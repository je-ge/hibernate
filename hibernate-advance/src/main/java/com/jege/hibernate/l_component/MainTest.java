package com.jege.hibernate.l_component;

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
    Address comAddress = new Address("bj", "st1");
    Address homeAddress = new Address("sh", "st2");

    Employee employee = new Employee("user");
    employee.setComAddress(comAddress);
    employee.setHomeAddress(homeAddress);

    session.save(employee);
  }

  @Test
  public void get() throws Exception {
    Employee employee = (Employee) session.get(Employee.class, 1L);
    System.out.println(employee);
    System.out.println(employee.getComAddress());
    System.out.println(employee.getHomeAddress());
  }

  @After
  public void colse() {
    session.getTransaction().commit();
    session.close();
  }
}
