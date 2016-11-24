package com.jege.hibernate.n_extend2;

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
    Employee employee = new Employee("jege");

    HourlyEmployee hourlyEmployee = new HourlyEmployee();
    hourlyEmployee.setName("hourly");
    hourlyEmployee.setRate(200D);

    SalariedEmployee salariedEmployee = new SalariedEmployee();
    salariedEmployee.setName("salaried");
    salariedEmployee.setSalary(300D);

    session.save(employee);
    session.save(hourlyEmployee);
    session.save(salariedEmployee);
  }

  @Test
  public void get() {
    Employee employee = (Employee) session.get(Employee.class, 1L);
    System.out.println(employee.getName());

    HourlyEmployee hourlyEmployee = (HourlyEmployee) session.get(HourlyEmployee.class, 2L);
    System.out.println(hourlyEmployee.getName());
    System.out.println(hourlyEmployee.getRate());

    SalariedEmployee salariedEmployee = (SalariedEmployee) session.get(SalariedEmployee.class, 3L);
    System.out.println(salariedEmployee.getName());
    System.out.println(salariedEmployee.getSalary());
  }

  @After
  public void colse() {
    session.getTransaction().commit();
    session.close();
  }
}
