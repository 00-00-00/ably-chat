package com.ground0.ablychat.viewmodel;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ground0.ablychat.activity.ChatActivity;
import com.ground0.ablychat.adapter.ChatAdapter;
import com.ground0.ablychat.core.viewmodel.AbstractActivityViewModel;
import com.ground0.ablychat.util.Constants;
import com.ground0.model.Message;
import com.ground0.model.MessageBuilder;
import com.ground0.model.User;
import com.ground0.repository.repository.Repository;
import com.ground0.repository.repository.RepositoryImpl;
import io.ably.lib.realtime.AblyRealtime;
import io.ably.lib.realtime.Channel;
import io.ably.lib.realtime.CompletionListener;
import io.ably.lib.types.AblyException;
import io.ably.lib.types.ErrorInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zer0 on 7/4/17.
 */

public class ChatActivityViewModel extends AbstractActivityViewModel<ChatActivity> {

  private User toUser;
  private String threadId;

  private Repository repository;
  private ChatAdapter chatAdapter;
  private ObjectMapper objectMapper;

  private List<Message> messages = new ArrayList<>();
  private String message = "";

  @Override public void afterRegister() {
    super.afterRegister();
    repository = RepositoryImpl.getInstance(getActivity());
    this.objectMapper = new ObjectMapper();
    fetchMessages();
  }

  @Override public void onRestoreState(Bundle savedInstanceState) {
    super.onRestoreState(savedInstanceState);
    threadId = savedInstanceState.getString(ChatActivity.CHAT_ACTIVITY_THREAD_ID);
    String userName = savedInstanceState.getString(ChatActivity.CHAT_ACTIVITY_TO_USERNAME);
    repository.getUser(userName).subscribe(user -> {
      if (user == null) {
        user = new User();
        user.setUserName(savedInstanceState.getString(ChatActivity.CHAT_ACTIVITY_TO_USERNAME));
      }
      this.toUser = user;
    });
  }

  @Override public void onSaveState(Bundle outstate) {
    super.onSaveState(outstate);
    if (toUser != null) {
      outstate.putString(ChatActivity.CHAT_ACTIVITY_TO_USERNAME, toUser.getUserName());
      outstate.putString(ChatActivity.CHAT_ACTIVITY_THREAD_ID, threadId);
    }
  }

  public ChatAdapter getAdapter() {
    if (chatAdapter == null) chatAdapter = new ChatAdapter(getApplication().getSelf(), messages);
    return chatAdapter;
  }

  private void fetchMessages() {
    repository.getMessages(threadId).subscribe(messages1 -> {
      this.messages.clear();
      this.messages.addAll(messages1);
      if (chatAdapter != null) chatAdapter.notifyDataSetChanged();
    });
  }

  public void sendMessage(View view) {
    Long time = System.currentTimeMillis();
    Message message = new MessageBuilder().setMessage(this.message)
        .setMessageId(time)
        .setSendTimeStamp(time)
        .setFromUser(toUser)
        .setToUser(getApplication().getSelf())
        .build();
    pushMessage(message);
  }

  private void pushMessage(Message message) {
    try {
      AblyRealtime ablyRealtime = new AblyRealtime(Constants.ABLY_API_KEY);
      Channel channel = ablyRealtime.channels.get(toUser.getUserName());
      String messageString = objectMapper.writeValueAsString(message);
      channel.publish("message", messageString, new CompletionListener() {
        @Override public void onSuccess() {
          Log.d(getClass().getSimpleName(), "Message sent");
          repository.saveMessage(message).subscribe(aLong -> {
            Log.d(getClass().getSimpleName(), "Message saved");
          });
        }

        @Override public void onError(ErrorInfo reason) {

        }
      });
    } catch (AblyException e) {
      e.printStackTrace();
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
