package com.jege.hibernate.z_optimistic_lock;

import org.hibernate.Session;
import org.hibernate.StaleObjectStateException;
import org.junit.Before;
import org.junit.Test;

public class MainTest {
  @Before
  public void save() throws Exception {
    Product product = new Product();
    product.setName("xxxx");
    product.setNumber(10);

    Session session = HibernateUtils.getSession();
    session.beginTransaction();
    session.save(product);
    session.getTransaction().commit();
    session.close();
  }

  // 模拟2个事务在毫秒级别update操作
  @Test
  public void update() throws Exception {
    try {
      Session session1 = HibernateUtils.getSession();
      Session session2 = HibernateUtils.getSession();
      session1.beginTransaction();
      session2.beginTransaction();

      Product product = (Product) session1.get(Product.class, 1L);
      product.setNumber(product.getNumber() - 8);
      Product product2 = (Product) session2.get(Product.class, 1L);
      product2.setNumber(product2.getNumber() - 5);

      session2.update(product2);
      session1.update(product);

      session1.getTransaction().commit();
      session2.getTransaction().commit();
      session1.close();
      session2.close();
    } catch (StaleObjectStateException e) {
      Session session3 = HibernateUtils.getSession();
      Product product = (Product) session3.get(Product.class, 1L);
      System.out.println("库存已经改变，请重新刷新:" + product.getNumber());
    }
  }
}
