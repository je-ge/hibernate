package com.jege.hibernate.z_optimistic_lock;

public class Product {
  private Long id;
  private String name;
  private Integer number;// 库存数量
  // 私有字段只能在Product类内部改, 这里由直接Hibernate管理
  private Integer version;

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

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    if (number != null && number < 1) {
      throw new RuntimeException("库存不足，不能购买");
    }
    this.number = number;
  }

}
