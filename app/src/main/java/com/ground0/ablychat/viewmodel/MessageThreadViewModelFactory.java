package com.ground0.ablychat.viewmodel;

import com.ground0.model.MessageThread;

/**
 * Created by zer0 on 7/4/17.
 */

public class MessageThreadViewModelFactory {

  public MessageThreadItemViewModel createItemViewModel(MessageThread messageThread) {
    return new MessageThreadItemViewModel(messageThread);
  }

  public class MessageThreadItemViewModel {

    MessageThread messageThread;

    private MessageThreadItemViewModel(MessageThread messageThread) {
      this.messageThread = messageThread;
    }

    public MessageThread getMessageThread() {
      return messageThread;
    }

    public void setMessageThread(MessageThread messageThread) {
      this.messageThread = messageThread;
    }
  }
}
