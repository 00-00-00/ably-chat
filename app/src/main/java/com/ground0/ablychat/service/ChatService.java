package com.ground0.ablychat.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ground0.ablychat.core.components.BaseService;
import com.ground0.ablychat.util.Constants;
import com.ground0.model.Message;
import com.ground0.model.MessageThread;
import com.ground0.repository.repository.Repository;
import com.ground0.repository.repository.RepositoryImpl;
import io.ably.lib.realtime.AblyRealtime;
import io.ably.lib.realtime.Channel;
import io.ably.lib.types.AblyException;
import java.io.IOException;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by zer0 on 7/4/17.
 */

public class ChatService extends BaseService {

  Repository repository;
  ObjectMapper objectMapper = new ObjectMapper();
  Channel channel;
  SelfClearedListener selfClearedListener = new SelfClearedListener();
  SelfRefreshedListener selfRefreshedListener = new SelfRefreshedListener();

  @Override public void onCreate() {
    super.onCreate();
    repository = RepositoryImpl.getInstance(getBaseApplication());
    try {
      initAbly();
      initListeners();
    } catch (AblyException e) {
      e.printStackTrace();
    }
  }

  private void initListeners() {
    registerReceiver(selfClearedListener, new IntentFilter(Constants.SELF_CLEARED_ACTION));
    registerReceiver(selfRefreshedListener, new IntentFilter(Constants.SELF_REFRESHED_ACTION));
  }

  private void initAbly() throws AblyException {
    if (getSelf() == null) return;
    AblyRealtime ablyRealtime = new AblyRealtime(Constants.ABLY_API_KEY);
    channel = ablyRealtime.channels.get(getSelf().getUserName());
    channel.subscribe(message -> {
      Log.d(getClass().getSimpleName(), "Message received:" + message.data);
      try {

        Message messagePOJO = objectMapper.readValue(message.data.toString(), Message.class);
        messagePOJO.setReceivedTimeStamp(System.currentTimeMillis());
        messagePOJO.setToUser(getBaseApplication().getSelf());
        messagePOJO.setThreadId(MessageThread.generateId(messagePOJO));

        Log.d(getClass().getSimpleName(), "Saving message to db");
        repository.saveMessage(getSelf().getUserName(), messagePOJO)
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(aLong -> {
              Log.d(getClass().getSimpleName(), "Success");
            });
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }

  @Override public void onDestroy() {
    super.onDestroy();
    unregisterReceiver(selfRefreshedListener);
    unregisterReceiver(selfClearedListener);
  }

  public class SelfClearedListener extends BroadcastReceiver {
    @Override public void onReceive(Context context, Intent intent) {
      try {
        channel.detach();
      } catch (AblyException e) {
        e.printStackTrace();
      }
    }
  }

  public class SelfRefreshedListener extends BroadcastReceiver {
    @Override public void onReceive(Context context, Intent intent) {
      try {
        initAbly();
      } catch (AblyException e) {
        e.printStackTrace();
      }
    }
  }
}
