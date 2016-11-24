package com.jege.hibernate.i_compositeid;

import java.io.Serializable;

//使用复合主键的持久化类需要实现serializable接口和覆盖equals()、hashCode()方法。
public class Airline implements Serializable {
  private String startCity;
  private String endCity;
  private String name;

  public String getStartCity() {
    return startCity;
  }

  public void setStartCity(String startCity) {
    this.startCity = startCity;
  }

  public String getEndCity() {
    return endCity;
  }

  public void setEndCity(String endCity) {
    this.endCity = endCity;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((endCity == null) ? 0 : endCity.hashCode());
    result = prime * result + ((startCity == null) ? 0 : startCity.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Airline other = (Airline) obj;
    if (endCity == null) {
      if (other.endCity != null)
	return false;
    } else if (!endCity.equals(other.endCity))
      return false;
    if (startCity == null) {
      if (other.startCity != null)
	return false;
    } else if (!startCity.equals(other.startCity))
      return false;
    return true;
  }

}
