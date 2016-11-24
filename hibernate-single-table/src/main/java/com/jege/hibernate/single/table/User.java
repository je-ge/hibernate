package com.jege.hibernate.single.table;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description: 模型对象
 */
public class User {
  private Long id;
  private String username;
  private String password;

  public User() {

  }

  public User(Long id) {
    this.id = id;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
  }

}
