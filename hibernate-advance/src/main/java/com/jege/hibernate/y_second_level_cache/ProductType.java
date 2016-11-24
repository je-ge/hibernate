package com.jege.hibernate.y_second_level_cache;

import java.util.HashSet;
import java.util.Set;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:双向一方：一个产品类型下面有n个产品
 */
public class ProductType {
  private Long id;
  private String name;
  private Set<Product> products = new HashSet<Product>();// 一对多:集合Set

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

  public Set<Product> getProducts() {
    return products;
  }

  public void setProducts(Set<Product> products) {
    this.products = products;
  }

  @Override
  public String toString() {
    return "ProductType [id=" + id + ", name=" + name + "]";
  }

}
