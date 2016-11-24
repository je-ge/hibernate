package com.jege.hibernate.k_self;

import java.util.Set;

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

    Employee boss = new Employee("boss");
    Employee manager = new Employee("manager");
    Employee emp1 = new Employee("emp1");
    Employee emp2 = new Employee("emp2");

    manager.addEmployee(emp1).addEmployee(emp2);
    boss.addEmployee(manager);

    session.save(boss);

  }

  @Test
  public void get() {
    Employee boss = (Employee) session.get(Employee.class, 1L);
    System.out.println("boss:" + boss.getName());
    Set<Employee> employees = boss.getChildren();
    for (Employee manager : employees) {
      System.out.println("manager:" + manager.getName());
      Set<Employee> children = manager.getChildren();
      for (Employee employee : children) {
	System.out.println("employee:" + employee.getName());
      }
    }
  }

  @After
  public void colse() {
    session.getTransaction().commit();
    session.close();
  }
}
