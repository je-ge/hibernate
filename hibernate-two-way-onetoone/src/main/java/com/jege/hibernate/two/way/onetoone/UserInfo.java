package com.jege.hibernate.two.way.onetoone;

//从一
public class UserInfo {
  private Long id;
  private Integer age;
  private String address;
  private User user;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "UserInfo [id=" + id + ", age=" + age + ", address=" + address + "]";
  }

}
