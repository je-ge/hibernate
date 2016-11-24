package com.jege.hibernate.execption;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:自定义异常处理类
 */
public class LogicExecption extends RuntimeException {
  public LogicExecption(String message) {
    super(message);
  }
}
