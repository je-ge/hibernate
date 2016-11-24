package com.jege.hibernate.y_second_level_cache;

import java.math.BigDecimal;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:多方：多个产品属于同一个产品类型
 */
public class Product {
  private Long id;
  private String name;
  private ProductType type;// 多对一
  private BigDecimal price;

  public Product() {

  }

  public Product(String name) {
    super();
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

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public ProductType getType() {
    return type;
  }

  public void setType(ProductType type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "Product [id=" + id + ", name=" + name + ", price=" + price + "]";
  }

}
