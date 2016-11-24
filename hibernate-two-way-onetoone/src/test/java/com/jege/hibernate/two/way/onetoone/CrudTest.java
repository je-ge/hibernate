package com.jege.hibernate.two.way.onetoone;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import com.jege.hibernate.two.way.onetoone.User;
import com.jege.hibernate.util.HibernateUtils;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description: 
 */
public class CrudTest {

  // 在每个@Test之前自动执行，先保存数据
  @Before
  public void before() throws Exception {
  }

  @Test
  public void save() throws Exception {
HibernateUtils.INSTANCE.getSession();
  }

  
}
