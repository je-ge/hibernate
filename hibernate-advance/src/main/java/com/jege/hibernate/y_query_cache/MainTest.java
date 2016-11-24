package com.jege.hibernate.y_query_cache;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

public class MainTest {
  @Before
  public void save() throws Exception {
    Product product = new Product();
    product.setName("xxxx");

    Session session = HibernateUtils.getSession();
    session.beginTransaction();
    session.save(product);
    session.getTransaction().commit();
    session.close();
  }

  // 模拟同一个SessionFactory,不同session获取hql查询
  // 查询缓存需要先配置类的二级缓存
  // 只发出一条sql：查询缓存命中
  @Test
  public void get() throws Exception {
    Session session1 = HibernateUtils.getSession();
    String hql = "select p from Product p";
    Query query = session1.createQuery(hql);
    // 放入查询缓存
    query.setCacheable(true);
    System.out.println(query.list().size());
    session1.close();

    Session session2 = HibernateUtils.getSession();
    String hql2 = "from Product";
    Query query2 = session2.createQuery(hql2);
    // 从查询缓存取
    query2.setCacheable(true);
    System.out.println(query2.list().size());
    session2.close();
  }
}
