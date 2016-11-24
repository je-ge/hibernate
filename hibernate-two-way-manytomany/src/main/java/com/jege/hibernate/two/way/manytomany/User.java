package com.jege.hibernate.two.way.manytomany;

import java.util.HashSet;
import java.util.Set;

public class User {
  private Long id;
  private String name;
  private Set<Role> roles = new HashSet<Role>();

  public User() {

  }

  public User(String name) {
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

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", name=" + name + "]";
  }

}
