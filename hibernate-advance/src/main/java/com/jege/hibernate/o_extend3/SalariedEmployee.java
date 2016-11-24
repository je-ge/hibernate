package com.jege.hibernate.o_extend3;

public class SalariedEmployee extends Employee {
  private Double salary;

  public Double getSalary() {
    return salary;
  }

  public void setSalary(Double salary) {
    this.salary = salary;
  }

}
