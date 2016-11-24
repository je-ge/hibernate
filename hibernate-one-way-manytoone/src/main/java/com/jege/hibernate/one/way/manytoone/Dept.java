package com.jege.hibernate.one.way.manytoone;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description: 模型对象关系的一方
 */
public class Dept {
  private Long id;
  private String name;

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

  @Override
  public String toString() {
    return "Dept [id=" + id + ", name=" + name + "]";
  }

}
