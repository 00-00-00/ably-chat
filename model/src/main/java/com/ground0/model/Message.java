package com.ground0.model;

import android.support.annotation.StringDef;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by zer0 on 6/4/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true) public class Message extends RealmObject {

  @Retention(RetentionPolicy.SOURCE)
  @StringDef({ MESSAGE_STAT_SENT, MESSAGE_STAT_SENDING, MESSAGE_STAT_FAILED })
  public @interface MessageState {
  }

  @JsonIgnore @Ignore public static final String MESSAGE_STAT_SENDING = "MESSAGE_NOT_SEND";
  @JsonIgnore @Ignore public static final String MESSAGE_STAT_SENT = "MESSAGE_SENT";
  @JsonIgnore @Ignore public static final String MESSAGE_STAT_FAILED = "MESSAGE_SEND_FAILED";

  @PrimaryKey Long messageId;

  String threadId;

  String message;

  User fromUser;

  User toUser;

  Long sendTimeStamp;

  Long receivedTimeStamp;

  @MessageState String state;

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

  @MessageState public String getState() {
    return state;
  }

  public void setState(@MessageState String state) {
    this.state = state;
  }
}
