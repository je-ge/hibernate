package com.jege.hibernate.k_self;

import java.util.HashSet;
import java.util.Set;

public class Employee {
  private Long id;
  private String name;
  private Employee manager;// 多对一： 多个员工对一上级
  private Set<Employee> children = new HashSet<Employee>();// 一对多：一个领导有多个下级

  public Employee() {

  }

  public Employee(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Employee getManager() {
    return manager;
  }

  public void setManager(Employee manager) {
    this.manager = manager;
  }

  public Set<Employee> getChildren() {
    return children;
  }

  public void setChildren(Set<Employee> children) {
    this.children = children;
  }

  public Employee addEmployee(Employee employee) {
    children.add(employee);
    employee.setManager(this);
    return this;
  }

}
