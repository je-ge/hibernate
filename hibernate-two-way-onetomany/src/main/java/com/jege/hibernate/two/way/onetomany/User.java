package com.jege.hibernate.two.way.onetomany;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description: 模型对象关系的多方
 */
public class User {
  private Long id;
  private String username;
  private Dept dept;

  public User() {

  }

  public User(Long id) {
    this.id = id;
  }
  public User(String username) {
    this.username = username;
  }
  public User(String username, Dept dept) {
    this.username = username;
    this.dept = dept;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Dept getDept() {
    return dept;
  }

  public void setDept(Dept dept) {
    this.dept = dept;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", username=" + username + "]";
  }

}
