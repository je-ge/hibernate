package com.jege.hibernate.l_component;

public class Employee {
  private Long id;
  private String name;
  // 公司的地址
  private Address comAddress;
  // 籍贯所在地
  private Address homeAddress;

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

  public Address getComAddress() {
    return comAddress;
  }

  public void setComAddress(Address comAddress) {
    this.comAddress = comAddress;
  }

  public Address getHomeAddress() {
    return homeAddress;
  }

  public void setHomeAddress(Address homeAddress) {
    this.homeAddress = homeAddress;
  }

  @Override
  public String toString() {
    return "Employee [id=" + id + ", name=" + name + "]";
  }

}
