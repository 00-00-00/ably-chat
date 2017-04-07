package com.ground0.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by zer0 on 6/4/17.
 */

public class MessageThread extends RealmObject {

  @PrimaryKey String id;

  User fromUser;

  User toUser;

  Message lastMessage;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public User getFromUser() {
    return fromUser;
  }

  public void setFromUser(User fromUser) {
    this.fromUser = fromUser;
  }

  public User getToUser() {
    return toUser;
  }

  public void setToUser(User toUser) {
    this.toUser = toUser;
  }

  public Message getLastMessage() {
    return lastMessage;
  }

  public void setLastMessage(Message lastMessage) {
    this.lastMessage = lastMessage;
  }

  public static String generateId(Message message) {
    if (message == null || message.getFromUser() == null || message.getToUser() == null) return "";
    return message.getFromUser().toString() + message.getToUser().toString();
  }

  public static String generateId(User fromUser, User toUser) {
    if (fromUser == null || toUser == null) return "";
    return fromUser.toString() + toUser.toString();
  }

  public static String generateId(String fromUser, String toUser) {
    return fromUser + toUser;
  }

  public void readFrom(Message message) {
    setFromUser(message.getFromUser());
    setId(message.getThreadId());
    setLastMessage(message);
    setToUser(message.getToUser());
  }
}
