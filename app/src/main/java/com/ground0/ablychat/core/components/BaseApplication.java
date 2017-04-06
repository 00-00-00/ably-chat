package com.ground0.ablychat.core.components;

import android.app.Application;
import android.util.Log;
import com.ground0.ablychat.util.Constants;
import com.ground0.model.User;
import io.ably.lib.realtime.AblyRealtime;
import io.ably.lib.realtime.Channel;
import io.ably.lib.types.AblyException;

/**
 * Created by zer0 on 6/4/17.
 */

public class BaseApplication extends Application {

  UserIdentity userIdentity;

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
    Channel channel = ablyRealtime.channels.get("test_channel");
    channel.subscribe(messages -> {
      Log.d(getClass().getSimpleName(), "Message received:" + messages.data);
    });
  }

  public User getSelf() {
    return userIdentity == null ? null : userIdentity.getSelf();
  }

  public void setSelf(User user) {
    if (userIdentity != null) {
      userIdentity.setSelf(user);
    }
  }
}
