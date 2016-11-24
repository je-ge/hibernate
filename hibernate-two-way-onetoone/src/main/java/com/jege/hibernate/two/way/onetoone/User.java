package com.jege.hibernate.two.way.onetoone;

//主一
public class User {
  private Long id;
  private String username;
  private String password;
  private UserInfo info;

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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public UserInfo getInfo() {
    return info;
  }

  public void setInfo(UserInfo info) {
    this.info = info;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
  }

}
