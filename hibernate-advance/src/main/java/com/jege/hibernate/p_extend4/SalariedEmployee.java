package com.jege.hibernate.p_extend4;

public class SalariedEmployee extends Employee {
  private Double salary;

  public Double getSalary() {
    return salary;
  }

  public void setSalary(Double salary) {
    this.salary = salary;
  }

}
