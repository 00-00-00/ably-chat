package com.ground0.ablychat.viewmodel;

import android.view.View;
import com.ground0.model.MessageThread;

/**
 * Created by zer0 on 7/4/17.
 */

public class MessageThreadViewModelFactory {

  MessageThreadViewModelHandler handler;

  public MessageThreadItemViewModel createItemViewModel(MessageThreadViewModelHandler handler,
      MessageThread messageThread) {
    this.handler = handler;
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

    public void openDetail(View view) {
      handler.openDetail(messageThread);
    }
  }

  public interface MessageThreadViewModelHandler {
    void openDetail(MessageThread messageThread);
  }
}
