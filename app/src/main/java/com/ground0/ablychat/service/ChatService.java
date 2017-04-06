package com.ground0.ablychat.service;

import android.util.Log;
import com.ground0.ablychat.core.components.BaseService;
import com.ground0.ablychat.util.Constants;
import io.ably.lib.realtime.AblyRealtime;
import io.ably.lib.realtime.Channel;
import io.ably.lib.types.AblyException;

/**
 * Created by zer0 on 7/4/17.
 */

public class ChatService extends BaseService {

  @Override public void onCreate() {
    super.onCreate();
    try {
      initAbly();
    } catch (AblyException e) {
      e.printStackTrace();
    }
  }

  private void initAbly() throws AblyException {
    //if (getSelf() == null) return;
    AblyRealtime ablyRealtime = new AblyRealtime(Constants.ABLY_API_KEY);
    Channel channel = ablyRealtime.channels.get("test_channel"/*getSelf().getId().toString()*/);
    channel.subscribe(messages -> {
      Log.d(getClass().getSimpleName(), "Message received:" + messages.data);
    });
  }
}
