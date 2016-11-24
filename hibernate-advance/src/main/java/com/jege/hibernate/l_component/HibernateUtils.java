package com.jege.hibernate.l_component;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {
  // 保证SessionFactory在当前应用程序里面只有一个实例
  private static SessionFactory sessionFactory;

  static {
    try {
      Configuration configuration = new Configuration();
      configuration.configure("com/jege/hibernate/l_component/hibernate.cfg.xml");
      // sessionFactory = configuration.buildSessionFactory();
      // 如果你想用不过时的方法,也可以定义一个hibernate.properties,
      // 把hibernate.cfg.xml里面所有的property属性移入hibernate.properties
      ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().build();
      sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    } catch (Exception e) {
      System.out.println("解析配置或者映射文件出现异常。。。。。。");
      e.printStackTrace();
    }
  }

  public static Session getSession() {
    return sessionFactory.openSession();
  }
}
