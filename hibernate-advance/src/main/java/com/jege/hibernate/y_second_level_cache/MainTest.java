package com.jege.hibernate.y_second_level_cache;

import java.math.BigDecimal;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

public class MainTest {
  @Before
  public void save() throws Exception {
    ProductType type = new ProductType();
    type.setName("类型1");

    Product product1 = new Product("产品1");
    product1.setPrice(new BigDecimal(200));
    Product product2 = new Product("产品2");
    product2.setPrice(new BigDecimal(300));

    // 建立多方到一方的关系
    product1.setType(type);
    product2.setType(type);

    // 建立一方多到方的关系(出现多方在一方的索引)
    type.getProducts().add(product2);
    type.getProducts().add(product1);

    Session session = HibernateUtils.getSession();
    session.beginTransaction();

    session.save(type);// 持久化状态
    session.save(product1);// 持久化状态
    session.save(product2);// 持久化状态

    session.getTransaction().commit();
    session.close();
  }

  // 模拟同一个SessionFactory,不同session获取同一个OID对象
  // 只发出一条sql：二级缓存命中
  @Test
  public void get() throws Exception {
    Session session1 = HibernateUtils.getSession();
    Product product1 = (Product) session1.get(Product.class, 1L);
    Product product2 = (Product) session1.get(Product.class, 1L);// 一级缓存命中
    session1.close();

    Session session2 = HibernateUtils.getSession();
    Product product3 = (Product) session2.get(Product.class, 1L);// 二级缓存命中
    Product product4 = (Product) session2.get(Product.class, 1L);// 一级缓存命中
    session2.close();
  }

  // 模拟同一个SessionFactory,不同session获取同一个OID对象里面的集合
  // 只发出一条sql：二级缓存命中
  @Test
  public void get2() throws Exception {
    Session session1 = HibernateUtils.getSession();
    ProductType productType1 = (ProductType) session1.get(ProductType.class, 1L);
    System.out.println(productType1.getProducts().size());
    session1.close();

    Session session2 = HibernateUtils.getSession();
    ProductType productType2 = (ProductType) session2.get(ProductType.class, 1L);
    System.out.println(productType2.getProducts().size());
    session2.close();
  }
}
