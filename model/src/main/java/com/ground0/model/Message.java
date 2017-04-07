package com.ground0.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by zer0 on 6/4/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Message extends RealmObject {

  @PrimaryKey Long messageId;

  String threadId;

  String message;

  User fromUser;

  User toUser;

  Long sendTimeStamp;

  Long receivedTimeStamp;

  public String getThreadId() {
    return threadId;
  }

  public void setThreadId(String threadId) {
    this.threadId = threadId;
  }

  public Long getMessageId() {
    return messageId;
  }

  public void setMessageId(Long messageId) {
    this.messageId = messageId;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
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

  public Long getSendTimeStamp() {
    return sendTimeStamp;
  }

  public void setSendTimeStamp(Long sendTimeStamp) {
    this.sendTimeStamp = sendTimeStamp;
  }

  public Long getReceivedTimeStamp() {
    return receivedTimeStamp;
  }

  public void setReceivedTimeStamp(Long receivedTimeStamp) {
    this.receivedTimeStamp = receivedTimeStamp;
  }
}
