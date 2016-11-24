package com.jege.hibernate.y_first_level_cache;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

public class MainTest {
  @Before
  public void save() throws Exception {
    Product product = new Product();
    product.setName("jege");

    Session session = HibernateUtils.getSession();
    session.beginTransaction();
    session.save(product);
    session.getTransaction().commit();
    session.close();
  }

  // 模拟同一个session获取同一个OID对象
  // 只发出一条sql：一级缓存命中
  @Test
  public void get() throws Exception {
    Session session = HibernateUtils.getSession();
    Product product1 = (Product) session.get(Product.class, 1L);
    Product product2 = (Product) session.get(Product.class, 1L);
    session.close();
  }
}
