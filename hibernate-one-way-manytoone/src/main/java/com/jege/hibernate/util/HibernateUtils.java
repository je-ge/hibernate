package com.jege.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.jege.hibernate.execption.LogicExecption;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description: 枚举单例类，保证SessionFactory是唯一的
 */
public enum HibernateUtils {

  INSTANCE;

  // 保证SessionFactory在当前应用程序里面只有一个实例
  private SessionFactory sessionFactory;

  private HibernateUtils() {
    try {
      // 实例化配置对象
      Configuration configuration = new Configuration();
      // 加载classpath路径下面的默认配置文件hibernate.cfg.xml
      configuration.configure();
      // Hibernate4之后，Configuration类中，原来获取SessionFactory的方法buildSessionFactory()已经被标记为过时
      ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
	  .applySettings(configuration.getProperties()).build();
      // 官方建议使用buildSessionFactory(ServiceRegistry)这个方法来获取SessionFactory
      sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    } catch (Exception e) {
      // 显示堆栈异常，便于排除
      e.printStackTrace();
      throw new LogicExecption("解析配置文件或映射文件出现异常了：" + e.getMessage());
    }
  }

  public Session getSession() {
    return sessionFactory.openSession();
  }
}
