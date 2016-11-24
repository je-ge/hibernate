package com.jege.hibernate.two.way.onetomany;

import java.util.HashSet;
import java.util.Set;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description: 模型对象关系的一方
 */
public class Dept {
  private Long id;
  private String name;
  private Set<User> users = new HashSet<User>();

  public Dept() {

  }

  public Dept(Long id) {
    this.id = id;
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

  public Set<User> getUsers() {
    return users;
  }

  public void setUsers(Set<User> users) {
    this.users = users;
  }

  @Override
  public String toString() {
    return "Dept [id=" + id + ", name=" + name + "]";
  }

}
