package com.ground0.model;

/**
 * Created by zer0 on 7/4/17.
 */

public class MessageBuilder {
  public Message message;

  public MessageBuilder() {
    this.message = new Message();
  }

  public MessageBuilder setMessageId(Long messageId) {
    this.message.setMessageId(messageId);
    return this;
  }

  public MessageBuilder setMessage(String message) {
    this.message.setMessage(message);
    return this;
  }

  public MessageBuilder setSendTimeStamp(Long sendTimeStamp) {
    this.message.setSendTimeStamp(sendTimeStamp);
    return this;
  }

  public MessageBuilder setThreadId(String threadId) {
    this.message.setThreadId(threadId);
    return this;
  }

  public MessageBuilder setToUser(User toUser) {
    this.message.setToUser(toUser);
    return this;
  }

  public MessageBuilder setFromUser(User fromUser) {
    this.message.setFromUser(fromUser);
    return this;
  }

  public Message build() {
    return message;
  }
}