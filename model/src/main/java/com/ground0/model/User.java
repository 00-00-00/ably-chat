package com.ground0.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by zer0 on 6/4/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends RealmObject {

  @PrimaryKey Long id;

  String userName;

  String firstName;

  String lastName;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @JsonIgnore public String getFullName() {
    return firstName + " " + lastName;
  }
}
